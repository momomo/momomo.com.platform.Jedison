package m.jedison;

public interface JedisonSerializerThroughJedison extends JedisonSerializer, JedisonDeclaration {
    
    /**
     * Can not be made to be overridable since this will affect serializiblity of certain things.
     */
    private JedisonSerializer serializer() {
        return jedison().serializer();
    }
    
    @Override
    default Object $deserializeVal$(byte[] bytes) throws Exception {
        return serializer().$deserializeVal$(bytes);
    }
    
    @Override
    default byte[] $serializeVal$(Object object) throws Exception {
        return serializer().$serializeVal$(object);
    }
    
}
