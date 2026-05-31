package m.jedison;

import m.jedison.spaces.JSpace;

public final class JSpaceStreamGroup extends JSpace {
    public static final String GROUP = "group";

    public JSpaceStreamGroup(JSpaceStream space, CharSequence key) {
        super(space, key);
    }

    public JSpaceStreamGroup(CharSequence key, boolean asis) {
        super(key, asis);
    }

    @Override
    protected CharSequence type() {
        return GROUP;
    }
}
