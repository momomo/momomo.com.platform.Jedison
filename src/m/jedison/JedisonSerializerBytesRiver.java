package m.jedison;

import m.instance.ObjectSerializerBytesRiver;

/**
 * @author Joseph S.
 */
public interface JedisonSerializerBytesRiver extends JedisonSerializer, ObjectSerializerBytesRiver {
    public static final JedisonSerializerBytesRiver $ = new JedisonSerializerBytesRiver(){};
}
