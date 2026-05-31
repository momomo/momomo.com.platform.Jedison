package m.jedison;

import m.instance.Id;
import m.jedison.spaces.JSpaceId;

public abstract class JedisonIdConsumer extends Id {
    public static final String CONSUMER = "consumer";

    protected JedisonIdConsumer(JSpaceId id, CharSequence type) {
        super(id + Jedison.DOT + CONSUMER + Jedison.DOT + type + Jedison.HASH + Jedison.CONSUMERS.incrementAndGet() + Jedison.HASH);
    }

}
