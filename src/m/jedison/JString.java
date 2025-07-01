package m.jedison;

import m.jedison.spaces.JSpaceLong;

/**
 * TODO consider adding a lock as well to be able to lock on get ... operate then set operation.
 *
 * @author Joseph S.
 */
public class JString extends JLive<String> {
    public JString(JSpaceLong space) {
        super(space);
    }
    
    public String get() {
        return super.get(null);
    }
    
}
