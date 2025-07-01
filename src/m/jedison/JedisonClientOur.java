package m.jedison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.Is;
import m.Lambda;
import m.Numbers;
import m.instance.Id;

import redis.clients.jedis.Protocol;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.StreamPendingEntry;
import redis.clients.jedis.params.SetParams;

public interface JedisonClientOur extends JedisonSerializerThroughJedison {
    
    private JedisonClientBare bare() {
        return jedison().bare;
    }
    
    private JedisonClientBinary binary() {
        return jedison().binary;
    }
    
    private JedisonClientText text() {
        return jedison().text;
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default byte[] xadd(CharSequence name, byte[] key, byte[] val) {
        Map<byte[], byte[]> hash = new HashMap<>();
        
        hash.put(key, val);
        
        byte[] bytes = xadd(name, hash);
        
        return bytes;
    }
    
    default byte[] xadd(CharSequence name, Map<byte[], byte[]> hash) {
        return xadd(serializeKey(name), hash);
    }
    
    private byte[] xadd(byte[] name, Map<byte[], byte[]> hash) {
        return binary().xadd(name, new byte[]{Protocol.ASTERISK_BYTE}, hash, Long.MAX_VALUE, false);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default long lpush(CharSequence name, Object val) {
        return lpush(name, serializeVal(val));
    }
    
    default long lpush(CharSequence name, byte[] val) {
        return lpush(serializeKey(name), val);
    }
    
    private long lpush(byte[] name, byte[] val) {
        return Numbers.toLong(
            binary().lpush(
                name, val
            )
        );
    }
    
    default long rpush(CharSequence name, Object val) {
        return rpush(name, serializeVal(val));
    }
    
    default long rpush(CharSequence name, byte[] val) {
        return rpush(serializeKey(name), val);
    }
    
    private long rpush(byte[] name, byte[] val) {
        return Numbers.toLong(
            binary().rpush(
                name, val
            )
        );
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default byte[] lindex(CharSequence name, long index) {
        return lindex(serializeKey(name), index);
        
    }
    
    private byte[] lindex(byte[] name, long index) {
        return binary().lindex(name, index);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default List<byte[]> lrange(CharSequence name, long from, long to) {
        return lrange(serializeKey(name), from, to);
    }
    
    private List<byte[]> lrange(byte[] name, long from, long to) {
        return binary().lrange(name, from, to);
    }
    
    default <E extends Exception> void lrange(CharSequence name, long from, long to, Lambda.R2E<Boolean, byte[], Long, E> lambda) throws E {
        lrange(serializeKey(name), from, to, lambda);
    }
    
    private <E extends Exception> void lrange(byte[] name, long from, long to, Lambda.R2E<Boolean, byte[], Long, E> lambda) throws E {
        List<byte[]> values = lrange(name, from, to);
        
        long i = -1;
        for (byte[] value : values) {
            if (Is.False(lambda.call(value, ++i))) {
                return;
            }
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default long llen(CharSequence name) {
        return llen(serializeKey(name));
    }
    
    default long llen(byte[] key) {
        return Numbers.toLong(binary().llen(key));
    }
    
    /////////////////////////////////////////////////////////////////////
    
    
    default Long incr(CharSequence name) {
        return Numbers.toLong( binary().incr(serializeKey(name)) );
    }
    default Long incr(CharSequence name, long by) {
            return Numbers.toLong( binary().incrBy(serializeKey(name), by) );
    }
    default Long decr(CharSequence name) {
        return Numbers.toLong( binary().decr(serializeKey(name)) );
    }
    default Long decr(CharSequence name, long by) {
        return Numbers.toLong( binary().decrBy(serializeKey(name), by) );
    }
    
    
    /////////////////////////////////////////////////////////////////////
    
    default String set(CharSequence name, Object val) {
        return set(name, val, null);
    }
    
    default String set(CharSequence name, byte[] val) {
        return set(name, val, null);
    }
    
    default String set(CharSequence name, Object val, SetParams params) {
        return set(name, serializeVal(val), params);
    }
    
    default String set(CharSequence name, byte[] val, SetParams params) {
        return set(serializeKey(name), val, params);
    }
    
    private String set(byte[] key, byte[] val, SetParams params) {
        if (params == null) {
            return binary().set(key, val);
        }
        
        return binary().set(key, val, params);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default byte[] get(CharSequence name) {
        return get(serializeKey(name));
    }
    
    default byte[] get(byte[] key) {
        return binary().get(key);
    }
    
    default byte[] getSet(CharSequence name, Object val) {
        return getSet(name, serializeVal(val));
    }
    
    default byte[] getSet(CharSequence name, byte[] val) {
        return getSet(serializeKey(name), val);
    }
    
    default byte[] getSet(byte[] key, byte[] val) {
        return binary().getSet(key, val);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default Long del(CharSequence name) {
        if ( false ) {
            return text().del(name.toString()); // TODO, remove use of text()
        }
        else {
            return del(serializeKey(name));
        }
    }
    
    default Long del(byte[] key) {
        return binary().del(key);
    }
    
    /////////////////////////////////////////////////////////////////////
    default boolean hexists(CharSequence name, byte[] key) {
        return Is.True( binary().hexists(serializeKey(name), key) );
    }
    /////////////////////////////////////////////////////////////////////
    
    default Long hset(CharSequence name, Object key, Object val) {
        return hset(serializeKey(name), serializeVal(key), serializeVal(val));
    }
    
    default Long hset(CharSequence name, byte[] key, byte[] val) {
        return hset(serializeKey(name), key, val);
    }
    
    private Long hset(byte[] name, byte[] key, Object val) {
        return hset(name, key, serializeVal(val));
    }
    
    private Long hset(byte[] name, byte[] key, byte[] val) {
        return binary().hset(name, key, val);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default byte[] hget(CharSequence name, Object key) {
        return hget(serializeKey(name), serializeVal(key));
    }
    
    default byte[] hget(CharSequence name, byte[] key) {
        return hget(serializeKey(name), key);
    }
    
    private byte[] hget(byte[] name, Object key) {
        return hget(name, serializeVal(key));
    }
    
    private byte[] hget(byte[] name, byte[] key) {
        return binary().hget(name, key);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default Long hdel(CharSequence name, Object val) {
        return hdel(serializeKey(name), serializeVal(val));
    }
    
    private Long hdel(byte[] name, byte[] val) {
        return binary().hdel(name, val);
    }
    
    default Map<byte[], byte[]> hgetAll(String name) {
        return hgetAll(serializeKey(name));
    }
    
    private Map<byte[], byte[]> hgetAll(byte[] name) {
        return binary().hgetAll(name);
    }
    
    default long hlen(CharSequence name) {
        return hlen(serializeKey(name));
    }
    
    private long hlen(byte[] name) {
        return binary().hlen(name);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default void expire(CharSequence name, int timeout) {
        expire(serializeKey(name), timeout);
    }
    
    private void expire(byte[] name, int timeout) {
        binary().expire(name, timeout);
    }
    
    
    
    
    
    
    
    
    
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    
    default <K, V> List<JStreamMessageRecievedGroupRead<K, V>> xclaim(byte[] name, byte[] group, byte[] consumer, long minIdleTime, long newIdleTime, int retries, boolean force, List<StreamPendingEntry> pendings) {
        byte[][] array = new byte[pendings.size()][];
        int i = -1; while ( ++i < array.length ) {
            array[i] = serializeKey( pendings.get(i).getID().toString() );
        }
        
        return xclaim(name, group, consumer, minIdleTime, newIdleTime, retries, force, array);
    }
    default <K, V> List<JStreamMessageRecievedGroupRead<K, V>> xclaim(byte[] name, byte[] group, byte[] consumer, long minIdleTime, long newIdleTime, int retries, boolean force, byte[] ... ids) {
        List<?> entries = binary().xclaim(
            name,
            group,
            consumer,
            minIdleTime,
            newIdleTime,
            retries,
            force,
            ids
        );
        
        List<JStreamMessageRecievedGroupRead<K, V>> list = new ArrayList<>(entries.size());
        for (Object entry : entries) {
            list.add( toMessageRecieved((List<?>) entry) );
        }
        
        return list;
    }
    
    default <K, V> List<JStreamMessageRecievedGroupRead<K, V>> xreadGroup(byte[] name, byte[] group, byte[] consumer, int count, long block, boolean noAck, JStreamMessageSentGroupRead command) {
        return xreadGroup(group, consumer, count, block, noAck, entry(name, command));
    }
    
    default <K, V> List<JStreamMessageRecievedGroupRead<K, V>> xreadGroup(byte[] group, byte[] consumer, int count, long block, boolean noAck, Map<byte[], byte[]> streams) {
        List<?> entries = binary().xreadGroup(
            group,
            consumer,
            count,
            block,
            noAck,
            streams
        );
    
        List<JStreamMessageRecievedGroupRead<K, V>> list = new ArrayList<>();
        for (Object entryy : entries) {
            List<?> entry = (List<?>) entryy;
        
            // Jedison.Global.message.queue.mail.process.queue.stream
            // name()
            byte[]  n      = (byte[] ) entry.get(0);
        
            List<?> b      = (List<?>) entry.get(1);
            List<?> c      = (List<?>) b.get(0);
    
            list.add( toMessageRecieved(c) );
        }
        
        return list;
    }
    
    private Map<byte[], byte[]> entry(byte[] name, JStreamMessageSentGroupRead command) {
        Map<byte[], byte[]> bhash = new HashMap<>();
        bhash.put(
            name,
            serializeKey(command.toString())
        );
        return bhash;
    }
    
    private <K, V> JStreamMessageRecievedGroupRead<K, V> toMessageRecieved(List<?> c) {
        // message-id -> 1626820486260-0
        byte[]  id = (byte[]) c.get(0);
        
        // message, key
        List<?> e  = (List<?>) c.get(1);
        
        // group
        // Jedison.Global.message.queue.mail.process.queue.stream.a.group.claim.key
        byte[]  k  = (byte[]) e.get(0);
        
        // consumer
        // Jedison.Global#1e9e75c5-c817-4abd-b08b-1343e1414f0e#1#.consumer.stream.queue#5#
        byte[] v   = (byte[]) e.get(1);
    
        return new JStreamMessageRecievedGroupRead<>(new StreamEntryID(deserializeKey(id)), (K) deserializeVal(k), (V) deserializeVal(v));
    }
    
    default List<StreamPendingEntry> xpending(byte[] name, byte[] group, byte[] start, byte[] end, int count, byte[] consumer) {
        List<?> entries = jedison().binary.xpending(
            name,
            group,
            start,
            end,
            count,
            consumer
        );
    
        List<StreamPendingEntry> list = new ArrayList<>(entries.size());
        for (Object entryy : entries) {
            List<?> c  = (List<?>) entryy;
        
            // message-id -> 1626820486260-0
            byte[]  id = (byte[]) c.get(0);
        
            // consumer
            // Jedison.Store#6e2ac6a3-d50b-40a8-ab6d-8db36709334d#4#.consumer.stream.queue#5#
            byte[] v             = (byte[]) c.get(1);
            long   idleTime      = (long  ) c.get(2);
            long   deliveredTime = (long  ) c.get(3);
        
            list.add(
                new StreamPendingEntry(
                    new StreamEntryID(deserializeKey(id)),
                    deserializeKey(v),
                    idleTime,
                    deliveredTime
                )
            );
        }
    
        return list;
    }
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Does not check the previous value.
     *
     * Always sets but also publishes event and extends expire time.
     */
    default Object mmm_hset(JedisonEvent.OutgoingEvent.Hset outgoing, long expires) {
        String script = """
            local name    = KEYS[1]
            local key     = KEYS[2]
            
            local val     = ARGV[1]
            local expires = ARGV[2]
            local channel = ARGV[3]
            local message = ARGV[4]
            
            redis.call('hset', name, key, val)
            
            redis.call('publish', channel, message)
            
            redis.call('expire', name, expires)
            
            return 1
        """;
        
        Object returns = binary().eval(
            JedisonByter.toBytes(script),
            2,
            // KEYS
            serializeKey(outgoing.name), outgoing.key,
            // ARGV
            outgoing.val, serializePrimitive(expires), serializeKey(outgoing.channel()), serializeVal(outgoing.message())
        );
        
        return returns;
    }
    
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Checks the previous val is equal to old before writing.
     *
     * @param expires in seconds. Note that the expires is to update expiry for the entire map, not this key
     */
    default Object mmm_hset_aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa(CharSequence owner, CharSequence name, long expires, byte[] key, byte[] val, byte[] old) {
        
        if ( false ) {
            String s = """
                def count_types(conn, start, end):
                    counts = count_types_lua(keys=['events'], args=[start, end])
                    return json.loads(counts)
                    
                count_types_lua = conn.register_script('''
                local counts = {}
                local ids = redis.call('ZRANGEBYSCORE', KEYS[1], ARGV[1], ARGV[2])
                for i, id in ipairs(ids) do
                    local type = redis.call('HGET', 'event:' .. id, 'type')
                    counts[type] = (counts[type] or 0) + 1
                end
                
                return cjson.encode(counts)
                ''')
            """;
        }
        String script = """
                local name    = KEYS[1]
                local key     = KEYS[2]
                
                local val     = ARGV[1]
                local old     = ARGV[2]
                local expires = ARGV[3]
                local channel = ARGV[4]
                local message = ARGV[5]
                
                
                
                local current = redis.call('hget', name, key)
                
                -- Note that current == false means current == nil here. Redis never returns nil but false.
                -- See https://stackoverflow.com/a/21365943/961018
                if (current == false and old == 'mmm.jedison.null') or (current == old) then
                    redis.call('hset', name, key, val);
                    
                    redis.call('publish', channel, message);
                    
                    redis.call('expire', name, expires)
                    
                    return val
                else
                    return current
                end
            """;
        
        JedisonEvent.OutgoingEvent.Hset outgoing = new JedisonEvent.OutgoingEvent.Hset(
            new Id(),
            jedison().id(),
            owner,
            name,
            key,
            val
        );
        
        Object returns = binary().eval(
            JedisonByter.toBytes(script),
            
            2,
            
            // KEYS
            serializeKey(name.toString()), key,
            
            // ARGV
            val, old, serializePrimitive(expires), serializeKey(outgoing.channel()), serializeVal(outgoing.message())
        );
        
        if ( returns instanceof byte[] ) {
            returns = deserializeVal((byte[]) returns);
        }
        
        return returns;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
