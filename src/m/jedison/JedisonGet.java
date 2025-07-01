package m.jedison;

import m.annotations.informative.Final;

public interface JedisonGet extends JedisonDeclaration {
    @Override
    @Final default Jedison jedison() {
        return Jedison.get();
    }
}
