package m.jedison.spaces;

import m.annotations.informative.Caution;

public class JSpaceLong extends JSpaceLive {
    private static final String TYPE = "num";
    
    public JSpaceLong(CharSequence space, CharSequence key) {
        super(space, key);
    }
    
    @Caution public JSpaceLong(JSpace space) {
        super(space);
    }
    
    @Caution public JSpaceLong(CharSequence key) {
        super(key);
    }
    
    @Caution public JSpaceLong(CharSequence key, boolean asis) {
        super(key, asis);
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }
}
