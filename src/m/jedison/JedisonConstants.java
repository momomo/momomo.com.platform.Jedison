package m.jedison;

import redis.clients.jedis.StreamEntryID;

public class JedisonConstants {
    
    public static final StreamEntryID MINUS = new StreamEntryID() {
        @Override
        public String toString() {
            return "-";
        }
    };
    public static final StreamEntryID PLUS = new StreamEntryID() {
        @Override
        public String toString() {
            return "+";
        }
    };
    
}
