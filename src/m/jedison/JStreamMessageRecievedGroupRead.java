package m.jedison;

import redis.clients.jedis.StreamEntryID;

// We wish to extract this class. Note that it is a non static inner class, and thus automatically will match the generics of the wrapper class.
// 
public final class JStreamMessageRecievedGroupRead<K, V> extends JStreamMessageRecieved<K, V> {
    public JStreamMessageRecievedGroupRead(StreamEntryID id, K key, V val) {
        super(id, key, val);
    }
}
