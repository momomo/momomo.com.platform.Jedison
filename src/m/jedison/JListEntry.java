package m.jedison;

import m.Lambda;

public interface JListEntry<V> {
    
    <E extends Exception> void each(Lambda.R2E<Boolean, V, Long, E> lambda) throws E;
    
    default <E extends Exception> void each(Lambda.V1E<V, E> lambda) throws E {
        this.each(lambda.R2E());
    }
    
    default <E extends Exception> void each(Lambda.V2E<V, Long, E> lambda) throws E {
        this.each(lambda.R2E());
    }
    
    default <E extends Exception> void each(Lambda.R1E<Boolean, V, E> lambda) throws E {
        this.each(lambda.R2E());
    }
    
    
}
