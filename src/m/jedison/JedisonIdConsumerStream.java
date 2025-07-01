package m.jedison;

import m.jedison.spaces.JSpaceId;

public class JedisonIdConsumerStream extends JedisonIdConsumer {
    private static final String TYPE = JSpaceStream.TYPE;

    public JedisonIdConsumerStream(JSpaceId id) {
        this(id, TYPE);
    }

    protected JedisonIdConsumerStream(JSpaceId id, String type) {
        super(id, TYPE + Jedison.DOT + type);
    }
    
}
