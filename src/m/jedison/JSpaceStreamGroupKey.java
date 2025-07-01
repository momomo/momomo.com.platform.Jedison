package m.jedison;

import m.jedison.spaces.JSpace;

public final class JSpaceStreamGroupKey extends JSpace {
    public static final String TYPE = "key";

    public JSpaceStreamGroupKey(JSpaceStreamGroup space, CharSequence key) {
        super(space, key);
    }

    public JSpaceStreamGroupKey(CharSequence key, boolean asis) {
        super(key, asis);
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }

}
