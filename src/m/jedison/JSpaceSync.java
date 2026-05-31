package m.jedison;

import m.annotations.informative.Caution;
import m.jedison.spaces.JSpaceMap;

public class JSpaceSync extends JSpaceMap {
    protected static final String TYPE = JSpaceMap.TYPE + Jedison.DOT + "cache";
    
    public JSpaceSync(CharSequence space, CharSequence key) {
        super(space, key);
    }
    
    @Caution public JSpaceSync(CharSequence key) {
        super(key);
    }
    
    @Caution public JSpaceSync(CharSequence key, boolean asis) {
        super(key, asis);
    }
    
    @Override
    protected CharSequence type() {
        return TYPE;
    }
}
