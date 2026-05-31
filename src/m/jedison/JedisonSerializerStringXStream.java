package m.jedison;

import m.instance.ObjectSerializerStringXStream;

/**
 * @author Joseph S.
 * 
 * First line the classname is written. Then the json comes after.  
 */
public interface JedisonSerializerStringXStream extends JedisonSerializer, ObjectSerializerStringXStream {
    public static final JedisonSerializerStringXStream $ = new JedisonSerializerStringXStream(){};
}
