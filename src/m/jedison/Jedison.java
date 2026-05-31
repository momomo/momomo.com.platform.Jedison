package m.jedison;

import m.Ex;
import m.Is;
import m.Lambda;
import m.Runtimes;
import m.Threads;
import m.Time;
import m.exceptions.$JedisonNotAvailableException;
import m.instance.Nanotime;
import m.jedison.spaces.JSpaceId;
import m.jedison.spaces.JSpaceJedison;
import m.jedison.spaces.JSpaceMap;
import m.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * https://gist.github.com/JonCole/925630df72be1351b21440625ff2671f
 *
 *  >> redis-cli
 *
 * TO SHOW ALL STREAMS:
 * https://redis.io/commands/scan
 * SCAN 0 TYPE stream
 *
 * XINFO STREAM Jedison.Store.bigbuy.ftp.image.download.process.queue.stream
 *
 * TO SHOW ALL SET STUFF:
 *  KEYS *
 *
 *  @author Joseph S.
 */
public class Jedison implements JedisonClientOur, JedisonPoolBase, JedisonDeclaration {
    public static final String DOT = ".", HASH = "#", OPEN = "[", CLOSE = "]", G = "G";

    public static final ThreadLocal<Jedison> JEDISON   = new ThreadLocal<>();
    public static final AtomicLong           CONSUMERS = new AtomicLong();    // Count for uniques

    public static final long CONNECTION_DEEMED_DEAD        = TimeUnit.SECONDS.toMillis(60);
    public static final long CONNECTION_DEEMED_DEAD_LEEWAY = TimeUnit.SECONDS.toMillis(30); // Consider latency before renewLease might be called due to high load on current system
    public static final long CONNECTION_LOCK_TIMEOUT       = CONNECTION_DEEMED_DEAD * 5;
    
    public static final String NULL_STRING = "mmm.jedison.null";
    public static final byte[] NULL_BYTES  = JedisonByter.toBytes(NULL_STRING); // This value is used by us to send null values to Redis. If sent, then we can read it at redis and decide for a null. If we recieve we are able to go back to a null.
    
    public final JedisonClientBare   bare;
    public final JedisonClientBinary binary;
    public final JedisonClientText   text;

    protected final JedisonConfig<?>  config;
    private   final JSpaceJedison     space;
    protected final JSpaceId          id;
    protected final JedisonSerializer serializer;
    protected final JedisonPoolBase   pool;
    protected final JedisonEvent      linear, concurrent;

    protected final JMap<JSpaceId, JedisonTracker> trackers;
    private   final JedisonTracker                 tracker;
    private   final ExecutorService                executor;
    
    
    public Jedison(JedisonConfig<?> config) {
        Jedison backup = JEDISON.get();
        
        // withJedison call won't work with constructors and final, so we have to manually try finally backup
        try {
            set(this);

            this.config = config;
            
            this.space    = newSpace();
            this.executor = newExecutor();

            {
                this.bare   = newBare();
                this.binary = newBinary();
                this.text   = newText();
            }

            this.id         = newId();
            this.serializer = newSerializer();
            this.pool       = newPool();

            {
                this.linear     = newLinear();
                this.concurrent = newConcurrent();
            }

            {
                this.trackers = newTrackers();
                this.tracker  = newTracker();
                trackers.put(id(), this.tracker);
            }

            async(() -> {
                while (true) {
                    try {
                        tracker.seen.set(Nanotime.$().offset());     // Update last seen on self
                    } catch (Throwable e) {
                        log.error(getClass(), e);
                    }

                    try {
                        // Iterate map entries
                        trackers.each((JedisonTracker tracker, JSpaceId id) -> {
                            long elapsed = TimeUnit.NANOSECONDS.toMillis(
                                Nanotime.$().elapsed( Is.Or(tracker.seen.get(), Time.EPOC_OFFSET) )
                            );

                            log.info( getClass(), "1. Analyzing tracker '", id, "' Elapsed: ", TimeUnit.MILLISECONDS.toSeconds(elapsed) );

                            if ( elapsed > (CONNECTION_DEEMED_DEAD + CONNECTION_DEEMED_DEAD_LEEWAY) ) {
                                // Ready to be destroyed. It can be put on the same line as if above, but easier to find like this!
                                if ( tracker.destroy() ) {
                                    log.info(getClass(), "3. Analyzing tracker '", id, "' Removing!");
                                    trackers.remove(id);
                                }
                            }
                        });
                    } catch (Throwable e) {
                        log.error(getClass(), e);
                    }

                    Threads.sleep(CONNECTION_DEEMED_DEAD);
                }
            });
        }
        finally {
            set(backup);
        }
    }
    
    public void async(Lambda.VE<? extends Exception> lambda) {
        executor.submit(runnable(lambda));
    }
    
    /**
     * This ensures the lambda to be used in a new thread will get the same jedison set for its ThreadLocal
     */
    public Lambda.V runnable(Lambda.VE<? extends Exception> lambda) {
        return () -> {
            try {
                Jedison.this.withJedison(lambda);
            } catch (Throwable e) {
                Runtimes.pauseIfShuttingDownAtomically();
                
                log.error(getClass(), e); throw Ex.runtime(e);
            }
        };
    }
    
    /////////////////////////////////////////////////////////////////////
    
    protected int parallelism() {
        return Integer.MAX_VALUE;
    }

    protected JSpaceJedison newSpace() {
        return config.newSpace(this);
    }
    
    protected ExecutorService newExecutor() {
        int parallism = Math.max(16, parallelism());   // Minimum 16 threads required to operate safely per instance
        
        return m.Executors.newFixedThreadPool("Jedison :: " + getClass() + " (" + Jedison.class + ") :: " + name(), parallism);
    }
    
    protected JSpaceId newId() {
        return config.newId(this);
    }

    protected JedisonSerializer newSerializer() {
        return config.newSerializer(this);
    }

    protected JedisonPoolBase newPool() {
        return config.newPool(this);
    }
    
    protected JedisonEvent newLinear() {
        return new JedisonEvent();
    }
    
    protected JedisonEvent newConcurrent() {
        return new JedisonEvent() {
            @Override protected int parallelism() {
                return Integer.MAX_VALUE;
            }
        };
    }

    protected JMap<JSpaceId, JedisonTracker> newTrackers() {
        return new JMap<>(new JSpaceMap(this.space(), "trackers"));
    }

    protected JedisonTracker newTracker() {
        return new JedisonTracker( id() );
    }

    protected JedisonClientText newText() {
        return () -> Jedison.this;
    }

    protected JedisonClientBinary newBinary() {
        return () -> Jedison.this;
    }

    protected JedisonClientBare newBare() {
        return () -> Jedison.this;
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    public static Jedis newJedis(String host, int port) {
        return new Jedis(host, port);
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    public static Jedison get() {
        Jedison instance = JEDISON.get();

        if ( instance == null) {
            throw new $JedisonNotAvailableException();
        }

        return instance;
    }

    private static void set(Jedison instance) {
        JEDISON.set(instance);
    }
    
    /**
     * Does not restore the previous version. Suitable
     */
    public Jedison withJedison() {
        Jedison backup = JEDISON.get();
        
        set(this);
        
        return backup;
    }
    public <E extends Exception> void withJedison(Lambda.VE<E> lambda) throws E {
        withJedison(lambda.R1E());
    }

    public <E extends Exception> void withJedison(Lambda.V1E<? super Jedison, E> lambda) throws E {
        withJedison(lambda.R1E());
    }
    
    public <R, E extends Exception> R withJedison(Lambda.RE<R, E> lambda) throws E {
        return withJedison(this, lambda.R1E());
    }

    public <R, E extends Exception> R withJedison(Lambda.R1E<R, ? super Jedison, E> lambda) throws E {
        return withJedison(this, lambda);
    }
    
    private <R, E extends Exception> R withJedison(Jedison instance, Lambda.R1E<R, ? super Jedison, E> lambda) throws E {
        Jedison backup = JEDISON.get();
        try {
            set(instance);   // set this on thread
            
            return lambda.call(instance);
        }
        finally {
            set(backup);    // restore the original one
        }
    }
    
    

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    public JSpaceId id() {
        return id;
    }

    public JSpaceJedison space() {
        return space;
    }

    public String name() {
        return space().name();
    }

    public JedisonSerializer serializer() {
        return serializer;
    }

    public JedisonTracker tracker() {
        return tracker;
    }

    @Override
    public Jedison jedison() {
        return this;
    }

    @Override
    public <E extends Exception, R> R withPool(Lambda.R1E<R, Jedis, E> lambda, int attempts, boolean log) throws E {
        return pool.withPool(lambda, attempts, log);
    }
}

