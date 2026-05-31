package m.jedison;

import lombok.experimental.Delegate;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class JedisonPoolApacheConfig extends JedisonPoolBaseConfig {
    @Delegate
    protected final GenericObjectPoolConfig<Jedis> delegate;

    protected JedisonPoolApacheConfig() {
        this.delegate = new GenericObjectPoolConfig<>();
    }
}
