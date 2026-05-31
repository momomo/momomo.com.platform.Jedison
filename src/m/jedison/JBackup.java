package m.jedison;

import m.annotations.informative.Overridable;
import m.jedison.spaces.JSpaceBackup;

import java.util.concurrent.TimeUnit;

/**
 *
 * This could be used to set a value for later use, but where we do not care of the immediate verification that this was set.
 *
 * We set and go on assuming it will eventually be set. It could be a counting up operation or setting of a status or something similar.
 *
 * @author Joseph S.
 */
public class JBackup<K, V> extends JSync<K, V> {
    
    public JBackup(JSpaceBackup space) {
        super(space);
    }
    
    @Override
    protected JedisonEvent newEvents() {
        return jedison().concurrent;
    }
    
    @Override
    public void put(K key, V val) {
        jedison().async(()-> {
            super.put(key, val);
        });
    }
    
    @Override
    protected void putAll(KeyLock<K, V> lock, K key, V val, JedisonEvent.OutgoingEvent.Hset outgoing) {
        super.putSend(outgoing);
    
        // We do not care of the immediate value of this put operation but rather assign the syncing to the event listener which may or may not have it ready for a subsequent get() call
        // Most of the time it will, but we can expect a delay on the event listener where we can see this actually not being ready on a get().
        // For backup and session we do not need the base classes high requirements for synced settings, but rather we only wish to put and continue,
        // and accept some order discrepencies due to rare high concurrency situations between potentially distributed shared client session.
    }
    
    @Overridable
    public int expires() {
        return (int) TimeUnit.DAYS.toSeconds(7);
    }
    
}
