package m.jedison;

// We wish to extract this class. Note that it is a non static inner class, and thus automatically will match the generics of the wrapper class.

import redis.clients.jedis.StreamEntryID;

public abstract class JStreamMessageRecieved<K, V> extends JStreamMessage {
    
    protected final K key;
    protected final V val;
    
    // This is the resulting extracted method. The correct way to extract it is....
    // Hurray! But I bet it got the call wrong as well.... let us see.... we go back 
    protected JStreamMessageRecieved(StreamEntryID id, K key, V val) {
        super(id);
        this.key = key;
        this.val = val;
    }
    
    public K key() {
        return key;
    }
    
    public V val() {
        return val;
    }
}
