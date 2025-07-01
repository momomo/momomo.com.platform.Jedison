package m.jedison;

import redis.clients.jedis.AccessControlUser;
import redis.clients.jedis.BinaryJedisPubSub;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Client;
import redis.clients.jedis.DebugParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.JedisMonitor;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.StreamConsumersInfo;
import redis.clients.jedis.StreamGroupInfo;
import redis.clients.jedis.StreamInfo;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.params.ClientKillParams;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JedisonClientBinary extends JedisonDeclaration {

    default String ping() {
        return jedison().withPool((jedis)-> {
            return jedis.ping();
        });
    }

    default byte[] ping(byte[] message) {
        return jedison().withPool((jedis)-> {
            return jedis.ping(message);
        });
    }

    default String set(byte[] key, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.set(key, value);
        });
    }

    default String set(byte[] key, byte[] value, SetParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.set(key, value, params);
        });
    }

    default byte[] get(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.get(key);
        });
    }

    default String quit() {
        return jedison().withPool((jedis)-> {
            return jedis.quit();
        });
    }

    default Long exists(byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.exists(keys);
        });
    }

    default Boolean exists(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.exists(key);
        });
    }

    default Long del(byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.del(keys);
        });
    }

    default Long del(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.del(key);
        });
    }

    default Long unlink(byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.unlink(keys);
        });
    }

    default Long unlink(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.unlink(key);
        });
    }

    default String type(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.type(key);
        });
    }

    default String flushDB() {
        return jedison().withPool((jedis)-> {
            return jedis.flushDB();
        });
    }

    default Set<byte[]> keys(byte[] pattern) {
        return jedison().withPool((jedis)-> {
            return jedis.keys(pattern);
        });
    }

    default byte[] randomBinaryKey() {
        return jedison().withPool((jedis)-> {
            return jedis.randomBinaryKey();
        });
    }

    default String rename(byte[] oldkey, byte[] newkey) {
        return jedison().withPool((jedis)-> {
            return jedis.rename(oldkey, newkey);
        });
    }

    default Long renamenx(byte[] oldkey, byte[] newkey) {
        return jedison().withPool((jedis)-> {
            return jedis.renamenx(oldkey, newkey);
        });
    }

    default Long dbSize() {
        return jedison().withPool((jedis)-> {
            return jedis.dbSize();
        });
    }

    default Long expire(byte[] key, int seconds) {
        return jedison().withPool((jedis)-> {
            return jedis.expire(key, seconds);
        });
    }

    default Long expireAt(byte[] key, long unixTime) {
        return jedison().withPool((jedis)-> {
            return jedis.expireAt(key, unixTime);
        });
    }

    default Long ttl(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.ttl(key);
        });
    }

    default Long touch(byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.touch(keys);
        });
    }

    default Long touch(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.touch(key);
        });
    }

    default String select(int index) {
        return jedison().withPool((jedis)-> {
            return jedis.select(index);
        });
    }

    default String swapDB(int index1, int index2) {
        return jedison().withPool((jedis)-> {
            return jedis.swapDB(index1, index2);
        });
    }

    default Long move(byte[] key, int dbIndex) {
        return jedison().withPool((jedis)-> {
            return jedis.move(key, dbIndex);
        });
    }

    default String flushAll() {
        return jedison().withPool((jedis)-> {
            return jedis.flushAll();
        });
    }

    default byte[] getSet(byte[] key, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.getSet(key, value);
        });
    }

    default List<byte[]> mget(byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.mget(keys);
        });
    }

    default Long setnx(byte[] key, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.setnx(key, value);
        });
    }

    default String setex(byte[] key, int seconds, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.setex(key, seconds, value);
        });
    }

    default String mset(byte[]... keysvalues) {
        return jedison().withPool((jedis)-> {
            return jedis.mset(keysvalues);
        });
    }

    default Long msetnx(byte[]... keysvalues) {
        return jedison().withPool((jedis)-> {
            return jedis.msetnx(keysvalues);
        });
    }

    default Long decrBy(byte[] key, long decrement) {
        return jedison().withPool((jedis)-> {
            return jedis.decrBy(key, decrement);
        });
    }

    default Long decr(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.decr(key);
        });
    }

    default Long incrBy(byte[] key, long increment) {
        return jedison().withPool((jedis)-> {
            return jedis.incrBy(key, increment);
        });
    }

    default Double incrByFloat(byte[] key, double increment) {
        return jedison().withPool((jedis)-> {
            return jedis.incrByFloat(key, increment);
        });
    }

    default Long incr(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.incr(key);
        });
    }

    default Long append(byte[] key, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.append(key, value);
        });
    }

    default byte[] substr(byte[] key, int start, int end) {
        return jedison().withPool((jedis)-> {
            return jedis.substr(key, start, end);
        });
    }

    default Long hset(byte[] key, byte[] field, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.hset(key, field, value);
        });
    }

    default Long hset(byte[] key, Map<byte[], byte[]> hash) {
        return jedison().withPool((jedis)-> {
            return jedis.hset(key, hash);
        });
    }

    default byte[] hget(byte[] key, byte[] field) {
        return jedison().withPool((jedis)-> {
            return jedis.hget(key, field);
        });
    }

    default Long hsetnx(byte[] key, byte[] field, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.hsetnx(key, field, value);
        });
    }

    default String hmset(byte[] key, Map<byte[], byte[]> hash) {
        return jedison().withPool((jedis)-> {
            return jedis.hmset(key, hash);
        });
    }

    default List<byte[]> hmget(byte[] key, byte[]... fields) {
        return jedison().withPool((jedis)-> {
            return jedis.hmget(key, fields);
        });
    }

    default Long hincrBy(byte[] key, byte[] field, long value) {
        return jedison().withPool((jedis)-> {
            return jedis.hincrBy(key, field, value);
        });
    }

    default Double hincrByFloat(byte[] key, byte[] field, double value) {
        return jedison().withPool((jedis)-> {
            return jedis.hincrByFloat(key, field, value);
        });
    }

    default Boolean hexists(byte[] key, byte[] field) {
        return jedison().withPool((jedis)-> {
            return jedis.hexists(key, field);
        });
    }

    default Long hdel(byte[] key, byte[]... fields) {
        return jedison().withPool((jedis)-> {
            return jedis.hdel(key, fields);
        });
    }

    default Long hlen(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.hlen(key);
        });
    }

    default Set<byte[]> hkeys(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.hkeys(key);
        });
    }

    default List<byte[]> hvals(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.hvals(key);
        });
    }

    default Map<byte[], byte[]> hgetAll(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.hgetAll(key);
        });
    }

    default Long rpush(byte[] key, byte[]... strings) {
        return jedison().withPool((jedis)-> {
            return jedis.rpush(key, strings);
        });
    }

    default Long lpush(byte[] key, byte[]... strings) {
        return jedison().withPool((jedis)-> {
            return jedis.lpush(key, strings);
        });
    }

    default Long llen(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.llen(key);
        });
    }

    default List<byte[]> lrange(byte[] key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.lrange(key, start, stop);
        });
    }

    default String ltrim(byte[] key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.ltrim(key, start, stop);
        });
    }

    default byte[] lindex(byte[] key, long index) {
        return jedison().withPool((jedis)-> {
            return jedis.lindex(key, index);
        });
    }

    default String lset(byte[] key, long index, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.lset(key, index, value);
        });
    }

    default Long lrem(byte[] key, long count, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.lrem(key, count, value);
        });
    }

    default byte[] lpop(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.lpop(key);
        });
    }

    default byte[] rpop(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.rpop(key);
        });
    }

    default byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
        return jedison().withPool((jedis)-> {
            return jedis.rpoplpush(srckey, dstkey);
        });
    }

    default Long sadd(byte[] key, byte[]... members) {
        return jedison().withPool((jedis)-> {
            return jedis.sadd(key, members);
        });
    }

    default Set<byte[]> smembers(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.smembers(key);
        });
    }

    default Long srem(byte[] key, byte[]... member) {
        return jedison().withPool((jedis)-> {
            return jedis.srem(key, member);
        });
    }

    default byte[] spop(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.spop(key);
        });
    }

    default Set<byte[]> spop(byte[] key, long count) {
        return jedison().withPool((jedis)-> {
            return jedis.spop(key, count);
        });
    }

    default Long smove(byte[] srckey, byte[] dstkey, byte[] member) {
        return jedison().withPool((jedis)-> {
            return jedis.smove(srckey, dstkey, member);
        });
    }

    default Long scard(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.scard(key);
        });
    }

    default Boolean sismember(byte[] key, byte[] member) {
        return jedison().withPool((jedis)-> {
            return jedis.sismember(key, member);
        });
    }

    default Set<byte[]> sinter(byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sinter(keys);
        });
    }

    default Long sinterstore(byte[] dstkey, byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sinterstore(dstkey, keys);
        });
    }

    default Set<byte[]> sunion(byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sunion(keys);
        });
    }

    default Long sunionstore(byte[] dstkey, byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sunionstore(dstkey, keys);
        });
    }

    default Set<byte[]> sdiff(byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sdiff(keys);
        });
    }

    default Long sdiffstore(byte[] dstkey, byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sdiffstore(dstkey, keys);
        });
    }

    default byte[] srandmember(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.srandmember(key);
        });
    }

    default List<byte[]> srandmember(byte[] key, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.srandmember(key, count);
        });
    }

    default Long zadd(byte[] key, double score, byte[] member) {
        return jedison().withPool((jedis)-> {
            return jedis.zadd(key, score, member);
        });
    }

    default Long zadd(byte[] key, double score, byte[] member, ZAddParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.zadd(key, score, member, params);
        });
    }

    default Long zadd(byte[] key, Map<byte[], Double> scoreMembers) {
        return jedison().withPool((jedis)-> {
            return jedis.zadd(key, scoreMembers);
        });
    }

    default Long zadd(byte[] key, Map<byte[], Double> scoreMembers, ZAddParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.zadd(key, scoreMembers, params);
        });
    }

    default Set<byte[]> zrange(byte[] key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.zrange(key, start, stop);
        });
    }

    default Long zrem(byte[] key, byte[]... members) {
        return jedison().withPool((jedis)-> {
            return jedis.zrem(key, members);
        });
    }

    default Double zincrby(byte[] key, double increment, byte[] member) {
        return jedison().withPool((jedis)-> {
            return jedis.zincrby(key, increment, member);
        });
    }

    default Double zincrby(byte[] key, double increment, byte[] member, ZIncrByParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.zincrby(key, increment, member, params);
        });
    }

    default Long zrank(byte[] key, byte[] member) {
        return jedison().withPool((jedis)-> {
            return jedis.zrank(key, member);
        });
    }

    default Long zrevrank(byte[] key, byte[] member) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrank(key, member);
        });
    }

    default Set<byte[]> zrevrange(byte[] key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrange(key, start, stop);
        });
    }

    default Set<Tuple> zrangeWithScores(byte[] key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeWithScores(key, start, stop);
        });
    }

    default Set<Tuple> zrevrangeWithScores(byte[] key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeWithScores(key, start, stop);
        });
    }

    default Long zcard(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.zcard(key);
        });
    }

    default Double zscore(byte[] key, byte[] member) {
        return jedison().withPool((jedis)-> {
            return jedis.zscore(key, member);
        });
    }

    default Tuple zpopmax(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.zpopmax(key);
        });
    }

    default Set<Tuple> zpopmax(byte[] key, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zpopmax(key, count);
        });
    }

    default Tuple zpopmin(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.zpopmin(key);
        });
    }

    default Set<Tuple> zpopmin(byte[] key, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zpopmin(key, count);
        });
    }

    default Transaction multi() {
        return jedison().withPool((jedis)-> {
            return jedis.multi();
        });
    }

    default void connect() {
        jedison().withPool((jedis)-> {
            jedis.connect();
        });
    }

    default void disconnect() {
        jedison().withPool((jedis)-> {
            jedis.disconnect();
        });
    }

    default void resetState() {
        jedison().withPool((jedis)-> {
            jedis.resetState();
        });
    }

    default String watch(byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.watch(keys);
        });
    }

    default String unwatch() {
        return jedison().withPool((jedis)-> {
            return jedis.unwatch();
        });
    }

    default List<byte[]> sort(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.sort(key);
        });
    }

    default List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
        return jedison().withPool((jedis)-> {
            return jedis.sort(key, sortingParameters);
        });
    }

    default List<byte[]> blpop(int timeout, byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.blpop(timeout, keys);
        });
    }

    default Long sort(byte[] key, SortingParams sortingParameters, byte[] dstkey) {
        return jedison().withPool((jedis)-> {
            return jedis.sort(key, sortingParameters, dstkey);
        });
    }

    default Long sort(byte[] key, byte[] dstkey) {
        return jedison().withPool((jedis)-> {
            return jedis.sort(key, dstkey);
        });
    }

    default List<byte[]> brpop(int timeout, byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.brpop(timeout, keys);
        });
    }

    default List<byte[]> blpop(byte[]... args) {
        return jedison().withPool((jedis)-> {
            return jedis.blpop(args);
        });
    }

    default List<byte[]> brpop(byte[]... args) {
        return jedison().withPool((jedis)-> {
            return jedis.brpop(args);
        });
    }

    default String auth(String password) {
        return jedison().withPool((jedis)-> {
            return jedis.auth(password);
        });
    }

    default String auth(String user, String password) {
        return jedison().withPool((jedis)-> {
            return jedis.auth(user, password);
        });
    }

    default Pipeline pipelined() {
        return jedison().withPool((jedis)-> {
            return jedis.pipelined();
        });
    }

    default Long zcount(byte[] key, double min, double max) {
        return jedison().withPool((jedis)-> {
            return jedis.zcount(key, min, max);
        });
    }

    default Long zcount(byte[] key, byte[] min, byte[] max) {
        return jedison().withPool((jedis)-> {
            return jedis.zcount(key, min, max);
        });
    }

    default Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScore(key, min, max);
        });
    }

    default Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScore(key, min, max);
        });
    }

    default Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScore(key, min, max, offset, count);
        });
    }

    default Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScore(key, min, max, offset, count);
        });
    }

    default Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScoreWithScores(key, min, max);
        });
    }

    default Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScoreWithScores(key, min, max);
        });
    }

    default Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        });
    }

    default Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        });
    }

    default Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScore(key, max, min);
        });
    }

    default Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScore(key, max, min);
        });
    }

    default Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        });
    }

    default Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        });
    }

    default Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        });
    }

    default Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        });
    }

    default Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        });
    }

    default Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        });
    }

    default Long zremrangeByRank(byte[] key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.zremrangeByRank(key, start, stop);
        });
    }

    default Long zremrangeByScore(byte[] key, double min, double max) {
        return jedison().withPool((jedis)-> {
            return jedis.zremrangeByScore(key, min, max);
        });
    }

    default Long zremrangeByScore(byte[] key, byte[] min, byte[] max) {
        return jedison().withPool((jedis)-> {
            return jedis.zremrangeByScore(key, min, max);
        });
    }

    default Long zunionstore(byte[] dstkey, byte[]... sets) {
        return jedison().withPool((jedis)-> {
            return jedis.zunionstore(dstkey, sets);
        });
    }

    default Long zunionstore(byte[] dstkey, ZParams params, byte[]... sets) {
        return jedison().withPool((jedis)-> {
            return jedis.zunionstore(dstkey, params, sets);
        });
    }

    default Long zinterstore(byte[] dstkey, byte[]... sets) {
        return jedison().withPool((jedis)-> {
            return jedis.zinterstore(dstkey, sets);
        });
    }

    default Long zinterstore(byte[] dstkey, ZParams params, byte[]... sets) {
        return jedison().withPool((jedis)-> {
            return jedis.zinterstore(dstkey, params, sets);
        });
    }

    default Long zlexcount(byte[] key, byte[] min, byte[] max) {
        return jedison().withPool((jedis)-> {
            return jedis.zlexcount(key, min, max);
        });
    }

    default Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByLex(key, min, max);
        });
    }

    default Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByLex(key, min, max, offset, count);
        });
    }

    default Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByLex(key, max, min);
        });
    }

    default Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByLex(key, max, min, offset, count);
        });
    }

    default Long zremrangeByLex(byte[] key, byte[] min, byte[] max) {
        return jedison().withPool((jedis)-> {
            return jedis.zremrangeByLex(key, min, max);
        });
    }

    default String save() {
        return jedison().withPool((jedis)-> {
            return jedis.save();
        });
    }

    default String bgsave() {
        return jedison().withPool((jedis)-> {
            return jedis.bgsave();
        });
    }

    default String bgrewriteaof() {
        return jedison().withPool((jedis)-> {
            return jedis.bgrewriteaof();
        });
    }

    default Long lastsave() {
        return jedison().withPool((jedis)-> {
            return jedis.lastsave();
        });
    }

    default String shutdown() {
        return jedison().withPool((jedis)-> {
            return jedis.shutdown();
        });
    }

    default String info() {
        return jedison().withPool((jedis)-> {
            return jedis.info();
        });
    }

    default String info(String section) {
        return jedison().withPool((jedis)-> {
            return jedis.info(section);
        });
    }

    default void monitor(JedisMonitor jedisMonitor) {
        jedison().withPool((jedis)-> {
            jedis.monitor(jedisMonitor);
        });
    }

    default String slaveof(String host, int port) {
        return jedison().withPool((jedis)-> {
            return jedis.slaveof(host, port);
        });
    }

    default String slaveofNoOne() {
        return jedison().withPool((jedis)-> {
            return jedis.slaveofNoOne();
        });
    }

    default List<byte[]> configGet(byte[] pattern) {
        return jedison().withPool((jedis)-> {
            return jedis.configGet(pattern);
        });
    }

    default String configResetStat() {
        return jedison().withPool((jedis)-> {
            return jedis.configResetStat();
        });
    }

    default String configRewrite() {
        return jedison().withPool((jedis)-> {
            return jedis.configRewrite();
        });
    }

    default byte[] configSet(byte[] parameter, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.configSet(parameter, value);
        });
    }

    default boolean isConnected() {
        return jedison().withPool((jedis)-> {
            return jedis.isConnected();
        });
    }

    default Long strlen(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.strlen(key);
        });
    }

    default void sync() {
        jedison().withPool((jedis)-> {
            jedis.sync();
        });
    }

    default Long lpushx(byte[] key, byte[]... string) {
        return jedison().withPool((jedis)-> {
            return jedis.lpushx(key, string);
        });
    }

    default Long persist(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.persist(key);
        });
    }

    default Long rpushx(byte[] key, byte[]... string) {
        return jedison().withPool((jedis)-> {
            return jedis.rpushx(key, string);
        });
    }

    default byte[] echo(byte[] string) {
        return jedison().withPool((jedis)-> {
            return jedis.echo(string);
        });
    }

    default Long linsert(byte[] key, ListPosition where, byte[] pivot, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.linsert(key, where, pivot, value);
        });
    }

    default String debug(DebugParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.debug(params);
        });
    }

    default Client getClient() {
        return jedison().withPool((jedis)-> {
            return JedisonClientBare.client(jedis);
        });
    }

    default byte[] brpoplpush(byte[] source, byte[] destination, int timeout) {
        return jedison().withPool((jedis)-> {
            return jedis.brpoplpush(source, destination, timeout);
        });
    }

    default Boolean setbit(byte[] key, long offset, boolean value) {
        return jedison().withPool((jedis)-> {
            return jedis.setbit(key, offset, value);
        });
    }

    default Boolean setbit(byte[] key, long offset, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.setbit(key, offset, value);
        });
    }

    default Boolean getbit(byte[] key, long offset) {
        return jedison().withPool((jedis)-> {
            return jedis.getbit(key, offset);
        });
    }

    default Long bitpos(byte[] key, boolean value) {
        return jedison().withPool((jedis)-> {
            return jedis.bitpos(key, value);
        });
    }

    default Long bitpos(byte[] key, boolean value, BitPosParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.bitpos(key, value, params);
        });
    }

    default Long setrange(byte[] key, long offset, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.setrange(key, offset, value);
        });
    }

    default byte[] getrange(byte[] key, long startOffset, long endOffset) {
        return jedison().withPool((jedis)-> {
            return jedis.getrange(key, startOffset, endOffset);
        });
    }

    default Long publish(byte[] channel, byte[] message) {
        return jedison().withPool((jedis)-> {
            return jedis.publish(channel, message);
        });
    }

    default void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels) {
        jedison().withPool((jedis)-> {
            jedis.subscribe(jedisPubSub, channels);
        });
    }

    default void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns) {
        jedison().withPool((jedis)-> {
            jedis.psubscribe(jedisPubSub, patterns);
        });
    }

    default int getDB() {
        return jedison().withPool((jedis)-> {
            return jedis.getDB();
        });
    }

    default Object eval(byte[] script, List<byte[]> keys, List<byte[]> args) {
        return jedison().withPool((jedis)-> {
            return jedis.eval(script, keys, args);
        });
    }

    default Object eval(byte[] script, byte[] keyCount, byte[]... params) {
        return jedison().withPool((jedis)-> {
            return jedis.eval(script, keyCount, params);
        });
    }

    default Object eval(byte[] script, int keyCount, byte[]... params) {
        return jedison().withPool((jedis)-> {
            return jedis.eval(script, keyCount, params);
        });
    }

    default Object eval(byte[] script) {
        return jedison().withPool((jedis)-> {
            return jedis.eval(script);
        });
    }

    default Object evalsha(byte[] sha1) {
        return jedison().withPool((jedis)-> {
            return jedis.evalsha(sha1);
        });
    }

    default Object evalsha(byte[] sha1, List<byte[]> keys, List<byte[]> args) {
        return jedison().withPool((jedis)-> {
            return jedis.evalsha(sha1, keys, args);
        });
    }

    default Object evalsha(byte[] sha1, int keyCount, byte[]... params) {
        return jedison().withPool((jedis)-> {
            return jedis.evalsha(sha1, keyCount, params);
        });
    }

    default String scriptFlush() {
        return jedison().withPool((jedis)-> {
            return jedis.scriptFlush();
        });
    }

    default Long scriptExists(byte[] sha1) {
        return jedison().withPool((jedis)-> {
            return jedis.scriptExists(sha1);
        });
    }

    default List<Long> scriptExists(byte[]... sha1) {
        return jedison().withPool((jedis)-> {
            return jedis.scriptExists(sha1);
        });
    }

    default byte[] scriptLoad(byte[] script) {
        return jedison().withPool((jedis)-> {
            return jedis.scriptLoad(script);
        });
    }

    default String scriptKill() {
        return jedison().withPool((jedis)-> {
            return jedis.scriptKill();
        });
    }

    default String slowlogReset() {
        return jedison().withPool((jedis)-> {
            return jedis.slowlogReset();
        });
    }

    default Long slowlogLen() {
        return jedison().withPool((jedis)-> {
            return jedis.slowlogLen();
        });
    }

    default List<Object> slowlogGetBinary() {
        return jedison().withPool((jedis)-> {
            return jedis.slowlogGetBinary();
        });
    }

    default List<Object> slowlogGetBinary(long entries) {
        return jedison().withPool((jedis)-> {
            return jedis.slowlogGetBinary(entries);
        });
    }

    default Long objectRefcount(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.objectRefcount(key);
        });
    }

    default byte[] objectEncoding(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.objectEncoding(key);
        });
    }

    default Long objectIdletime(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.objectIdletime(key);
        });
    }

    default List<byte[]> objectHelpBinary() {
        return jedison().withPool((jedis)-> {
            return jedis.objectHelpBinary();
        });
    }

    default Long objectFreq(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.objectFreq(key);
        });
    }

    default Long bitcount(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.bitcount(key);
        });
    }

    default Long bitcount(byte[] key, long start, long end) {
        return jedison().withPool((jedis)-> {
            return jedis.bitcount(key, start, end);
        });
    }

    default Long bitop(BitOP op, byte[] destKey, byte[]... srcKeys) {
        return jedison().withPool((jedis)-> {
            return jedis.bitop(op, destKey, srcKeys);
        });
    }

    default byte[] dump(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.dump(key);
        });
    }

    default String restore(byte[] key, int ttl, byte[] serializedValue) {
        return jedison().withPool((jedis)-> {
            return jedis.restore(key, ttl, serializedValue);
        });
    }

    default String restoreReplace(byte[] key, int ttl, byte[] serializedValue) {
        return jedison().withPool((jedis)-> {
            return jedis.restoreReplace(key, ttl, serializedValue);
        });
    }

    default Long pexpire(byte[] key, long milliseconds) {
        return jedison().withPool((jedis)-> {
            return jedis.pexpire(key, milliseconds);
        });
    }

    default Long pexpireAt(byte[] key, long millisecondsTimestamp) {
        return jedison().withPool((jedis)-> {
            return jedis.pexpireAt(key, millisecondsTimestamp);
        });
    }

    default Long pttl(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.pttl(key);
        });
    }

    default String psetex(byte[] key, long milliseconds, byte[] value) {
        return jedison().withPool((jedis)-> {
            return jedis.psetex(key, milliseconds, value);
        });
    }

    default byte[] memoryDoctorBinary() {
        return jedison().withPool((jedis)-> {
            return jedis.memoryDoctorBinary();
        });
    }

    default byte[] aclWhoAmIBinary() {
        return jedison().withPool((jedis)-> {
            return jedis.aclWhoAmIBinary();
        });
    }

    default byte[] aclGenPassBinary() {
        return jedison().withPool((jedis)-> {
            return jedis.aclGenPassBinary();
        });
    }

    default List<byte[]> aclListBinary() {
        return jedison().withPool((jedis)-> {
            return jedis.aclListBinary();
        });
    }

    default List<byte[]> aclUsersBinary() {
        return jedison().withPool((jedis)-> {
            return jedis.aclUsersBinary();
        });
    }

    default AccessControlUser aclGetUser(byte[] name) {
        return jedison().withPool((jedis)-> {
            return jedis.aclGetUser(name);
        });
    }

    default String aclSetUser(byte[] name) {
        return jedison().withPool((jedis)-> {
            return jedis.aclSetUser(name);
        });
    }

    default String aclSetUser(byte[] name, byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.aclSetUser(name, keys);
        });
    }

    default Long aclDelUser(byte[] name) {
        return jedison().withPool((jedis)-> {
            return jedis.aclDelUser(name);
        });
    }

    default List<byte[]> aclCatBinary() {
        return jedison().withPool((jedis)-> {
            return jedis.aclCatBinary();
        });
    }

    default List<byte[]> aclCat(byte[] category) {
        return jedison().withPool((jedis)-> {
            return jedis.aclCat(category);
        });
    }

    default String clientKill(byte[] ipPort) {
        return jedison().withPool((jedis)-> {
            return jedis.clientKill(ipPort);
        });
    }

    default String clientKill(String ip, int port) {
        return jedison().withPool((jedis)-> {
            return jedis.clientKill(ip, port);
        });
    }

    default Long clientKill(ClientKillParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.clientKill(params);
        });
    }

    default byte[] clientGetnameBinary() {
        return jedison().withPool((jedis)-> {
            return jedis.clientGetnameBinary();
        });
    }

    default byte[] clientListBinary() {
        return jedison().withPool((jedis)-> {
            return jedis.clientListBinary();
        });
    }

    default String clientSetname(byte[] name) {
        return jedison().withPool((jedis)-> {
            return jedis.clientSetname(name);
        });
    }

    default Long clientId() {
        return jedison().withPool((jedis)-> {
            return jedis.clientId();
        });
    }

    default String clientPause(long timeout) {
        return jedison().withPool((jedis)-> {
            return jedis.clientPause(timeout);
        });
    }

    default List<String> time() {
        return jedison().withPool((jedis)-> {
            return jedis.time();
        });
    }

    default String migrate(String host, int port, byte[] key, int destinationDb, int timeout) {
        return jedison().withPool((jedis)-> {
            return jedis.migrate(host, port, key, destinationDb, timeout);
        });
    }

    default String migrate(String host, int port, int destinationDB, int timeout, MigrateParams params, byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.migrate(host, port, destinationDB, timeout, params, keys);
        });
    }

    default Long waitReplicas(int replicas, long timeout) {
        return jedison().withPool((jedis)-> {
            return jedis.waitReplicas(replicas, timeout);
        });
    }

    default Long pfadd(byte[] key, byte[]... elements) {
        return jedison().withPool((jedis)-> {
            return jedis.pfadd(key, elements);
        });
    }

    default long pfcount(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.pfcount(key);
        });
    }

    default String pfmerge(byte[] destkey, byte[]... sourcekeys) {
        return jedison().withPool((jedis)-> {
            return jedis.pfmerge(destkey, sourcekeys);
        });
    }

    default Long pfcount(byte[]... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.pfcount(keys);
        });
    }

    default ScanResult<byte[]> scan(byte[] cursor) {
        return jedison().withPool((jedis)-> {
            return jedis.scan(cursor);
        });
    }

    default ScanResult<byte[]> scan(byte[] cursor, ScanParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.scan(cursor, params);
        });
    }

    default ScanResult<Map.Entry<byte[], byte[]>> hscan(byte[] key, byte[] cursor) {
        return jedison().withPool((jedis)-> {
            return jedis.hscan(key, cursor);
        });
    }

    default ScanResult<Map.Entry<byte[], byte[]>> hscan(byte[] key, byte[] cursor, ScanParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.hscan(key, cursor, params);
        });
    }

    default ScanResult<byte[]> sscan(byte[] key, byte[] cursor) {
        return jedison().withPool((jedis)-> {
            return jedis.sscan(key, cursor);
        });
    }

    default ScanResult<byte[]> sscan(byte[] key, byte[] cursor, ScanParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.sscan(key, cursor, params);
        });
    }

    default ScanResult<Tuple> zscan(byte[] key, byte[] cursor) {
        return jedison().withPool((jedis)-> {
            return jedis.zscan(key, cursor);
        });
    }

    default ScanResult<Tuple> zscan(byte[] key, byte[] cursor, ScanParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.zscan(key, cursor, params);
        });
    }

    default Long geoadd(byte[] key, double longitude, double latitude, byte[] member) {
        return jedison().withPool((jedis)-> {
            return jedis.geoadd(key, longitude, latitude, member);
        });
    }

    default Long geoadd(byte[] key, Map<byte[], GeoCoordinate> memberCoordinateMap) {
        return jedison().withPool((jedis)-> {
            return jedis.geoadd(key, memberCoordinateMap);
        });
    }

    default Double geodist(byte[] key, byte[] member1, byte[] member2) {
        return jedison().withPool((jedis)-> {
            return jedis.geodist(key, member1, member2);
        });
    }

    default Double geodist(byte[] key, byte[] member1, byte[] member2, GeoUnit unit) {
        return jedison().withPool((jedis)-> {
            return jedis.geodist(key, member1, member2, unit);
        });
    }

    default List<byte[]> geohash(byte[] key, byte[]... members) {
        return jedison().withPool((jedis)-> {
            return jedis.geohash(key, members);
        });
    }

    default List<GeoCoordinate> geopos(byte[] key, byte[]... members) {
        return jedison().withPool((jedis)-> {
            return jedis.geopos(key, members);
        });
    }

    default List<GeoRadiusResponse> georadius(byte[] key, double longitude, double latitude, double radius, GeoUnit unit) {
        return jedison().withPool((jedis)-> {
            return jedis.georadius(key, longitude, latitude, radius, unit);
        });
    }

    default List<GeoRadiusResponse> georadiusReadonly(byte[] key, double longitude, double latitude, double radius, GeoUnit unit) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusReadonly(key, longitude, latitude, radius, unit);
        });
    }

    default List<GeoRadiusResponse> georadius(byte[] key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return jedison().withPool((jedis)-> {
            return jedis.georadius(key, longitude, latitude, radius, unit, param);
        });
    }

    default List<GeoRadiusResponse> georadiusReadonly(byte[] key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusReadonly(key, longitude, latitude, radius, unit, param);
        });
    }

    default List<GeoRadiusResponse> georadiusByMember(byte[] key, byte[] member, double radius, GeoUnit unit) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusByMember(key, member, radius, unit);
        });
    }

    default List<GeoRadiusResponse> georadiusByMemberReadonly(byte[] key, byte[] member, double radius, GeoUnit unit) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusByMemberReadonly(key, member, radius, unit);
        });
    }

    default List<GeoRadiusResponse> georadiusByMember(byte[] key, byte[] member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusByMember(key, member, radius, unit, param);
        });
    }

    default List<GeoRadiusResponse> georadiusByMemberReadonly(byte[] key, byte[] member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusByMemberReadonly(key, member, radius, unit, param);
        });
    }

    default List<Long> bitfield(byte[] key, byte[]... arguments) {
        return jedison().withPool((jedis)-> {
            return jedis.bitfield(key, arguments);
        });
    }

    default List<Long> bitfieldReadonly(byte[] key, byte[]... arguments) {
        return jedison().withPool((jedis)-> {
            return jedis.bitfieldReadonly(key, arguments);
        });
    }

    default Long hstrlen(byte[] key, byte[] field) {
        return jedison().withPool((jedis)-> {
            return jedis.hstrlen(key, field);
        });
    }

    default List<byte[]> xread(int count, long block, Map<byte[], byte[]> streams) {
        return jedison().withPool((jedis)-> {
            return jedis.xread(count, block, streams);
        });
    }

    default List<byte[]> xreadGroup(byte[] groupname, byte[] consumer, int count, long block, boolean noAck, Map<byte[], byte[]> streams) {
        return jedison().withPool((jedis)-> {
            return jedis.xreadGroup(groupname, consumer, count, block, noAck, streams);
        });
    }

    default byte[] xadd(byte[] key, byte[] id, Map<byte[], byte[]> hash, long maxLen, boolean approximateLength) {
        return jedison().withPool((jedis)-> {
            return jedis.xadd(key, id, hash, maxLen, approximateLength);
        });
    }

    default Long xlen(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.xlen(key);
        });
    }

    default List<byte[]> xrange(byte[] key, byte[] start, byte[] end, long count) {
        return jedison().withPool((jedis)-> {
            return jedis.xrange(key, start, end, count);
        });
    }

    default List<byte[]> xrevrange(byte[] key, byte[] end, byte[] start, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.xrevrange(key, end, start, count);
        });
    }

    default Long xack(byte[] key, byte[] group, byte[]... ids) {
        return jedison().withPool((jedis)-> {
            return jedis.xack(key, group, ids);
        });
    }

    default String xgroupCreate(byte[] key, byte[] consumer, byte[] id, boolean makeStream) {
        return jedison().withPool((jedis)-> {
            return jedis.xgroupCreate(key, consumer, id, makeStream);
        });
    }

    default String xgroupSetID(byte[] key, byte[] consumer, byte[] id) {
        return jedison().withPool((jedis)-> {
            return jedis.xgroupSetID(key, consumer, id);
        });
    }

    default Long xgroupDestroy(byte[] key, byte[] consumer) {
        return jedison().withPool((jedis)-> {
            return jedis.xgroupDestroy(key, consumer);
        });
    }

    default Long xgroupDelConsumer(byte[] key, byte[] consumer, byte[] consumerName) {
        return jedison().withPool((jedis)-> {
            return jedis.xgroupDelConsumer(key, consumer, consumerName);
        });
    }

    default Long xdel(byte[] key, byte[]... ids) {
        return jedison().withPool((jedis)-> {
            return jedis.xdel(key, ids);
        });
    }

    default Long xtrim(byte[] key, long maxLen, boolean approximateLength) {
        return jedison().withPool((jedis)-> {
            return jedis.xtrim(key, maxLen, approximateLength);
        });
    }

    default List<byte[]> xpending(byte[] key, byte[] groupname, byte[] start, byte[] end, int count, byte[] consumername) {
        return jedison().withPool((jedis)-> {
            return jedis.xpending(key, groupname, start, end, count, consumername);
        });
    }

    default List<byte[]> xclaim(byte[] key, byte[] groupname, byte[] consumername, long minIdleTime, long newIdleTime, int retries, boolean force, byte[][] ids) {
        return jedison().withPool((jedis)-> {
            return jedis.xclaim(key, groupname, consumername, minIdleTime, newIdleTime, retries, force, ids);
        });
    }

    default Object sendCommand(ProtocolCommand cmd, byte[]... args) {
        return jedison().withPool((jedis)-> {
            return jedis.sendCommand(cmd, args);
        });
    }

    default StreamInfo xinfoStream(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.xinfoStream(key);
        });
    }

    default List<StreamGroupInfo> xinfoGroup(byte[] key) {
        return jedison().withPool((jedis)-> {
            return jedis.xinfoGroup(key);
        });
    }

    default List<StreamConsumersInfo> xinfoConsumers(byte[] key, byte[] group) {
        return jedison().withPool((jedis)-> {
            return jedis.xinfoConsumers(key, group);
        });
    }

    default Object sendCommand(ProtocolCommand cmd) {
        return jedison().withPool((jedis)-> {
            return jedis.sendCommand(cmd);
        });
    }



}
