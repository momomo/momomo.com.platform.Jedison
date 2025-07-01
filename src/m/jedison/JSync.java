package m.jedison;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import m.Ex;
import m.Lambda;
import m.Runtimes;
import m.annotations.informative.Inspired;
import m.annotations.informative.Overridable;
import m.annotations.informative.Overriden;
import m.instance.Id;
import m.instance.LockKey;
import m.instance.LockObj;
import m.jedison.spaces.JSpace;

/**
 * Note, this can not be easily serialized as it relies on a thread being started on recreation.
 * Extend and serialize it on your own accord.
 * It is possible, but not while supporting all serializable and deserialize libraries.
 * Each has its own way to do so. For us, we currently do not have the use case for this one.
 * <p>
 * TODO. If only one participator, then don't listen, nor update values later for self.
 *
 * @author Joseph S.
 */
@Inspired(LockKey.class)
public class JSync<K, V> implements JedisonSpaced {
    
    protected final ConcurrentHashMap<K, KeyLock<K, V>> locks;
    protected final JSpaceSync                          space;
    
    public JSync(JSpaceSync space) {
        this.locks = new ConcurrentHashMap<>();
        this.space = space;
        
        JedisonEvent events = newEvents();
        
        listen(events);
    }
    
    @Overridable
    @Overriden(by = {JSession.class, JBackup.class})
    protected JedisonEvent newEvents() {
        // To ensure proper setting order, we process events serially using one thread per JSync instance
        // We could also do this using the global linear event listener, but this would be shared with all JSync instances and could potentially become clogged.
        // Using concurrent would potentially lead to messages being parsed out of order
        return new JedisonEvent(){
            @Override protected int parallelism() {
                return 1;
            }
        };
    }
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    @Overridable
    public void put(K key, V val) {
        KeyLock<K, V> lock; synchronized ( lock = getLock(key) ) {
            // We construct the outgoing event message to which we will subscribe for an update
            JedisonEvent.OutgoingEvent.Hset outgoing = new JedisonEvent.OutgoingEvent.Hset(
                new Id(),
                owner(),
                identity(),
                name(),
                serializeVal(key),
                serializeVal(val)
            );
            
            putAll(lock, key, val, outgoing);
        }
    }
    
    @Overridable
    protected void putAll(KeyLock<K, V> lock, K key, V val, JedisonEvent.OutgoingEvent.Hset outgoing) {
        Message.Lock messageLock = lock.registerMessageSent(outgoing.id);
        
        putSend(outgoing);
        
        // Then we wait until we recieve the message back to ensure it has been properly set across "all" distributed systems.
        // This kind of synced timing is as close as we can get.
        // We could possibly wait for others to confirm they have updated their value but communication back to us would put us once again at the mercy of concurrency issues
        // If all subscribers are subscribing the same way, we should get a fairer set / get pattern this way.
        // That means that on every set, updates happen later, at consumption, but here we wait for it before releasing.
        // There are pros and cons. Pros is consistency and a quebased and concurrently safe way of setting values in redis where each set will be performed linearly and in order.
        // The cons is the wait for the set, since we are waiting here to get the info of it being set.
        // If we had ignore the wait, we could have had a scenario where things are set out of order in a concurrently harsh environment.
        // This follows the hardest possible standard besides the JLive where we allow a local cache of values as well, which is the goal.
        // The cost is on the put, but gets are consistent and cheap.
    
        messageLock.awaitResponse();
    }
    
    @Overridable
    protected void putSend(JedisonEvent.OutgoingEvent.Hset outgoing) {
        // We send it off
        jedison().mmm_hset(outgoing, expires());
    }
    
    public V get(K key) {
        KeyLock<K, V> lock; synchronized ( lock = getLock(key) ) {
            return lock.synchronize(()-> {
                // Nothing on this lock, could it be set in redis?
                if ( lock.message == null ) {
                    byte[] got = jedison().hget(name(), key);
                    
                    V val = (V) jedison().deserializeVal( got );
        
                    lock.message(new Message<>(null, val));
                }
    
                return lock.message.val;
            });
        }
    }
    
    public <E extends Exception> void compute(K key, Lambda.R2E<V, K, V, E> lambda) throws E  {
        atomic(key, (k, v) -> {
            put(k, lambda.call(k, v) ); // Rewrites the compute value to remote Redis
        });
    }
    
    public <E extends Exception> void atomic(K key, Lambda.V2E<K, V, E> lambda) throws E {
        atomic(key, lambda.R2E());
    }
    
    public <R, E extends Exception> R atomic(K key, Lambda.R2E<R, K, V, E> lambda) throws E {
        KeyLock<K, V> lock; synchronized ( lock = getLock(key) ) {
            return lambda.call(key, get(key));
        }
    }
    
    public void remove(K key) {
        KeyLock<K, V> lock; synchronized ( lock = getLock(key) ) {
            locks.remove(key); // TODO remove from remote as well!  jedison().hdel(name(), val);
        }
    }
    
    @Overridable
    protected void listen(JedisonEvent events) {
        events.listen(name(), JedisonEvent.OutgoingEvent.Hset.ACTION, event -> {
            onMessage(event);
        });
    }
    
    @Overridable
    protected void onMessage(JedisonEvent.IncomingEvent event) {
        onMessage((JedisonEvent.IncomingEvent.Hset) event);
    }
    
    @Overridable
    protected void onMessage(JedisonEvent.IncomingEvent.Hset event) {
        String id = event.id;
        K key = (K) deserializeVal(event.key);
        V val = (V) deserializeVal(event.val);
        
        getLock(key).message(new Message<>(id, val));
    }
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    @Overridable
    protected CharSequence owner() {
        return jedison().id();
    }
    
    @Overridable
    protected String identity() {
        return Runtimes.identity(this);
    }
    
    @Overridable
    @Overriden(by = { JSession.class })
    public int expires() {
        return (int) TimeUnit.DAYS.toSeconds(1);
    }
    
    protected KeyLock<K, V> getLock(K key) {
        return locks.computeIfAbsent(key, (k) -> new KeyLock<>());
    }
    
    @Override
    public JSpace space() {
        return space;
    }
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    /**
     * We use a double lock approach, synchronizing on both and waiting and notifying on two.
     */
    public static final class KeyLock<K, V> {
        private final LockObj internal = new LockObj();
        
        // Message id to response awaitees which registeres prior to sending off
        private final Map<String, Message.Lock> sent    = new ConcurrentHashMap<>();
        private       Message<V>                message;
    
        public V val() {
            synchronized (internal) {
                if (message == null) return null;
                
                return message.val;
            }
        }
    
        /**
         * Invoked before a message is sent
         */
        public Message.Lock registerMessageSent(String id) {
            synchronized (internal) {
                Message.Lock messageLock = new Message.Lock();
                
                this.sent.put(id, messageLock);
                
                return messageLock;
            }
        }
    
        public void message(Message<V> message) {
            synchronized (internal) {
                this.message = message;
            
                String id = message.id;
                if (id != null) {
                    Message.Lock messageLock = this.sent.remove(id);
                
                    if ( messageLock != null ) {
                        messageLock.notifyResponse();
                    }
                }
            }
        }
        
        public <R, E extends Exception> R synchronize(Lambda.RE<R, E> lambda) throws E{
            synchronized (internal) {
                return lambda.call();
            }
            
        }
        
    }
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Holds the value and the message id.
     * We avoid saving too much info on this object.
     */
    protected static final class Message<V> {
        protected final String id;  // Id is not really important other than to identity your own posted message on put so you can stop waiting. On get, this would be null.
        protected final V      val;
        
        protected Message(String id, V val) {
            this.id  = id;
            this.val = val;
        }
        
        public static final class Lock extends LockObj {
            public boolean recieved = false;
            
            public void awaitResponse() {
                // Note, do while won't work here, since techincally the notifyMessageResponse might come in before we reach this stage here.
                synchronized (this) {
                    while ( recieved == false ) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            throw Ex.runtime(e);
                        }
                    }
                }
            }
            
            private void notifyResponse() {
                synchronized (this) {
                    recieved = true;
                    notifyAll();
                }
            }
            
        }
    }
    
}
