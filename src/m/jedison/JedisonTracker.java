package m.jedison;

import m.jedison.spaces.JSpaceId;
import m.jedison.spaces.JSpaceLive;
import m.jedison.spaces.JSpaceLock;
import m.jedison.spaces.JSpaceMap;
import m.jedison.spaces.JSpaceQueue;
import m.log;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Is serializable but we do not want to bind ourselves to that contract since we do not use it that way.
 * We will try to keep it "clean" however if possible.
 *
 * Currently contains all easily serializable properties.
 *
 * @author Joseph S.
 */
public class JedisonTracker implements JSpacedSerializable {

    private final JSpaceId      id;
    private final JSpaceTracker space;
    public  final JLive<OffsetDateTime> seen;

    // Connection was participating in these queues, and locks. Not sure he was holding anything relating to them though.
    public final JMap<JSpaceQueue, QueueEntry> queues;
    public final JMap<JSpaceLock, LockEntry>   locks;

    public JedisonTracker(JSpaceId id) {
        this.id     = id;
        this.space  = new JSpaceTracker(id);
        this.seen   = new JLive<>( new JSpaceLive(space,  "seen"   ) );
        this.queues = new JMap<> ( new JSpaceMap(space , "queues" ) );
        this.locks  = new JMap<> ( new JSpaceMap (space , "locks"  ) );
    }

    public <M> void register(JQueue<M> queue) {
        queues.put(queue.space(), new QueueEntry(queue));
    }
    
    public void register(JLock lock) {
        locks.put(lock.space(), new LockEntry(lock));
    }
    
    /**
     * When a connection is deemed dead, this destroy method is invoked by one to many detectors. Only one gets to execute.
     *
     * @return true if was destroyed, otherwise false
     */
    public boolean destroy() {
        // This lock can not be part of the constructor object since register by other locks requires JedisonTracker instance to already be created
        JLock lock = new JLock( new JSpaceLock(id, "destroy" ) );

        // Note that the lock.call(false) will result in return being null for non claimed locks. The false simply means don't wait around for the lock to be released.
        Boolean returns = lock.call(false, () -> {
            // All get to try to claim for this to destroyed connection.
    
            // One might claim it all, before next comes in. But he might not be registered for these types so next one get to attempt.
            queues.each((QueueEntry v, JSpaceQueue k) -> {
                JQueue<Object> copy = new JQueue<>(k) {
                    @Override protected void constructor() { /* Do not create or register as this is just a message sender and there is no need for cleanup after us */ }
                };
        
                // We send a message to others that they can claim it. We use the queue for it, so similar minded can do the claiming.
                copy.claim(v.consumer);
        
                // We've release it from id, now remove it from collection tracking queues
                queues.remove(k);
            });
        
            locks.each((LockEntry v, JSpaceLock k) -> {
                // In the event the connection itself is trying to destroy itself, this lock must remain which which will be released at the end of lambda either way
                if (lock.space().equals(k)) return;
            
                JLock copy = new JLock(k) {
                    @Override protected void constructor() { /* Do not create or register as this is just a message sender and there is no need for cleanup after us */ }
                };
            
                // We avoid deleting / releasing the lock if others are remaining the lock
                JSpaceId holder = copy.get();
                if (holder == null || holder.equals(id)) {
                    // If holder is null, we don't really need to delete it, since it will be deleted on a timeout usually, but not neccesarily.
                    // If the holder is the connection to be destroyed then we delete it to release potential awaitees.
                    copy.delete();
                }
            
                // We've release it from id, now remove it from collection tracking locks
                locks.remove(k);
            });
    
            // Nothing more is being held, meaning everything is gone.
            long remaining = queues.size() + locks.size();
            log.info(getClass(), "2. Analyzing tracker '", id, "' Remaining: ", remaining);
        
            if (remaining == 0L) {
                // Delete the queue registered for this tracker
                this.queues.delete();
            
                // Delete the locks registerd for this tracker
                this.locks.delete();
            
                // Delete the live object for this tracker
                seen.delete();
            
                return true;
            }
        
            return false;
        });
    
        return returns == null ? Boolean.FALSE : returns;
    }

    @Override
    public JSpaceTracker space() {
        return space;
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    private static final class QueueEntry implements Serializable {
        public static final long serialVersionUID = 1L;

        private final JedisonIdConsumerStreamQueue consumer;

        private QueueEntry(JQueue<?> queue) {
            this.consumer = queue.consumer();
        }
    }

    private static class LockEntry implements Serializable {
        public static final long serialVersionUID = 1L;

        private final JSpaceLock space;

        public LockEntry(JLock lock) {
            space = lock.space();
        }
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    
    public static void main(String[] args) {
    
    }
    
    
    
}
