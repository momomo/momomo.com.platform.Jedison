package m.jedison.spaces;

import m.annotations.informative.Caution;

public class JSpaceLock extends JSpace {
    private static final String TYPE = "lock";

    public JSpaceLock(CharSequence space, CharSequence key) {
        super(space, key);
    }
    
    /**
     * We allow random generated locks, since on release they will be deleted, as well as cleaned up in trackers
     */
    @Caution public JSpaceLock(JSpace space) {
        super(space);
    }
    
    @Caution public JSpaceLock(CharSequence key) {
        super(key);
    }
    
    @Caution public JSpaceLock(CharSequence key, boolean asis) {
        super(key, asis);
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }
}
