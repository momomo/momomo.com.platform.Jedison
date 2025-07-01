package m.jedison;

import redis.clients.jedis.StreamEntryID;

/**
 * @author Joseph S.
 * 
 * This JStream is an option to be sent to the redis server
 */
public class JStreamMessageSentGroupRead extends JStreamMessageSent {
    
    public static final JStreamMessageSentGroupRead
        ALL        = new JStreamMessageSentGroupRead(JStreamMessageSentGroupCreate.ALL.jedis),
        UNRECIEVED = new JStreamMessageSentGroupRead(StreamEntryID.UNRECEIVED_ENTRY)    
    ;
    
    public JStreamMessageSentGroupRead(CharSequence id) {
        super(id);
    }
    
    public JStreamMessageSentGroupRead(StreamEntryID id) {
        super(id);
    }
}
