package m.jedison;

import m.jedison.spaces.JSpaceId;
import m.jedison.spaces.JSpaceQueue;

public class JedisonIdConsumerStreamQueue extends JedisonIdConsumerStream {
    private static final String TYPE = JSpaceQueue.TYPE;

    public JedisonIdConsumerStreamQueue(JSpaceId id) {
        super(id, TYPE);
    }
}
