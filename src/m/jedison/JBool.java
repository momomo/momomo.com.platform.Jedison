package m.jedison;

import m.Is;
import m.jedison.spaces.JSpaceLong;

/**
 * TODO consider adding a lock as well to be able to lock on get ... operate then set operation.
 *
 * @author Joseph S.
 */
public class JBool extends JLive<Boolean> {
    public JBool(JSpaceLong space) {
        super(space);
    }
    
    public Boolean get() {
        return super.get(null);
    }
    
    public Boolean isFalse() {
        return Is.Equal(get(), "0");
    }
    
    public Boolean isTrue() {
        return Is.Equal(get(), "1");
    }
    
}
