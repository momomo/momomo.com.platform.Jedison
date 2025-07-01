package m.jedison;

import m.instance.ObjectSerializerBytesXStream;

/**
 * @author Joseph S.
 */
public interface JedisonSerializerBytesXStream extends JedisonSerializer, ObjectSerializerBytesXStream {
    public static final JedisonSerializerBytesXStream $ = new JedisonSerializerBytesXStream(){};
}
