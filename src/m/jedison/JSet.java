package m.jedison;

import m.Is;
import m.Lambda;
import m.Numbers;
import m.jedison.spaces.JSpaceSet;

import java.util.Map;
import java.util.Set;

/**
 * @author Joseph S.
 */
public final class JSet<V> implements JSpacedSerializable {
    private final JSpaceSet space;
    
    public JSet(JSpaceSet space) {
        this.space = space;
    }
    
    @Override
    public JSpaceSet space() {
        return space;
    }
    
    public boolean add(V val) {
        return jedison().hset(name(), serializeToHashcodeKey(val), val) == 1L;
    }
    
    public boolean contains(V val) {
        return jedison().hexists(name(), serializeToHashcodeKey(val));
    }
    
    public long size() {
        return jedison().hlen(name());
    }
    
    public long delete() {
        return Numbers.toLong(jedison().del(name()));
    }
    
    public boolean remove(V val) {
        return jedison().hdel(name(), serializeToHashcodeKey(val)) == 1L;
    }
    
    /////////////////////////////////////////////////////////////////////
    
    private Set<Map.Entry<byte[], byte[]>> entrySet() {
        return jedison().hgetAll(name()).entrySet();
    }
    
    public <E extends Exception> void each(Lambda.V1E<V, E> lambda) throws E {
        this.each(lambda.R1E());
    }
    
    public <E extends Exception> void each(Lambda.R1E<Boolean, V, E> lambda) throws E {
        for (Map.Entry<byte[], byte[]> entry : entrySet()) {
            if (
                Is.False(
                    lambda.call(
                        (V) deserializeVal(entry.getValue())
                    )
                )
            ) {
                return;
            }
        }
    }
    
    /////////////////////////////////////////////////////////////////////

    protected boolean isEmpty() {
        return size() == 0;
    }
    
}
