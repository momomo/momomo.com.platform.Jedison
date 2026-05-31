package m.jedison.spaces;

import m.annotations.informative.Caution;
import m.exceptions.$JedisonException;

public class JSpaceJedison extends JSpace {
    
    public JSpaceJedison(CharSequence name) {
        this(join("Jedison", name), true);
    }
    
    @Caution public JSpaceJedison(CharSequence name, boolean asis) {
        super(name, asis);
    }

    @Override
    protected CharSequence type() {
        throw new $JedisonException("Should not be called.");
    }
}
