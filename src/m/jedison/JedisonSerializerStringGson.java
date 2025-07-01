package m.jedison;

import m.instance.ObjectSerializerStringGson;

/**
 * @author Joseph S.
 * 
 * First line the classname is written. Then the json comes after.  
 */
@Deprecated public interface JedisonSerializerStringGson extends JedisonSerializer, ObjectSerializerStringGson {
    public static final JedisonSerializerStringJackson $ = new JedisonSerializerStringJackson(){};
}
