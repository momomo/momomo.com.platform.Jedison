package m.jedison.spaces;

import m.Runtimes;
import m.jedison.Jedison;

import static m.jedison.Jedison.HASH;

public class JSpaceId extends JSpace {
    public static final String TYPE = "connection" + Jedison.DOT + "id";

    public JSpaceId(JSpaceJedison space) {
        this(space + HASH + Runtimes.ID + "" + HASH + Jedison.CONSUMERS.incrementAndGet() + HASH);
    }

    public JSpaceId(CharSequence id) {
        super(id, true);
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }

}
