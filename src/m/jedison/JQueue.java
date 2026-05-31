package m.jedison;

import m.Lambda;
import m.Threads;
import m.annotations.informative.Optional;
import m.annotations.informative.Overridable;
import m.jedison.spaces.JSpaceQueue;
import m.log;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * A queue based on JStream, keys are defined here.
 *
 * @author Joseph S.
 */
public class JQueue<M> implements JSpacedSerializable {

    /**
     * Key can be of any type.
     * Val is restricted to M on 'main' manually
     * Val is restricted to RedisConsumerId for 'claim'
     * Val is unrestricted for anything else that a subclass might desire to handle manually
     * M really is only related to 'main'
     */
    protected final JSpaceQueue                  space;
    private   final JedisonIdConsumerStreamQueue consumer;
    protected final JStream<String, Object>         stream;
    protected final JStreamGroup<String, Object>    group;
    protected final JStreamGroupKey<String, Object> main;
    protected final JStreamGroupKey<String, Object> claim;
    
    private final ExecutorService executor;
    
    public JQueue(JSpaceQueue space) {
        this.space = space;
        this.consumer = new JedisonIdConsumerStreamQueue(jedison().id());
        
        this.stream = new JStream<>(new JSpaceStream(this.space), consumer);
        this.group  = stream.group("a");
        this.main   = this.group.key("main" );
        this.claim  = this.group.key("claim");
        
        this.executor = newExecutor();
        
        constructor();
    }
    
    @Overridable
    protected void constructor() {
        create();
        register();
    }
    
    @Overridable
    public int parallelism() {
        return 1;
    }
    
    protected ExecutorService newExecutor() {
        return this.parallelism() > 1 ? m.Executors.newFixedThreadPool("Jedison :: " + getClass() + " (" + JQueue.class +") :: " + name(), this.parallelism()) : null;
    }
    
    protected final <E extends Exception> void exec(Lambda.VE<E> lambda) throws E {
        if (this.executor == null) {
            lambda.call();
        } else {
            async(lambda);
        }
    }
    
    protected void async(Lambda.VE<? extends Exception> lambda) {
        this.executor.submit( jedison().runnable(lambda) );
    }
    
    @Overridable
    protected void create() {
        try {
            this.group.create(JStreamMessageSentGroupCreate.ALL);
        } catch (Exception ignore) { /* Will throw if group already exists */ }
    }
    
    @Overridable
    protected void register() {
        jedison().tracker().register(this);
    }
    
    /////////////
    
    @Overridable
    protected void queue(String key, Object val) {
        this.stream.add(key, val);
    }
    
    public void queue(M message) {
        queue(main.name(), message);
    }
    
    public void claim(JedisonIdConsumerStreamQueue id) {
        queue(claim.name(), id);
    }
    
    /////////////
    
    protected List<JStreamMessageRecievedGroupRead<String, Object>> read() {
        return group.read();
    }
    
    public void process() {
        while (true) {
            try {
                onReads(read());
            }
            catch (Throwable e) {
                onReadsFail(e);
            }
        }
    }
    
    @Overridable
    protected void onReadsFail(Throwable e) {
        log.error(getClass(), e);
        
        // To avoid hard reads incase of multiple errors due to something like a connection error to try to avoid reading again immediately after
        Threads.sleep(250);
    }
    
    /////////////
    
    @Overridable
    protected void onClaimed(List<JStreamMessageRecievedGroupRead<String, Object>> claimed) {
        // In theory we could process them immediately since we know what the claimed messages are, however, we let them be procesed from the queue instead so we do nothing here.
        // try { onReads(claimed) } catch() { } might otherwise be an option, but we might end up with unforseen consequences such as process order of messages being scewed due to a consumer dying.
        // Recommended to leave this as is
    }
    
    private void onReads(List<JStreamMessageRecievedGroupRead<String, Object>> read) {
        for (JStreamMessageRecievedGroupRead<String, Object> message : read) {
            exec((Lambda.V) () -> onRead(message));
        }
    }
    
    private void onRead(JStreamMessageRecievedGroupRead<String, Object> message) {
        try {
            if (message.key().equals(main.name())) {
                $process$((M) message.val());
            }
            else if (message.key().equals(claim.name())) {
                remove((JedisonIdConsumerStreamQueue) message.val()); // Claims pendings from consumer and then removes them
            }
        } catch (Throwable e) {
            // There to catch processFail, processSuccess errors as well
            onReadFail(e);
        } finally {
            // We always ack on completion regardless if success or fail. If fail, processor should take care of that, however, here we say that this message is no longer to linger as pending.
            ack(message);
        }
    }
    
    protected void onReadFail(Throwable e) {
        log.error(getClass(), e);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    protected final void $process$(M message) {
        $process$(message, 0);
    }
    
    @Overridable
    protected void $process$(M message, int attempt) {
        boolean success;
        try {
            process(message);
            
            success = true;
        } catch (Throwable e) {
            success = false;
            
            onProcessFailed(message, e, attempt);
        }
        
        if (success) {
            onProcessSuccess(message);
        }
    }
    
    @Optional
    @Overridable
    protected void process(M message) throws Exception {
        // Not abstract due to producers/querers not really desiring to be part of the consuming can leave it as is
    }
    
    @Optional
    @Overridable
    protected void onProcessFailed(M message, Throwable e, int attempt) {
        log.error(getClass(), e);
        
        if (++attempt <= 3) {
            Threads.sleep(250);
            
            // Retry
            $process$(message, attempt);
        } else {
            Threads.sleep(1000);
            
            // Requeue
            queue(message);
        }
    }
    
    @Optional
    @Overridable
    protected void onProcessSuccess(M message) {
        // On success do nothing special by default
    }
    /////////////////////////////////////////////////////////////////////
    
    protected long ack(JStreamMessage message) {
        return group.ack(message);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Remove consumer
     */
    public void remove(JedisonIdConsumerStreamQueue consumer) {
        group.remove(consumer, this::onClaimed);
    }
    
    @Override
    public JSpaceQueue space() {
        return this.space;
    }
    
    public JedisonIdConsumerStreamQueue consumer() {
        return consumer;
    }
    
}
