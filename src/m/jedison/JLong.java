package m.jedison;

import m.jedison.spaces.JSpaceLong;

/**
 * TODO consider adding a lock as well to be able to lock on get ... operate then set operation.
 *
 * @author Joseph S.
 */
public class JLong extends JLive<Long> {
    public JLong(JSpaceLong space) {
        super(space);
    }
    
    public Long get() {
        return super.get(0L);
    }
    
    public Long increment() {
        return jedison().incr(name());
    }
    public Long increment(long by) {
        return jedison().incr(name(), by);
    }
    
    public Long decrement() {
        return jedison().decr(name());
    }
    public Long decrement(long by) {
        return jedison().decr(name(), by);
    }
}
