package m.jedison;

import m.Is;
import m.Lambda;
import m.Numbers;
import m.jedison.spaces.JSpaceMap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class JMap<K, V> implements JSpacedSerializable {
    private final JSpaceMap space;
    
    public JMap(JSpaceMap space) {
        this.space = space;
    }
    
    @Override
    public JSpaceMap space() {
        return space;
    }
    
    public boolean put(K key, V val) {
        return jedison().hset(name(), key, val) == 1L;
    }
    
    public V get(K key) {
        byte[] val = jedison().hget(name(), key);
        
        return (V) deserializeVal(val);
    }
    
    public boolean remove(K key) {
        return jedison().hdel(name(), key) == 1L;
    }
    
    public long size() {
        return jedison().hlen(name());
    }
    
    public long delete() {
        return Numbers.toLong(jedison().del(name()));
    }
    
    /////////////////////////////////////////////////////////////////////
    
    private Set<Map.Entry<byte[], byte[]>> entrySet() {
        return jedison().hgetAll(name()).entrySet();
    }
    
    private Set<byte[]> keys() {
        return jedison().hgetAll(name()).keySet();
    }
    
    private Collection<byte[]> values() {
        return jedison().hgetAll(name()).values();
    }
    
    /**
     * Iterate keyset while being deserialized
     */
    public <E extends Exception> void keys(Lambda.V1E<K, E> lambda) throws E {
        this.keys(lambda.R1E());
    }
    
    public <E extends Exception> void keys(Lambda.R1E<Boolean, K, E> lambda) throws E {
        for (byte[] key : keys()) {
            if (Is.False(
                    lambda.call(
                        (K) deserializeVal(key)
                    )
                )
            ) {
                return;
            }
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public <E extends Exception> void each(Lambda.V2E<V, K, E> lambda) throws E {
        this.each(lambda.R2E());
    }
    
    public <E extends Exception> void each(Lambda.R2E<Boolean, V, K, E> lambda) throws E {
        for (Map.Entry<byte[], byte[]> entry : entrySet()) {
            if ( Is.False(
                    lambda.call(
                        (V) deserializeVal(entry.getValue()),
                        (K) deserializeVal(entry.getKey())
                    )
                )
            ) {
                return;
            }
        }
    }
    
}
