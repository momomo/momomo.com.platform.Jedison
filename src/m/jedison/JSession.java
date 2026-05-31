package m.jedison;

import java.util.concurrent.TimeUnit;

import m.annotations.informative.Overridable;
import m.jedison.spaces.JSpaceSession;

/**
 * A session rarely will have concurrency issues.
 *
 * @author Joseph S.
 */
public class JSession<K, V> extends JBackup<K, V> {
    
    public JSession(JSpaceSession space) {
        super(space);
    }
    
    @Override
    protected JedisonEvent newEvents() {
        return super.newEvents();
    }
    
    @Override
    protected void putAll(KeyLock<K, V> lock, K key, V val, JedisonEvent.OutgoingEvent.Hset outgoing) {
        super.putAll(lock, key, val, outgoing);
    
        // However we choose to retain the value being set if a get call is made then we would be able to read it.
        // There is a lock for all actions anyway.
        // This means that this is potentially set twice though
        lock.message( new Message<>(null, val) );
    }
    
    @Overridable
    public int expires() {
        return (int) TimeUnit.DAYS.toSeconds(180);
    }
    
}
