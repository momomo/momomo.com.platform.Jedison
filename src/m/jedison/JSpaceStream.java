package m.jedison;

import m.jedison.spaces.JSpace;

public class JSpaceStream extends JSpace {
    public static final String TYPE = "stream";

    public JSpaceStream(CharSequence space, CharSequence key) {
        super(space, key);
    }

    public JSpaceStream(CharSequence key) {
        super(key);
    }

    public JSpaceStream(CharSequence key, boolean asis) {
        super(key, asis);
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }

}
