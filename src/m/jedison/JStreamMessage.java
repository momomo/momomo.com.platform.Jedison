package m.jedison;

import m.instance.Id;
import redis.clients.jedis.StreamEntryID;

/**
 * A close copy of Jedis StreamEntryID which mostly delegates but also adds a few constructors but also is and Id and implements CharSequence.    
 * 
 * We do not wish to expose the Jedis api version as it contains other things we do not need to expose. 
 * 
 * @author Joseph S.
 */
public class JStreamMessage extends Id {
    public final StreamEntryID jedis;

    public JStreamMessage() {
        this(new StreamEntryID());
    }

    public JStreamMessage(CharSequence id) {
        this(new StreamEntryID(id.toString()));
    }

    public JStreamMessage(long time, long sequence) {
        this(new StreamEntryID(time, sequence));
    }

    public JStreamMessage(StreamEntryID id) {
        this.jedis = id;
    }
    
    @Override
    public String toString() {
        return jedis.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return jedis.equals(obj);
    }

    @Override
    public int hashCode() {
        return jedis.hashCode();
    }

    public int compareTo(StreamEntryID other) {
        return jedis.compareTo(other);
    }

    public long getTime() {
        return jedis.getTime();
    }

    public long getSequence() {
        return jedis.getSequence();
    }
}
