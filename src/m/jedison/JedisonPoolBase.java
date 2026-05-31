package m.jedison;

import m.Lambda;

public interface JedisonPoolBase {
    public static final int     DEFAULT_ATTEMPTS = 3;
    public static final boolean DEFAULTS_LOG     = true;

    <E extends Exception, R> R withPool(Lambda.R1E<R, Jedis, E> lambda, int attempts, boolean log) throws E;

    default <E extends Exception> void withPool(Lambda.V1E<Jedis, E> lambda) throws E {
        withPool(lambda.R1E());
    }

    default <E extends Exception, R> R withPool(Lambda.R1E<R, Jedis, E> lambda) throws E {
        return withPool(lambda, DEFAULT_ATTEMPTS);
    }

    default <E extends Exception, R> R withPool(Lambda.R1E<R, Jedis, E> lambda, int attempts) throws E {
        return withPool(lambda, attempts, DEFAULTS_LOG);
    }

    default <E extends Exception, R> R withPool(Lambda.R1E<R, Jedis, E> lambda, boolean log) throws E {
        return withPool(lambda, DEFAULT_ATTEMPTS, log);
    }

}
