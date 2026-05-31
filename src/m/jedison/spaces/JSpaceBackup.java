package m.jedison.spaces;

import m.jedison.JSpaceSync;
import m.jedison.Jedison;

public class JSpaceBackup extends JSpaceSync {
    private static final String TYPE = JSpaceSync.TYPE + Jedison.DOT + "backup";
    
    
    public JSpaceBackup(CharSequence space, CharSequence key) {
        super(space, key);
    }
    
    @Override
    protected CharSequence type() {
        return TYPE;
    }
}
