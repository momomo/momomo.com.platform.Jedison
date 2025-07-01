package m.jedison;

import m.jedison.spaces.JSpace;
import m.jedison.spaces.JSpaceId;

public class JSpaceTracker extends JSpace {
    public static final String TYPE = "tracker";

    public JSpaceTracker(JSpaceId id) {
        super(id, "connection");
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }

}
