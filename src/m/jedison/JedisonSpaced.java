package m.jedison;

import m.annotations.informative.Final;
import m.jedison.spaces.JSpace;

public interface JedisonSpaced extends JedisonGet, JedisonSerializer, JedisonSerializerThroughJedison {
    static final long serialVersionUID = 1L;
    
    public abstract JSpace space();
    
    @Final default String name() {
        return space().toString();
    }

}
