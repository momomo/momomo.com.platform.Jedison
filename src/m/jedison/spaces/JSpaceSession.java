package m.jedison.spaces;

import m.jedison.JSpaceSync;
import m.jedison.Jedison;

public class JSpaceSession extends JSpaceBackup {
    private static final String TYPE = JSpaceSync.TYPE + Jedison.DOT + "session";
    
    
    public JSpaceSession(CharSequence space, CharSequence key) {
        super(space, key);
    }
    
    @Override
    protected CharSequence type() {
        return TYPE;
    }
}
