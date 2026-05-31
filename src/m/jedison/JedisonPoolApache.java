package m.jedison;

import m.Lambda;
import redis.clients.jedis.JedisFactory;
import redis.clients.jedis.JedisPool;

public class JedisonPoolApache implements JedisonPoolBase {

    private JedisPool pool;

    public JedisonPoolApache(JedisonConfigApache config) {
        this(config.getPoolConfig(), new JedisonFactoryApache(config));
    }

    public JedisonPoolApache(JedisonPoolApacheConfig config, JedisonFactoryApache factory) {
        pool = new redis.clients.jedis.JedisPool(config.delegate, factory);
    }
    /////////////

    @Override
    public <E extends Exception, R> R withPool(Lambda.R1E<R, Jedis, E> lambda, int attempts, boolean log) throws E {
        Jedis jedis = null;

        try {
            try {
                jedis = (Jedis) pool.getResource();
            } catch (Throwable e) {
                if (--attempts >= 0) {
                    m.log.error(getClass(), e);

                    return withPool(lambda, attempts, log);
                } else {
                    throw e;
                }
            }
            return lambda.call(jedis);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }
    
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    public static class JedisonFactoryApache extends JedisFactory {
        protected final JedisonConfigApache config;
    
        public JedisonFactoryApache(JedisonConfigApache config) {
            super(
                    config.getUrl(),
                    config.getPort(),
                    config.getTimeout(),
                    config.getTimeout(),
                    config.getUsername(),
                    config.getPassword(),
                    config.getDatabase(),
                    config.getClient(),
                    config.getSslFactory() != null,
                    config.getSslFactory(),
                    config.getSslParams(),
                    config.getSslVerifier()
            );
            this.config = config;
        }
    
        @Override
        protected Jedis newJedis() {
            return config.newJedis();
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
}
