package m.jedison;

import m.Numbers;
import m.jedison.spaces.JSpace;
import m.jedison.spaces.JSpaceLive;

/**
 * TODO consider adding a lock as well to be able to lock on get ... operate then set operation.
 *
 * @author Joseph S.
 */
public class JLive<T> implements JSpacedSerializable {
    private final JSpaceLive space;

    public JLive(JSpaceLive space) {
        this.space = space == null ? new JSpaceLive(jedison().space()) : space;
    }
    
    public T get(T dephault) {
        T v = $get$(); return v == null ? dephault : v;
    }
    
    public T get() {
        return $get$();
    }
    
    private T $get$() {
        return (T) deserializeVal(jedison().get(name()));
    }
    
    public void set(T val) {
        jedison().set(name(), val);
    }

    public T getAndSet(T val) {
        return (T) deserializeVal( jedison().getSet(name(), val) );
    }
    
    public long delete() {
        return Numbers.toLong(jedison().del(name()));
    }
    
    @Override
    public JSpace space() {
        return space;
    }
    
}
