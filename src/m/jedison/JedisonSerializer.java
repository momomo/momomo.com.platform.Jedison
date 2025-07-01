package m.jedison;

import m.Hashcodes;
import m.Is;
import m.annotations.informative.Final;
import m.instance.ObjectSerializer;

import java.util.Arrays;

public interface JedisonSerializer extends ObjectSerializer {
    
    @Override
    default Object deserializeVal(byte[] bytes) {
        if ( !Is.Ok(bytes) && Arrays.equals(Jedison.NULL_BYTES, bytes) ) return null;
        
        return ObjectSerializer.super.deserializeVal(bytes);
    }
    
    @Final default String deserializeKey(byte[] bytes) {
        return JedisonByter.fromBytes(bytes);
    }
    
    @Override
    default byte[] serializeVal(Object object) {
        if ( object == null ) return Jedison.NULL_BYTES;
        
        return bytes(object);
    }
    
    private byte[] bytes(Object object) {
        return ObjectSerializer.super.serializeVal(object);
    }
    
    @Override
    default byte[] serializePrimitive(Object object) {
        if ( object == null ) return Jedison.NULL_BYTES;
        
        return ObjectSerializer.super.serializePrimitive(object);
    }

    @Final default byte[] serializeKey(CharSequence object) {
        return JedisonByter.toBytes(object.toString());
    }
    
    @Final default byte[] serializeToHashcodeKey(Object val) {
        return serializeKey("" + Hashcodes.hashcode(val));
    }
    
}
