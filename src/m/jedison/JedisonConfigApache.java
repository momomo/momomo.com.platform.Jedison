package m.jedison;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import redis.clients.jedis.Protocol;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

@Accessors @Setter(AccessLevel.PUBLIC) @Getter
public class JedisonConfigApache extends JedisonConfig<JedisonPoolApacheConfig> {
    protected String           username    = null;
    protected String           client      = null;
    protected int              database    = Protocol.DEFAULT_DATABASE;
    protected int              timeout     = Protocol.DEFAULT_TIMEOUT;
    protected SSLSocketFactory sslFactory  = null;
    protected SSLParameters    sslParams   = null;
    protected HostnameVerifier sslVerifier = null;

    public JedisonConfigApache() {
        super(new JedisonPoolApacheConfig());
    }

    @Override
    protected JedisonPoolApache newPool(Jedison jedison) {
        return new JedisonPoolApache(this);
    }
}
