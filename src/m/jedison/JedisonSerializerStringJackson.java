package m.jedison;

import m.instance.ObjectSerializerStringJackson;

/**
 * @author Joseph S.
 * 
 * First line the classname is written. Then the json comes after.  
 */
@Deprecated public interface JedisonSerializerStringJackson extends JedisonSerializer, ObjectSerializerStringJackson {
    public static final JedisonSerializerStringJackson $ = new JedisonSerializerStringJackson(){};
}
