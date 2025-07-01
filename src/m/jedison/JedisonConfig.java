package m.jedison;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import m.Is;
import m.annotations.informative.Overridable;
import m.jedison.spaces.JSpaceId;
import m.jedison.spaces.JSpaceJedison;

@Accessors(chain = true) @Setter(AccessLevel.PUBLIC) @Getter
public abstract class JedisonConfig<P extends JedisonPoolBaseConfig> {

    private       CharSequence module;
    private       String       url, password;
    private       int          port;
    private final P            poolConfig;
    
    protected JedisonConfig(P poolConfig) {
        this.poolConfig = poolConfig;
    }
    
    /////////////////////////////////////////////////////////////////////
    
    @Overridable
    protected JSpaceJedison newSpace(Jedison jedison) {
        return new JSpaceJedison(getModule());
    }
    
    @Overridable
    protected JSpaceId newId(Jedison jedison) {
        return new JSpaceId(jedison.space());
    }
    
    @Overridable
    protected JedisonSerializer newSerializer(Jedison jedison) {
        if ( true  ) return JedisonSerializerBytesXStream.$;
        if ( true  ) return JedisonSerializerStringXStream.$;
        if ( true  ) return JedisonSerializerBytesHessian.$;
        if ( true  ) return JedisonSerializerBytesRiver.$;
        
        return null;
        
    }
    
    protected abstract JedisonPoolBase newPool(Jedison jedison);
    
    @Overridable
    public Jedis newJedis() {
        Jedis jedis = Jedison.newJedis(getUrl(), getPort());
    
        if ( Is.Ok(password) ) {
            jedis.auth(password);
        }
    
        if ( true ) jedis.ping();
        
        return jedis;
    }

}
