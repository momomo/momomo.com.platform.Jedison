package m.jedison.spaces;

public class JSpaceProcess extends JSpaceQueue {
    public static final String TYPE = "process";

    public JSpaceProcess(CharSequence space, CharSequence key) {
        super(space, key);
    }

    @Override
    protected CharSequence type() {
        return TYPE;
    }
}
