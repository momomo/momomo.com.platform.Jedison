package m.jedison;

import redis.clients.jedis.StreamEntryID;

/**
 * @author Joseph S.
 * 
 * This JStream is an option to be sent to the redis server
 */
public class JStreamMessageSentGroupCreate extends JStreamMessageSent {
    
    public static final JStreamMessageSentGroupCreate
        ALL    = new JStreamMessageSentGroupCreate("0-0"),
        AFTER  = new JStreamMessageSentGroupCreate(StreamEntryID.LAST_ENTRY)
    ;
    
    /**
     * Should be using the ones created already above instead 99% of the time. 
     */
    public JStreamMessageSentGroupCreate(CharSequence id) {
        super(id);
    }
    
    /**
     * @see JStreamMessageSentGroupCreate (CharSequence) 
     */
    public JStreamMessageSentGroupCreate(StreamEntryID id) {
        super(id);
    }

    
}
