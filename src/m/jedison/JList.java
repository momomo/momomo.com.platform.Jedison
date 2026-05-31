package m.jedison;

import java.util.ArrayList;
import java.util.List;

import m.Lambda;
import m.Numbers;
import m.annotations.informative.Caution;
import m.jedison.spaces.JSpaceList;

/**
 * @author Joseph S.
 */

// TODO
// https://redis.io/docs/latest/commands/?group=list
// pop from end
// pop from start
// pop from start and block
// pop from end and block
// add on index
// removeAll(value)
//
public final class JList<V> implements JSpacedSerializable, JListEntry<V> {
    private final JSpaceList space;
    
    public JList(JSpaceList space) {
        this.space = space;
    }
    
    @Override
    public JSpaceList space() {
        return space;
    }
    
    public V get(long index) {
        return (V) deserializeVal( jedison().lindex(name(), index) );
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public long add(V val) {
        return jedison().rpush(name(), val);
    }
    public long addFirst(V val) {
        return jedison().lpush(name(), val);
    }
    
    /////////////////////////////////////////////////////////////////////

    public long remove(V v) {
        return removeAll(v, 1L, RemoveDirection.FORWARD);
    }
    
    public long removeAll(V v) {
        return removeAll(v, 0L, RemoveDirection.FORWARD);
    }
    
    public long removeAll(V v, long amount, RemoveDirection direction) {
        Long removed = jedison().binary.lrem(serializeKey(name()), amount * direction.multiplier, serializeVal(v));
        return Numbers.toLong(removed);
    }
    public enum RemoveDirection {
        FORWARD(1L), BACKWARD(-1L);
        
        private final long multiplier;
        RemoveDirection(long multiplier) {
            this.multiplier = multiplier;
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public long remove(long index) {
        byte[] v     = jedison().lindex(name(), index);
        Long removed = jedison().binary.lrem(serializeKey(name()), 1L, v);
        
        return Numbers.toLong(removed);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public long size() {
        return jedison().llen(name());
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /////////////////////////////////////////////////////////////////////

    public List<V> entries() {
        List<V> list = new ArrayList<>();

        each((v, i)-> {
            list.add(v);
        });

        return list;
    }

    /**
     * Use with caution.
     * This will return a view of the items in redis, but once you iterate the returned list
     * there can be no guarentee of their order, nor of their actual existence and value at the time you encounter it in iterations.
     * Consider getting size() and iterate by index for certain cases instead.
     */
    @Caution public <E extends Exception> void each(Lambda.R2E<Boolean, V, Long, E> lambda) throws E {
        jedison().lrange(name(), 0L, Long.MAX_VALUE, (byte[] v, Long i) -> {
            return lambda.call((V) deserializeVal(v), i);
        });
    }
    
    
    
    /////////////////////////////////////////////////////////////////////

    
    
}
