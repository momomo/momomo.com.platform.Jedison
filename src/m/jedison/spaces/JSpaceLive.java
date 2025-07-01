package m.jedison.spaces;

import m.annotations.informative.Caution;

public class JSpaceLive extends JSpace {
    private static final String TYPE = "live";
    
    public JSpaceLive(CharSequence space, CharSequence key) {
        super(space, key);
    }
    
    @Caution public JSpaceLive(JSpace space) {
        super(space);
    }
    
    @Caution public JSpaceLive(CharSequence key) {
        super(key);
    }
    
    @Caution public JSpaceLive(CharSequence key, boolean asis) {
        super(key, asis);
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }
}
