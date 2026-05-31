package m.jedison;

import m.Ex;
import m.Lambda;
import m.Runtimes;
import m.exceptions.$InterruptedException;
import m.exceptions.$RuntimeException;
import m.log;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

public class JedisonPoolOur implements JedisonPoolBase {

    protected final LinkedBlockingDeque<Jedis> queue  = new LinkedBlockingDeque<>();
    private   final JedisonConfigOur           config;

    /////////////

    public JedisonPoolOur(JedisonConfigOur config) {
        this.config = config;
        // Create connections concurrently
        IntStream.range(0, config.getPoolConfig().getMinIdle() + 1).parallel().forEach((i) -> {
            offer();
        });
    }

    /////////////

    @Override
    public <E extends Exception, R> R withPool(Lambda.R1E<R, Jedis, E> lambda, int attempts, boolean log) throws E {
        Jedis jedis = null;

        try {

            try {
                jedis = take();
            }
            catch (Throwable e) {
                if (--attempts >= 0) {
                    if (log) {
                        m.log.error(e);
                    }

                    return withPool(lambda, attempts, log);
                } else if (e instanceof InterruptedException) {
                    throw new $InterruptedException((InterruptedException) e);
                } else {
                    throw new $RuntimeException(e);
                }
            }

            return lambda.call(jedis);
        }
        catch (Throwable e) {
            Runtimes.pauseIfShuttingDownAtomically();

            throw Ex.runtime(e);
        }
        finally {
            if (jedis != null) {
                
                if (JedisonClientBare.client(jedis).isBroken() /*|| !instance.getClient().isConnected() */) {
                    if ( true ) {
                        jedis.close();
                        offer();
                    }
                    else {
                        try {
                            jedis.close();
                        }
                        catch(Exception e) {
                            m.log.error(getClass(), e);
                        }
                        finally {
                            offer();
                        }
                    }
                    
                } else {
                    offer(jedis);
                }
            }
        }
    }

    private void offer() {
        offer(3);
    }

    private void offer(int attempts) {
        Jedis jedis = null;
        try {
            jedis = this.config.newJedis();
        } catch (Throwable e) {
            if (--attempts >= 0) {
                log.error(getClass(), e);

                offer(attempts);
            }
        } finally {
            if (jedis != null) {
                offer(jedis);
            }
        }
    }

    /**
     * Least active instances will always end up last in the queue
     */
    protected void offer(Jedis jedis) {
        queue.offerFirst(jedis);
    }

    /**
     * Least active instances will always end up last in the queue
     */
    public Jedis take() throws InterruptedException {
        return queue.take();
    }


}
