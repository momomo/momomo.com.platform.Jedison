package m.jedison;

import m.Is;
import m.Lambda;
import m.Threads;
import m.Yarn;
import m.annotations.informative.Overridable;
import m.events.Event.Status;
import m.instance.Charseq;
import m.instance.LockObj;
import m.log;
import redis.clients.jedis.BinaryJedisPubSub;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Do note!
 * 
 * For this to work, redis.conf has to set the following: 
 *    
 *      notify-keyspace-events "KEA"
 *    
 * It then has to be started afterwards. 
 * 
 * Note that issuing 
 *      
 *      redis-cli config set notify-keyspace-events "KEA" 
 *      
 * won't fly or work in our tests.      
 * 
 * @author Joseph S.
 */
public class JedisonEvent implements JedisonGet {
    public final JedisonPubSub pubsub;
    
    /**
     * Channel -> ChannelListener
     */
    private final ConcurrentHashMap<Pattern, PatternListener> patterns = new ConcurrentHashMap<>();
    private final ExecutorService                             executor;
    
    public JedisonEvent() {
        this.pubsub     = new JedisonPubSub(this); 
        
        int parallelism = parallelism();
        this.executor   = newExecutor(parallelism);
    
        start();
    }
    
    protected void start() {
        AtomicInteger lock = new AtomicInteger(1);
        
        jedison().async(()-> {
            jedison().withPool((jedis)-> {
                Threads.decrement(lock);
                
                // This call is locking, and will end up in a while loop managing incoming messages and this jedis connection will never be released
                jedis.psubscribe(pubsub); // (1)
            });
        });
    
        Threads.await(lock);
        Threads.sleep(10);                // Just enough to ensure (1) runs before we allow the continuation. 
    }
    
    @Overridable
    protected int parallelism() {
        return 1;
    }
    
    protected ExecutorService newExecutor(int parallelism) {
        return parallelism > 1 ? m.Executors.newFixedThreadPool("Jedison :: " + getClass() + " (" + JedisonEvent.class +")", parallelism) : null;
    }
    
    protected <E extends Exception> void exec(Lambda.VE<E> lambda) throws E {
        if ( this.executor == null ) {
            lambda.call();
        } 
        else {
            async(lambda);
        }
    }
    
    protected void async(Lambda.VE<? extends Exception> lambda) {
        this.executor.submit( jedison().runnable(lambda) );
    }
    
    public <E extends Exception> void listen(CharSequence key, CharSequence listeners, Lambda.V1E<IncomingEvent, E> lambda) {
        listen(key, listeners, lambda.R1E());
    }
    public <E extends Exception> void listen(CharSequence key, CharSequence listeners, Lambda.R1E<Status, IncomingEvent, E> lambda) {
        listen(new Pattern(key), listeners, lambda);
    }
    public <E extends Exception> void listen(Pattern pattern, CharSequence listeners, Lambda.V1E<IncomingEvent, E> lambda) {
        listen(pattern, listeners, lambda.R1E());
    }
    public <E extends Exception> void listen(Pattern pattern, CharSequence listeners, Lambda.R1E<Status, IncomingEvent, E> lambda) {
        listen(pattern, listeners.toString().split(","), lambda);
    }
    
    public <E extends Exception> void listen(CharSequence key, CharSequence[] listeners, Lambda.V1E<IncomingEvent, E> lambda) {
        listen(key, listeners, lambda.R1E());
    }
    public <E extends Exception> void listen(CharSequence key, CharSequence[] listeners, Lambda.R1E<Status, IncomingEvent, E> lambda) {
        listen(new Pattern(key), listeners, lambda);
    }
    public <E extends Exception> void listen(Pattern pattern, CharSequence[] listeners, Lambda.V1E<IncomingEvent, E> lambda) {
        listen(pattern, listeners, lambda.R1E());
    }
    public <E extends Exception> void listen(Pattern pattern, CharSequence[] listeners, Lambda.R1E<Status, IncomingEvent, E> lambda) {
        patterns.compute(pattern, (c, v) -> {
            boolean first;
            if ( first = (v == null) ) {
                v = new PatternListener();
            }
            
            for ( CharSequence listener : listeners) {
                v.listen(listener, lambda);
            }
            
            if ( first ) {
                pubsub.subscribe(pattern);
            }
            
            return v;
        });
    }
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    private static class PatternListener {
        private final ConcurrentHashMap<String, List<Lambda.R1E<Status, IncomingEvent, ? extends Throwable>>> listeners = new ConcurrentHashMap<>();

        /**
         * @param message will be referring to things like expire, expired, set, del...
         */
        public final void onMessage(String pattern, String channel, Object message) {
            if ( message instanceof String ) {
                String action = message.toString();
                
                call(new IncomingEvent("*"   , channel, pattern, action));
                call(new IncomingEvent(action, channel, pattern, action));
            }
            else {
                if ( message instanceof OutgoingEvent.Hset  ) {
                    String action = ((OutgoingEvent) message).action;
    
                    call(new IncomingEvent.Hset("*"   , channel, pattern, (OutgoingEvent.Hset) message));
                    call(new IncomingEvent.Hset(action, channel, pattern, (OutgoingEvent.Hset) message));
                }
            }
        }
        
        public void call(IncomingEvent event) {
            List<Lambda.R1E<Status, IncomingEvent, ? extends Throwable>> lambdas = listeners.get(event.listener);
    
            if ( !Is.Ok(lambdas) ) return;
    
            // We use a list iterator to be able to remove while iterating should be desire to
            ListIterator<Lambda.R1E<Status, IncomingEvent, ? extends Throwable>> iterator = lambdas.listIterator();
            while(iterator.hasNext()) {
                Lambda.R1E<Status, IncomingEvent, ? extends Throwable> lambda = iterator.next();
                try {
                    if ( lambda.call(event) == Status.REMOVE ) {
                        iterator.remove();
                    }
                } catch ( Throwable throwable ) {
                    log.error(getClass(), throwable);  // Subscriber can choose not to throw and handle it themselves, but if they don't care, they can allow us to simply log it and continue
                }
            }
        }
    
        private <E extends Exception> void listen(CharSequence listener, Lambda.R1E<Status, IncomingEvent, E> lambda) {
            listeners.compute(listener.toString().trim(), (k, v) -> {
                if ( v == null ) {
                    v = new ArrayList<>();
                }
                v.add(lambda); return v;
            });
        }

    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    private static final class JedisonPubSub extends BinaryJedisPubSub implements JedisonGet, JedisonSerializer, JedisonSerializerThroughJedison {
        private final JedisonEvent outer;
        private final LockObj      lock  = new LockObj();
    
        private JedisonPubSub(JedisonEvent outer) {
            this.outer = outer;
        }
    
        @Override
        public void onPMessage(byte[] pattern, byte[] channel, byte[] message) {
            outer.exec(() -> {
                String p = deserializeKey(pattern);
                String c = deserializeKey(channel);
                Object m = deserializeValFallbackKey(message); // Here we might get both our own serializeVal stuff but also just straight String keys from Redis, such as expire, hset ... 
    
                PatternListener listener = outer.patterns.get(new Pattern(p));
                if (listener != null) {
                    listener.onMessage(p, c, m);
                }
                
            });
        }
    
        private void subscribe(Pattern pattern) {
            // If multiple calls from various threads attempt to subscribe on the same jedis instance without waiting 
            // for the answer then we will get an error. Since we only have one jedis instance to manage the subscribed 
            // events per Jedison instance, issueing the subscribe has to be mai    
            synchronized (lock) {
                // This will not block, and will send out the subscribe command using the dedicated jedis client set up in start.  
                super.psubscribe( serializeKey(pattern.toString()) ); 
            }
        }
    
        @Override
        public Jedison jedison() {
            return outer.jedison();
        }
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    public static final class Pattern implements Charseq {
        private static final String KEYSPACE_LEFT       = "__";
        private static final String KEYSPACE_CENTER     = "keyspace@0";
        private static final String KEYSPACE_RIGHT      = KEYSPACE_LEFT + ":";
        private static final String KEYSPACE_ALL        = KEYSPACE_LEFT + KEYSPACE_CENTER + KEYSPACE_RIGHT;
        private static final String KEYSPACE_ALL_QUOTED = java.util.regex.Pattern.quote(KEYSPACE_ALL);
    
        private final String pattern;
    
        public Pattern(CharSequence name) {
            this(KEYSPACE_LEFT + KEYSPACE_CENTER + KEYSPACE_RIGHT, name);
        }
    
        public Pattern(CharSequence left, CharSequence right) {
            this(left.toString() + right.toString());
        }
    
        private Pattern(String pattern) {
            this.pattern = pattern;
        }
    
        @Override
        public String toString() {
            return pattern;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Charseq.super.$equals$(obj);
        }
    
        @Override
        public int hashCode() {
            return Charseq.super.$hashCode$();
        }
        
        public String getRight() {
            return getRight(this); 
        }
    
        private static String getRight(Pattern pattern) {
            return getRight(pattern.pattern);
        }
    
        /**
         * Given __keyspace@0__:mymap it will return mymap 
         */
        private static String getRight(String keyspace) {
            String[] split = keyspace.split(KEYSPACE_ALL_QUOTED);
            return split.length > 1 ? split[1] : ""; 
        }
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    public static class IncomingEvent implements Charseq {
        public final String listener;
        public final String channel;
        public final String pattern;
        public final String message;

        private IncomingEvent(CharSequence listener, CharSequence channel, CharSequence pattern, CharSequence message) {
            this.listener = listener.toString();
            this.channel  = channel.toString();
            this.pattern  = pattern.toString();
            this.message  = message.toString();
        }
    
        @Override
        public String toString() {
            return Yarn.$("""
                
                listener = ${listener}
                channel  = ${channel} 
                pattern  = ${pattern} 
                message  = ${message} 
                """,

                          "${listener}", listener,
                          "${channel}" , channel,
                          "${pattern}" , pattern,
                          "${message}" , message.toString()
            );
        }
        
        public static final class Hset extends IncomingEvent {
            public final String id;
            public final String sender;
            public final String owner;
            public final String action;
            public final String name;
    
            public final byte[] key;
            public final byte[] val;
    
            private Hset(CharSequence listener, CharSequence channel, CharSequence pattern, OutgoingEvent.Hset outgoing) {
                super(listener, channel, pattern, outgoing.getClass().getCanonicalName());
                
                this.id     = outgoing.id;
                this.sender = outgoing.sender;
                this.owner  = outgoing.owner;
                this.action = outgoing.action;
                this.name   = outgoing.name;
                
                this.key    = outgoing.key; 
                this.val    = outgoing.val; 
            }
    
            @Override
            public String toString() {
                return Yarn.$("""
                
                listener = ${listener}
                channel  = ${channel} 
                pattern  = ${pattern} 
                message  = ${message}
                           sender = ${sender} 
                           owner  = ${owner} 
                           action = ${action} 
                           name   = ${name} 
                           key    = ${key} 
                           val    = ${val}
                
                """,

                              "${listener}", listener,
                              "${channel}" , channel,
                              "${pattern}" , pattern,
                              "${sender}"  , sender,
                              "${owner}"   , owner,
                              "${action}"  , action,
                              "${name}"    , name,
                              "${key}"     , key,
                              "${val}"     , val,
                              "${message}" , message
    
                );
            }
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    public static class OutgoingEvent {
        public final String id;
        public final String sender;
        public final String owner;
        public final String action;
        public final String name;
        
        public OutgoingEvent(CharSequence id, CharSequence sender, CharSequence owner, CharSequence action, CharSequence name) {
            this.id     = id.toString();
            this.sender = sender.toString();
            this.owner  = owner == null ? null : owner.toString();
            this.action = action.toString();
            this.name   = name.toString();
        }
    
        public String channel() {
            return new Pattern((CharSequence) this.name).toString();
        }
        
        public OutgoingEvent message() {
            return this; 
        }
        
        public static final class Hset extends OutgoingEvent {
            public static final String ACTION = "mmm.redis.event.hset";
            
            public final byte[] key; 
            public final byte[] val; 
            
            public Hset(CharSequence id, CharSequence sender, CharSequence owner, CharSequence name, byte[] key, byte[] val) {
                super(id, sender, owner, ACTION, name);
                this.key = key;
                this.val = val;
            }
        }
    }
    
    
}
