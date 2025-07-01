package m.jedison;

import m.Is;
import redis.clients.jedis.util.SafeEncoder;

import java.util.Arrays;

public abstract class JedisonByter { private JedisonByter(){}
    
    public static String fromBytes(byte[] bytes) {
        if ( !Is.Ok(bytes) && Arrays.equals(Jedison.NULL_BYTES, bytes) ) return null;
        
        return SafeEncoder.encode(bytes);
    }
    
    public static byte[] toBytes(CharSequence characters) {
        if ( characters == null ) return Jedison.NULL_BYTES;
        
        return SafeEncoder.encode(characters.toString());
    }
}
