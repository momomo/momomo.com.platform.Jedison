package m.jedison.spaces;

import m.annotations.informative.Caution;

public class JSpaceSet extends JSpace {
    private static final String TYPE = "set";

    public JSpaceSet(CharSequence space, CharSequence key) {
        super(space, key);
    }
    
    @Caution public JSpaceSet(CharSequence key) {
        super(key);
    }
    
    @Caution public JSpaceSet(CharSequence key, boolean asis) {
        super(key, asis);
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }
}
