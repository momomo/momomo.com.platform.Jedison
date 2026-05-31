package m.jedison.spaces;

import m.UUIDS;
import m.instance.Charseq;
import m.Strings;
import m.jedison.Jedison;

public abstract class JSpace implements Charseq {
    public static final long serialVersionUID = 1L;

    private final String val;
    
    public JSpace(JSpace space) {
        this(space, generatedKey());
    }

    public JSpace(CharSequence space, CharSequence key) {
        this( join(space, key) );
    }

    public JSpace(CharSequence key) {
        this.val = join(key, type());
    }

    public JSpace(CharSequence key, boolean asis) {
        this.val = key.toString();
    }

    protected abstract CharSequence type();

    public String name() {
        return toString();
    }
    
    @Override
    public String toString() {
        return this.val;
    }

    @Override
    public boolean equals(Object o) {
        return Charseq.super.$equals$(o);
    }

    @Override
    public int hashCode() {
        return Charseq.super.$hashCode$();
    }
    
    protected static String generatedKey() {
        return join(Jedison.G, UUIDS.U36.create().toString());
    }

    protected static String join(CharSequence ... things) {
        return Strings.join(Jedison.DOT, things);
    }
}
