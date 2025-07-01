package m.jedison;

import m.Lambda;
import m.annotations.informative.Overridable;
import m.jedison.spaces.JSpaceId;
import m.jedison.spaces.JSpaceLock;

import redis.clients.jedis.params.SetParams;

import java.util.concurrent.TimeUnit;

/**
 * Single space
 *
 * @author Joseph S.
 */
public class JLock implements JSpacedSerializable {
    private static final boolean AWAIT = true;

    private final JSpaceLock space;
    private final long       timeout;
    
    public JLock(JSpaceLock space) {
        this(space, TimeUnit.SECONDS.toMillis(30));
    }
    
    public JLock(JSpaceLock space, long timeout) {
        this.space   = space;
        this.timeout = timeout;
        
        constructor();
    }
    
    @Overridable
    protected void constructor() {
        register();
        listen  ();
    }
    
    protected void register() {
        jedison().tracker().register(this);
    }
    
    protected void listen() {
        // Everytime the key is deleted, awaitees is to be woken up to try claiming the global lock
        // Notifing is not expensive if there are no awaitees, so we can afford to have this as a global listener,
        // rather than adding and removing it constantly.
        jedison().concurrent.listen(name(), "del", (event -> {
            synchronized (space) {
                space.notifyAll();
            }
            return null;
        }));
    }
    
    public <E extends Exception> void call(Lambda.VE<E> lambda) throws E {
        call(AWAIT, lambda.RE());
    }
    public <E extends Exception> void call(boolean await, Lambda.VE<E> lambda) throws E {
        call(await, lambda.RE());
    }
    public <R, E extends Exception> R call(Lambda.RE<R, E> lambda) throws E {
        return call(AWAIT, lambda);
    }
    
    /**
     *
     * @param await false if the caller should not wait for the lock but simply exit.
     * @return null if does not claim otherwise whatever the lambda returns
     */
    public <R, E extends Exception> R call(boolean await, Lambda.RE<R, E> lambda) throws E {
        // Locally, only one can enter here concurrently
        synchronized (space) {
            if ( !lock(await) ) return null;

            try {
                // Globally, only one execute here concurrently
                return lambda.call();
            }
            finally {
                // In the event the system shuts down and we never delete a mechanism to release global awaitees is required.
                // This mechanism relies on detecting stale connections which will delete everything related, thus triggering the listen event on other machines, and releasing potential awaitees.
                // We could have also listened to the global expired event, however, that would require a timeout which would set a hard limit on how long the lambda can execute.
                // Instead, we register this lock, and when a connection is discovered to be dead (see Jedison class) we delete locks held by that machine, which in turn will trigger the event notifiying local awaitees.
                unlock();
            }
        }
    }

    public boolean lock() {
        return lock(AWAIT);
    }

    public boolean lock(boolean await) {
        synchronized (space) {
            while ( !wasClaimed(tryClaim()) ) {
                if ( await ) {
                    await();
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    public void unlock() {
        synchronized (space) {
            delete();
        }
    }

    private void await() {
        try {
            space.wait();
        } catch ( InterruptedException ignore ) {}
    }

    protected void delete() {
        jedison().del(name());
    }

    /**
     * Returns Jedison id of the holder
     */
    public JSpaceId get() {
        return (JSpaceId) deserializeVal(jedison().get(name()));
    }

    /**
     * Returns status of the attempt
     */
    private String tryClaim() {
        return set(jedison().id());
    }

    private String set(JSpaceId id) {
        return jedison().set(name(), id, new SetParams().nx().px(timeout));
    }

    private boolean wasClaimed(String val) {
        return "OK".equals(val);
    }
    
    @Override
    public JSpaceLock space() {
        return space;
    }

}
