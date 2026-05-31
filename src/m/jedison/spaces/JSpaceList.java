package m.jedison.spaces;

import m.annotations.informative.Caution;

public class JSpaceList extends JSpace {
    private static final String TYPE = "list";

    public JSpaceList(CharSequence space, CharSequence key) {
        super(space, key);
    }
    
    @Caution public JSpaceList(CharSequence key) {
        super(key);
    }
    
    @Caution public JSpaceList(CharSequence key, boolean asis) {
        super(key, asis);
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }
}
