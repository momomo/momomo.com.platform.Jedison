package m.jedison.spaces;

import m.annotations.informative.Caution;

public class JSpaceMap extends JSpace {
    protected static final String TYPE = "map";

    public JSpaceMap(CharSequence space, CharSequence key) {
        super(space, key);
    }

    @Caution public JSpaceMap(CharSequence key) {
        super(key);
    }

    @Caution public JSpaceMap(CharSequence key, boolean asis) {
        super(key, asis);
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }
}
