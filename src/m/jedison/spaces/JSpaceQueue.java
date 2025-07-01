package m.jedison.spaces;

public class JSpaceQueue extends JSpace {
    public static final String TYPE = "queue";

    public JSpaceQueue(CharSequence space, CharSequence key) {
        super(space, key);
    }

    public JSpaceQueue(CharSequence key) {
        super(key);
    }

    public JSpaceQueue(CharSequence key, boolean asis) {
        super(key, asis);
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }
}
