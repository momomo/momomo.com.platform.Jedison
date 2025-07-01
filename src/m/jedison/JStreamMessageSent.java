package m.jedison;

import redis.clients.jedis.StreamEntryID;

/**
 * @author Joseph S.
 * 
 * This JStream is an option to be sent to the redis server
 */
public abstract class JStreamMessageSent extends JStreamMessage {
    
    /**
     * Should be using the ones created already above instead 99% of the time. 
     */
    protected JStreamMessageSent(CharSequence id) {
        super(id);
    }
    
    /**
     * @see JStreamMessageSent (CharSequence) 
     */
    protected JStreamMessageSent(StreamEntryID id) {
        super(id);
    }

    
}
