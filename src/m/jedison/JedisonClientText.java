package m.jedison;

import redis.clients.jedis.Module;
import redis.clients.jedis.*;
import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.params.*;
import redis.clients.jedis.util.Slowlog;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JedisonClientText extends JedisonDeclaration {

    default String set(String key, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.set(key, value);
        });
    }

    default String set(String key, String value, SetParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.set(key, value, params);
        });
    }

    default String get(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.get(key);
        });
    }

    default Long exists(String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.exists(keys);
        });
    }

    default Boolean exists(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.exists(key);
        });
    }

    default Long del(String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.del(keys);
        });
    }

    default Long del(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.del(key);
        });
    }

    default Long unlink(String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.unlink(keys);
        });
    }

    default Long unlink(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.unlink(key);
        });
    }

    default String type(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.type(key);
        });
    }

    default Set<String> keys(String pattern) {
        return jedison().withPool((jedis)-> {
            return jedis.keys(pattern);
        });
    }

    default String randomKey() {
        return jedison().withPool((jedis)-> {
            return jedis.randomKey();
        });
    }

    default String rename(String oldkey, String newkey) {
        return jedison().withPool((jedis)-> {
            return jedis.rename(oldkey, newkey);
        });
    }

    default Long renamenx(String oldkey, String newkey) {
        return jedison().withPool((jedis)-> {
            return jedis.renamenx(oldkey, newkey);
        });
    }

    default Long expire(String key, int seconds) {
        return jedison().withPool((jedis)-> {
            return jedis.expire(key, seconds);
        });
    }

    default Long expireAt(String key, long unixTime) {
        return jedison().withPool((jedis)-> {
            return jedis.expireAt(key, unixTime);
        });
    }

    default Long ttl(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.ttl(key);
        });
    }

    default Long touch(String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.touch(keys);
        });
    }

    default Long touch(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.touch(key);
        });
    }

    default Long move(String key, int dbIndex) {
        return jedison().withPool((jedis)-> {
            return jedis.move(key, dbIndex);
        });
    }

    default String getSet(String key, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.getSet(key, value);
        });
    }

    default List<String> mget(String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.mget(keys);
        });
    }

    default Long setnx(String key, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.setnx(key, value);
        });
    }

    default String setex(String key, int seconds, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.setex(key, seconds, value);
        });
    }

    default String mset(String... keysvalues) {
        return jedison().withPool((jedis)-> {
            return jedis.mset(keysvalues);
        });
    }

    default Long msetnx(String... keysvalues) {
        return jedison().withPool((jedis)-> {
            return jedis.msetnx(keysvalues);
        });
    }

    default Long decrBy(String key, long decrement) {
        return jedison().withPool((jedis)-> {
            return jedis.decrBy(key, decrement);
        });
    }

    default Long decr(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.decr(key);
        });
    }

    default Long incrBy(String key, long increment) {
        return jedison().withPool((jedis)-> {
            return jedis.incrBy(key, increment);
        });
    }

    default Double incrByFloat(String key, double increment) {
        return jedison().withPool((jedis)-> {
            return jedis.incrByFloat(key, increment);
        });
    }

    default Long incr(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.incr(key);
        });
    }

    default Long append(String key, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.append(key, value);
        });
    }

    default String substr(String key, int start, int end) {
        return jedison().withPool((jedis)-> {
            return jedis.substr(key, start, end);
        });
    }

    default Long hset(String key, String field, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.hset(key, field, value);
        });
    }

    default Long hset(String key, Map<String, String> hash) {
        return jedison().withPool((jedis)-> {
            return jedis.hset(key, hash);
        });
    }

    default String hget(String key, String field) {
        return jedison().withPool((jedis)-> {
            return jedis.hget(key, field);
        });
    }

    default Long hsetnx(String key, String field, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.hsetnx(key, field, value);
        });
    }

    default String hmset(String key, Map<String, String> hash) {
        return jedison().withPool((jedis)-> {
            return jedis.hmset(key, hash);
        });
    }

    default List<String> hmget(String key, String... fields) {
        return jedison().withPool((jedis)-> {
            return jedis.hmget(key, fields);
        });
    }

    default Long hincrBy(String key, String field, long value) {
        return jedison().withPool((jedis)-> {
            return jedis.hincrBy(key, field, value);
        });
    }

    default Double hincrByFloat(String key, String field, double value) {
        return jedison().withPool((jedis)-> {
            return jedis.hincrByFloat(key, field, value);
        });
    }

    default Boolean hexists(String key, String field) {
        return jedison().withPool((jedis)-> {
            return jedis.hexists(key, field);
        });
    }

    default Long hdel(String key, String... fields) {
        return jedison().withPool((jedis)-> {
            return jedis.hdel(key, fields);
        });
    }

    default Long hlen(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.hlen(key);
        });
    }

    default Set<String> hkeys(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.hkeys(key);
        });
    }

    default List<String> hvals(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.hvals(key);
        });
    }

    default Map<String, String> hgetAll(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.hgetAll(key);
        });
    }

    default Long rpush(String key, String... strings) {
        return jedison().withPool((jedis)-> {
            return jedis.rpush(key, strings);
        });
    }

    default Long lpush(String key, String... strings) {
        return jedison().withPool((jedis)-> {
            return jedis.lpush(key, strings);
        });
    }

    default Long llen(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.llen(key);
        });
    }

    default List<String> lrange(String key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.lrange(key, start, stop);
        });
    }

    default String ltrim(String key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.ltrim(key, start, stop);
        });
    }

    default String lindex(String key, long index) {
        return jedison().withPool((jedis)-> {
            return jedis.lindex(key, index);
        });
    }

    default String lset(String key, long index, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.lset(key, index, value);
        });
    }

    default Long lrem(String key, long count, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.lrem(key, count, value);
        });
    }

    default String lpop(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.lpop(key);
        });
    }

    default String rpop(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.rpop(key);
        });
    }

    default String rpoplpush(String srckey, String dstkey) {
        return jedison().withPool((jedis)-> {
            return jedis.rpoplpush(srckey, dstkey);
        });
    }

    default Long sadd(String key, String... members) {
        return jedison().withPool((jedis)-> {
            return jedis.sadd(key, members);
        });
    }

    default Set<String> smembers(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.smembers(key);
        });
    }

    default Long srem(String key, String... members) {
        return jedison().withPool((jedis)-> {
            return jedis.srem(key, members);
        });
    }

    default String spop(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.spop(key);
        });
    }

    default Set<String> spop(String key, long count) {
        return jedison().withPool((jedis)-> {
            return jedis.spop(key, count);
        });
    }

    default Long smove(String srckey, String dstkey, String member) {
        return jedison().withPool((jedis)-> {
            return jedis.smove(srckey, dstkey, member);
        });
    }

    default Long scard(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.scard(key);
        });
    }

    default Boolean sismember(String key, String member) {
        return jedison().withPool((jedis)-> {
            return jedis.sismember(key, member);
        });
    }

    default Set<String> sinter(String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sinter(keys);
        });
    }

    default Long sinterstore(String dstkey, String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sinterstore(dstkey, keys);
        });
    }

    default Set<String> sunion(String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sunion(keys);
        });
    }

    default Long sunionstore(String dstkey, String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sunionstore(dstkey, keys);
        });
    }

    default Set<String> sdiff(String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sdiff(keys);
        });
    }

    default Long sdiffstore(String dstkey, String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.sdiffstore(dstkey, keys);
        });
    }

    default String srandmember(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.srandmember(key);
        });
    }

    default List<String> srandmember(String key, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.srandmember(key, count);
        });
    }

    default Long zadd(String key, double score, String member) {
        return jedison().withPool((jedis)-> {
            return jedis.zadd(key, score, member);
        });
    }

    default Long zadd(String key, double score, String member, ZAddParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.zadd(key, score, member, params);
        });
    }

    default Long zadd(String key, Map<String, Double> scoreMembers) {
        return jedison().withPool((jedis)-> {
            return jedis.zadd(key, scoreMembers);
        });
    }

    default Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.zadd(key, scoreMembers, params);
        });
    }

    default Set<String> zrange(String key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.zrange(key, start, stop);
        });
    }

    default Long zrem(String key, String... members) {
        return jedison().withPool((jedis)-> {
            return jedis.zrem(key, members);
        });
    }

    default Double zincrby(String key, double increment, String member) {
        return jedison().withPool((jedis)-> {
            return jedis.zincrby(key, increment, member);
        });
    }

    default Double zincrby(String key, double increment, String member, ZIncrByParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.zincrby(key, increment, member, params);
        });
    }

    default Long zrank(String key, String member) {
        return jedison().withPool((jedis)-> {
            return jedis.zrank(key, member);
        });
    }

    default Long zrevrank(String key, String member) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrank(key, member);
        });
    }

    default Set<String> zrevrange(String key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrange(key, start, stop);
        });
    }

    default Set<Tuple> zrangeWithScores(String key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeWithScores(key, start, stop);
        });
    }

    default Set<Tuple> zrevrangeWithScores(String key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeWithScores(key, start, stop);
        });
    }

    default Long zcard(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.zcard(key);
        });
    }

    default Double zscore(String key, String member) {
        return jedison().withPool((jedis)-> {
            return jedis.zscore(key, member);
        });
    }

    default Tuple zpopmax(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.zpopmax(key);
        });
    }

    default Set<Tuple> zpopmax(String key, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zpopmax(key, count);
        });
    }

    default Tuple zpopmin(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.zpopmin(key);
        });
    }

    default Set<Tuple> zpopmin(String key, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zpopmin(key, count);
        });
    }

    default String watch(String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.watch(keys);
        });
    }

    default List<String> sort(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.sort(key);
        });
    }

    default List<String> sort(String key, SortingParams sortingParameters) {
        return jedison().withPool((jedis)-> {
            return jedis.sort(key, sortingParameters);
        });
    }

    default List<String> blpop(int timeout, String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.blpop(timeout, keys);
        });
    }

    default List<String> blpop(String... args) {
        return jedison().withPool((jedis)-> {
            return jedis.blpop(args);
        });
    }

    default List<String> brpop(String... args) {
        return jedison().withPool((jedis)-> {
            return jedis.brpop(args);
        });
    }

    default Long sort(String key, SortingParams sortingParameters, String dstkey) {
        return jedison().withPool((jedis)-> {
            return jedis.sort(key, sortingParameters, dstkey);
        });
    }

    default Long sort(String key, String dstkey) {
        return jedison().withPool((jedis)-> {
            return jedis.sort(key, dstkey);
        });
    }

    default List<String> brpop(int timeout, String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.brpop(timeout, keys);
        });
    }

    default Long zcount(String key, double min, double max) {
        return jedison().withPool((jedis)-> {
            return jedis.zcount(key, min, max);
        });
    }

    default Long zcount(String key, String min, String max) {
        return jedison().withPool((jedis)-> {
            return jedis.zcount(key, min, max);
        });
    }

    default Set<String> zrangeByScore(String key, double min, double max) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScore(key, min, max);
        });
    }

    default Set<String> zrangeByScore(String key, String min, String max) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScore(key, min, max);
        });
    }

    default Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScore(key, min, max, offset, count);
        });
    }

    default Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScore(key, min, max, offset, count);
        });
    }

    default Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScoreWithScores(key, min, max);
        });
    }

    default Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScoreWithScores(key, min, max);
        });
    }

    default Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        });
    }

    default Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        });
    }

    default Set<String> zrevrangeByScore(String key, double max, double min) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScore(key, max, min);
        });
    }

    default Set<String> zrevrangeByScore(String key, String max, String min) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScore(key, max, min);
        });
    }

    default Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        });
    }

    default Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        });
    }

    default Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        });
    }

    default Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        });
    }

    default Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        });
    }

    default Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        });
    }

    default Long zremrangeByRank(String key, long start, long stop) {
        return jedison().withPool((jedis)-> {
            return jedis.zremrangeByRank(key, start, stop);
        });
    }

    default Long zremrangeByScore(String key, double min, double max) {
        return jedison().withPool((jedis)-> {
            return jedis.zremrangeByScore(key, min, max);
        });
    }

    default Long zremrangeByScore(String key, String min, String max) {
        return jedison().withPool((jedis)-> {
            return jedis.zremrangeByScore(key, min, max);
        });
    }

    default Long zunionstore(String dstkey, String... sets) {
        return jedison().withPool((jedis)-> {
            return jedis.zunionstore(dstkey, sets);
        });
    }

    default Long zunionstore(String dstkey, ZParams params, String... sets) {
        return jedison().withPool((jedis)-> {
            return jedis.zunionstore(dstkey, params, sets);
        });
    }

    default Long zinterstore(String dstkey, String... sets) {
        return jedison().withPool((jedis)-> {
            return jedis.zinterstore(dstkey, sets);
        });
    }

    default Long zinterstore(String dstkey, ZParams params, String... sets) {
        return jedison().withPool((jedis)-> {
            return jedis.zinterstore(dstkey, params, sets);
        });
    }

    default Long zlexcount(String key, String min, String max) {
        return jedison().withPool((jedis)-> {
            return jedis.zlexcount(key, min, max);
        });
    }

    default Set<String> zrangeByLex(String key, String min, String max) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByLex(key, min, max);
        });
    }

    default Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrangeByLex(key, min, max, offset, count);
        });
    }

    default Set<String> zrevrangeByLex(String key, String max, String min) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByLex(key, max, min);
        });
    }

    default Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.zrevrangeByLex(key, max, min, offset, count);
        });
    }

    default Long zremrangeByLex(String key, String min, String max) {
        return jedison().withPool((jedis)-> {
            return jedis.zremrangeByLex(key, min, max);
        });
    }

    default Long strlen(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.strlen(key);
        });
    }

    default Long lpushx(String key, String... string) {
        return jedison().withPool((jedis)-> {
            return jedis.lpushx(key, string);
        });
    }

    default Long persist(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.persist(key);
        });
    }

    default Long rpushx(String key, String... string) {
        return jedison().withPool((jedis)-> {
            return jedis.rpushx(key, string);
        });
    }

    default String echo(String string) {
        return jedison().withPool((jedis)-> {
            return jedis.echo(string);
        });
    }

    default Long linsert(String key, ListPosition where, String pivot, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.linsert(key, where, pivot, value);
        });
    }

    default String brpoplpush(String source, String destination, int timeout) {
        return jedison().withPool((jedis)-> {
            return jedis.brpoplpush(source, destination, timeout);
        });
    }

    default Boolean setbit(String key, long offset, boolean value) {
        return jedison().withPool((jedis)-> {
            return jedis.setbit(key, offset, value);
        });
    }

    default Boolean setbit(String key, long offset, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.setbit(key, offset, value);
        });
    }

    default Boolean getbit(String key, long offset) {
        return jedison().withPool((jedis)-> {
            return jedis.getbit(key, offset);
        });
    }

    default Long setrange(String key, long offset, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.setrange(key, offset, value);
        });
    }

    default String getrange(String key, long startOffset, long endOffset) {
        return jedison().withPool((jedis)-> {
            return jedis.getrange(key, startOffset, endOffset);
        });
    }

    default Long bitpos(String key, boolean value) {
        return jedison().withPool((jedis)-> {
            return jedis.bitpos(key, value);
        });
    }

    default Long bitpos(String key, boolean value, BitPosParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.bitpos(key, value, params);
        });
    }

    default List<String> configGet(String pattern) {
        return jedison().withPool((jedis)-> {
            return jedis.configGet(pattern);
        });
    }

    default String configSet(String parameter, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.configSet(parameter, value);
        });
    }

    default void subscribe(JedisPubSub jedisPubSub, String... channels) {
        jedison().withPool((jedis)-> {
            jedis.subscribe(jedisPubSub, channels);
        });
    }

    default Long publish(String channel, String message) {
        return jedison().withPool((jedis)-> {
            return jedis.publish(channel, message);
        });
    }

    default void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        jedison().withPool((jedis)-> {
            jedis.psubscribe(jedisPubSub, patterns);
        });
    }

    default Object eval(String script, int keyCount, String... params) {
        return jedison().withPool((jedis)-> {
            return jedis.eval(script, keyCount, params);
        });
    }

    default Object eval(String script, List<String> keys, List<String> args) {
        return jedison().withPool((jedis)-> {
            return jedis.eval(script, keys, args);
        });
    }

    default Object eval(String script) {
        return jedison().withPool((jedis)-> {
            return jedis.eval(script);
        });
    }

    default Object evalsha(String sha1) {
        return jedison().withPool((jedis)-> {
            return jedis.evalsha(sha1);
        });
    }

    default Object evalsha(String sha1, List<String> keys, List<String> args) {
        return jedison().withPool((jedis)-> {
            return jedis.evalsha(sha1, keys, args);
        });
    }

    default Object evalsha(String sha1, int keyCount, String... params) {
        return jedison().withPool((jedis)-> {
            return jedis.evalsha(sha1, keyCount, params);
        });
    }

    default Boolean scriptExists(String sha1) {
        return jedison().withPool((jedis)-> {
            return jedis.scriptExists(sha1);
        });
    }

    default List<Boolean> scriptExists(String... sha1) {
        return jedison().withPool((jedis)-> {
            return jedis.scriptExists(sha1);
        });
    }

    default String scriptLoad(String script) {
        return jedison().withPool((jedis)-> {
            return jedis.scriptLoad(script);
        });
    }

    default List<Slowlog> slowlogGet() {
        return jedison().withPool((jedis)-> {
            return jedis.slowlogGet();
        });
    }

    default List<Slowlog> slowlogGet(long entries) {
        return jedison().withPool((jedis)-> {
            return jedis.slowlogGet(entries);
        });
    }

    default Long objectRefcount(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.objectRefcount(key);
        });
    }

    default String objectEncoding(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.objectEncoding(key);
        });
    }

    default Long objectIdletime(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.objectIdletime(key);
        });
    }

    default List<String> objectHelp() {
        return jedison().withPool((jedis)-> {
            return jedis.objectHelp();
        });
    }

    default Long objectFreq(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.objectFreq(key);
        });
    }

    default Long bitcount(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.bitcount(key);
        });
    }

    default Long bitcount(String key, long start, long end) {
        return jedison().withPool((jedis)-> {
            return jedis.bitcount(key, start, end);
        });
    }

    default Long bitop(BitOP op, String destKey, String... srcKeys) {
        return jedison().withPool((jedis)-> {
            return jedis.bitop(op, destKey, srcKeys);
        });
    }

    default List<Map<String, String>> sentinelMasters() {
        return jedison().withPool((jedis)-> {
            return jedis.sentinelMasters();
        });
    }

    default List<String> sentinelGetMasterAddrByName(String masterName) {
        return jedison().withPool((jedis)-> {
            return jedis.sentinelGetMasterAddrByName(masterName);
        });
    }

    default Long sentinelReset(String pattern) {
        return jedison().withPool((jedis)-> {
            return jedis.sentinelReset(pattern);
        });
    }

    default List<Map<String, String>> sentinelSlaves(String masterName) {
        return jedison().withPool((jedis)-> {
            return jedis.sentinelSlaves(masterName);
        });
    }

    default String sentinelFailover(String masterName) {
        return jedison().withPool((jedis)-> {
            return jedis.sentinelFailover(masterName);
        });
    }

    default String sentinelMonitor(String masterName, String ip, int port, int quorum) {
        return jedison().withPool((jedis)-> {
            return jedis.sentinelMonitor(masterName, ip, port, quorum);
        });
    }

    default String sentinelRemove(String masterName) {
        return jedison().withPool((jedis)-> {
            return jedis.sentinelRemove(masterName);
        });
    }

    default String sentinelSet(String masterName, Map<String, String> parameterMap) {
        return jedison().withPool((jedis)-> {
            return jedis.sentinelSet(masterName, parameterMap);
        });
    }

    default byte[] dump(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.dump(key);
        });
    }

    default String restore(String key, int ttl, byte[] serializedValue) {
        return jedison().withPool((jedis)-> {
            return jedis.restore(key, ttl, serializedValue);
        });
    }

    default String restoreReplace(String key, int ttl, byte[] serializedValue) {
        return jedison().withPool((jedis)-> {
            return jedis.restoreReplace(key, ttl, serializedValue);
        });
    }

    default Long pexpire(String key, long milliseconds) {
        return jedison().withPool((jedis)-> {
            return jedis.pexpire(key, milliseconds);
        });
    }

    default Long pexpireAt(String key, long millisecondsTimestamp) {
        return jedison().withPool((jedis)-> {
            return jedis.pexpireAt(key, millisecondsTimestamp);
        });
    }

    default Long pttl(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.pttl(key);
        });
    }

    default String psetex(String key, long milliseconds, String value) {
        return jedison().withPool((jedis)-> {
            return jedis.psetex(key, milliseconds, value);
        });
    }

    default String clientKill(String ipPort) {
        return jedison().withPool((jedis)-> {
            return jedis.clientKill(ipPort);
        });
    }

    default String clientGetname() {
        return jedison().withPool((jedis)-> {
            return jedis.clientGetname();
        });
    }

    default String clientList() {
        return jedison().withPool((jedis)-> {
            return jedis.clientList();
        });
    }

    default String clientSetname(String name) {
        return jedison().withPool((jedis)-> {
            return jedis.clientSetname(name);
        });
    }

    default String migrate(String host, int port, String key, int destinationDb, int timeout) {
        return jedison().withPool((jedis)-> {
            return jedis.migrate(host, port, key, destinationDb, timeout);
        });
    }

    default String migrate(String host, int port, int destinationDB, int timeout, MigrateParams params, String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.migrate(host, port, destinationDB, timeout, params, keys);
        });
    }

    default ScanResult<String> scan(String cursor) {
        return jedison().withPool((jedis)-> {
            return jedis.scan(cursor);
        });
    }

    default ScanResult<String> scan(String cursor, ScanParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.scan(cursor, params);
        });
    }

    default ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return jedison().withPool((jedis)-> {
            return jedis.hscan(key, cursor);
        });
    }

    default ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.hscan(key, cursor, params);
        });
    }

    default ScanResult<String> sscan(String key, String cursor) {
        return jedison().withPool((jedis)-> {
            return jedis.sscan(key, cursor);
        });
    }

    default ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.sscan(key, cursor, params);
        });
    }

    default ScanResult<Tuple> zscan(String key, String cursor) {
        return jedison().withPool((jedis)-> {
            return jedis.zscan(key, cursor);
        });
    }

    default ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        return jedison().withPool((jedis)-> {
            return jedis.zscan(key, cursor, params);
        });
    }

    default String clusterNodes() {
        return jedison().withPool((jedis)-> {
            return jedis.clusterNodes();
        });
    }

    default String readonly() {
        return jedison().withPool((jedis)-> {
            return jedis.readonly();
        });
    }

    default String clusterMeet(String ip, int port) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterMeet(ip, port);
        });
    }

    default String clusterReset(ClusterReset resetType) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterReset(resetType);
        });
    }

    default String clusterAddSlots(int... slots) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterAddSlots(slots);
        });
    }

    default String clusterDelSlots(int... slots) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterDelSlots(slots);
        });
    }

    default String clusterInfo() {
        return jedison().withPool((jedis)-> {
            return jedis.clusterInfo();
        });
    }

    default List<String> clusterGetKeysInSlot(int slot, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterGetKeysInSlot(slot, count);
        });
    }

    default String clusterSetSlotNode(int slot, String nodeId) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterSetSlotNode(slot, nodeId);
        });
    }

    default String clusterSetSlotMigrating(int slot, String nodeId) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterSetSlotMigrating(slot, nodeId);
        });
    }

    default String clusterSetSlotImporting(int slot, String nodeId) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterSetSlotImporting(slot, nodeId);
        });
    }

    default String clusterSetSlotStable(int slot) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterSetSlotStable(slot);
        });
    }

    default String clusterForget(String nodeId) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterForget(nodeId);
        });
    }

    default String clusterFlushSlots() {
        return jedison().withPool((jedis)-> {
            return jedis.clusterFlushSlots();
        });
    }

    default Long clusterKeySlot(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterKeySlot(key);
        });
    }

    default Long clusterCountKeysInSlot(int slot) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterCountKeysInSlot(slot);
        });
    }

    default String clusterSaveConfig() {
        return jedison().withPool((jedis)-> {
            return jedis.clusterSaveConfig();
        });
    }

    default String clusterReplicate(String nodeId) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterReplicate(nodeId);
        });
    }

    default List<String> clusterSlaves(String nodeId) {
        return jedison().withPool((jedis)-> {
            return jedis.clusterSlaves(nodeId);
        });
    }

    default String clusterFailover() {
        return jedison().withPool((jedis)-> {
            return jedis.clusterFailover();
        });
    }

    default List<Object> clusterSlots() {
        return jedison().withPool((jedis)-> {
            return jedis.clusterSlots();
        });
    }

    default String asking() {
        return jedison().withPool((jedis)-> {
            return jedis.asking();
        });
    }

    default List<String> pubsubChannels(String pattern) {
        return jedison().withPool((jedis)-> {
            return jedis.pubsubChannels(pattern);
        });
    }

    default Long pubsubNumPat() {
        return jedison().withPool((jedis)-> {
            return jedis.pubsubNumPat();
        });
    }

    default Map<String, String> pubsubNumSub(String... channels) {
        return jedison().withPool((jedis)-> {
            return jedis.pubsubNumSub(channels);
        });
    }

    default void close() {
        jedison().withPool((jedis)-> {
            jedis.close();
        });
    }

    default void setDataSource(JedisPoolAbstract jedisPool) {
        jedison().withPool((jedis)-> {
            jedis.setDataSource(jedisPool);
        });
    }

    default Long pfadd(String key, String... elements) {
        return jedison().withPool((jedis)-> {
            return jedis.pfadd(key, elements);
        });
    }

    default long pfcount(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.pfcount(key);
        });
    }

    default long pfcount(String... keys) {
        return jedison().withPool((jedis)-> {
            return jedis.pfcount(keys);
        });
    }

    default String pfmerge(String destkey, String... sourcekeys) {
        return jedison().withPool((jedis)-> {
            return jedis.pfmerge(destkey, sourcekeys);
        });
    }

    default List<String> blpop(int timeout, String key) {
        return jedison().withPool((jedis)-> {
            return jedis.blpop(timeout, key);
        });
    }

    default List<String> brpop(int timeout, String key) {
        return jedison().withPool((jedis)-> {
            return jedis.brpop(timeout, key);
        });
    }

    default Long geoadd(String key, double longitude, double latitude, String member) {
        return jedison().withPool((jedis)-> {
            return jedis.geoadd(key, longitude, latitude, member);
        });
    }

    default Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return jedison().withPool((jedis)-> {
            return jedis.geoadd(key, memberCoordinateMap);
        });
    }

    default Double geodist(String key, String member1, String member2) {
        return jedison().withPool((jedis)-> {
            return jedis.geodist(key, member1, member2);
        });
    }

    default Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return jedison().withPool((jedis)-> {
            return jedis.geodist(key, member1, member2, unit);
        });
    }

    default List<String> geohash(String key, String... members) {
        return jedison().withPool((jedis)-> {
            return jedis.geohash(key, members);
        });
    }

    default List<GeoCoordinate> geopos(String key, String... members) {
        return jedison().withPool((jedis)-> {
            return jedis.geopos(key, members);
        });
    }

    default List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return jedison().withPool((jedis)-> {
            return jedis.georadius(key, longitude, latitude, radius, unit);
        });
    }

    default List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusReadonly(key, longitude, latitude, radius, unit);
        });
    }

    default List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return jedison().withPool((jedis)-> {
            return jedis.georadius(key, longitude, latitude, radius, unit, param);
        });
    }

    default List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusReadonly(key, longitude, latitude, radius, unit, param);
        });
    }

    default List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusByMember(key, member, radius, unit);
        });
    }

    default List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusByMemberReadonly(key, member, radius, unit);
        });
    }

    default List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusByMember(key, member, radius, unit, param);
        });
    }

    default List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return jedison().withPool((jedis)-> {
            return jedis.georadiusByMemberReadonly(key, member, radius, unit, param);
        });
    }

    default String moduleLoad(String path) {
        return jedison().withPool((jedis)-> {
            return jedis.moduleLoad(path);
        });
    }

    default String moduleUnload(String name) {
        return jedison().withPool((jedis)-> {
            return jedis.moduleUnload(name);
        });
    }

    default List<Module> moduleList() {
        return jedison().withPool((jedis)-> {
            return jedis.moduleList();
        });
    }

    default String aclSetUser(String name) {
        return jedison().withPool((jedis)-> {
            return jedis.aclSetUser(name);
        });
    }

    default String aclSetUser(String name, String... params) {
        return jedison().withPool((jedis)-> {
            return jedis.aclSetUser(name, params);
        });
    }

    default Long aclDelUser(String name) {
        return jedison().withPool((jedis)-> {
            return jedis.aclDelUser(name);
        });
    }

    default AccessControlUser aclGetUser(String name) {
        return jedison().withPool((jedis)-> {
            return jedis.aclGetUser(name);
        });
    }

    default List<String> aclUsers() {
        return jedison().withPool((jedis)-> {
            return jedis.aclUsers();
        });
    }

    default List<String> aclList() {
        return jedison().withPool((jedis)-> {
            return jedis.aclList();
        });
    }

    default String aclWhoAmI() {
        return jedison().withPool((jedis)-> {
            return jedis.aclWhoAmI();
        });
    }

    default List<String> aclCat() {
        return jedison().withPool((jedis)-> {
            return jedis.aclCat();
        });
    }

    default List<String> aclCat(String category) {
        return jedison().withPool((jedis)-> {
            return jedis.aclCat(category);
        });
    }

    default String aclGenPass() {
        return jedison().withPool((jedis)-> {
            return jedis.aclGenPass();
        });
    }

    default List<Long> bitfield(String key, String... arguments) {
        return jedison().withPool((jedis)-> {
            return jedis.bitfield(key, arguments);
        });
    }

    default List<Long> bitfieldReadonly(String key, String... arguments) {
        return jedison().withPool((jedis)-> {
            return jedis.bitfieldReadonly(key, arguments);
        });
    }

    default Long hstrlen(String key, String field) {
        return jedison().withPool((jedis)-> {
            return jedis.hstrlen(key, field);
        });
    }

    default String memoryDoctor() {
        return jedison().withPool((jedis)-> {
            return jedis.memoryDoctor();
        });
    }

    default StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash) {
        return jedison().withPool((jedis)-> {
            return jedis.xadd(key, id, hash);
        });
    }

    default StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash, long maxLen, boolean approximateLength) {
        return jedison().withPool((jedis)-> {
            return jedis.xadd(key, id, hash, maxLen, approximateLength);
        });
    }

    default Long xlen(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.xlen(key);
        });
    }

    default List<StreamEntry> xrange(String key, StreamEntryID start, StreamEntryID end, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.xrange(key, start, end, count);
        });
    }

    default List<StreamEntry> xrevrange(String key, StreamEntryID end, StreamEntryID start, int count) {
        return jedison().withPool((jedis)-> {
            return jedis.xrevrange(key, end, start, count);
        });
    }

    default List<Map.Entry<String, List<StreamEntry>>> xread(int count, long block, Map.Entry<String, StreamEntryID>... streams) {
        return jedison().withPool((jedis)-> {
            return jedis.xread(count, block, streams);
        });
    }

    default long xack(String key, String group, StreamEntryID... ids) {
        return jedison().withPool((jedis)-> {
            return jedis.xack(key, group, ids);
        });
    }

    default String xgroupCreate(String key, String groupname, StreamEntryID id, boolean makeStream) {
        return jedison().withPool((jedis)-> {
            return jedis.xgroupCreate(key, groupname, id, makeStream);
        });
    }

    default String xgroupSetID(String key, String groupname, StreamEntryID id) {
        return jedison().withPool((jedis)-> {
            return jedis.xgroupSetID(key, groupname, id);
        });
    }

    default long xgroupDestroy(String key, String groupname) {
        return jedison().withPool((jedis)-> {
            return jedis.xgroupDestroy(key, groupname);
        });
    }

    default Long xgroupDelConsumer(String key, String groupname, String consumerName) {
        return jedison().withPool((jedis)-> {
            return jedis.xgroupDelConsumer(key, groupname, consumerName);
        });
    }

    default long xdel(String key, StreamEntryID... ids) {
        return jedison().withPool((jedis)-> {
            return jedis.xdel(key, ids);
        });
    }

    default long xtrim(String key, long maxLen, boolean approximateLength) {
        return jedison().withPool((jedis)-> {
            return jedis.xtrim(key, maxLen, approximateLength);
        });
    }

    default List<Map.Entry<String, List<StreamEntry>>> xreadGroup(String groupname, String consumer, int count, long block, boolean noAck, Map.Entry<String, StreamEntryID>... streams) {
        return jedison().withPool((jedis)-> {
            return jedis.xreadGroup(groupname, consumer, count, block, noAck, streams);
        });
    }

    default List<StreamPendingEntry> xpending(String key, String groupname, StreamEntryID start, StreamEntryID end, int count, String consumername) {
        return jedison().withPool((jedis)-> {
            return jedis.xpending(key, groupname, start, end, count, consumername);
        });
    }

    default List<StreamEntry> xclaim(String key, String group, String consumername, long minIdleTime, long newIdleTime, int retries, boolean force, StreamEntryID... ids) {
        return jedison().withPool((jedis)-> {
            return jedis.xclaim(key, group, consumername, minIdleTime, newIdleTime, retries, force, ids);
        });
    }

    default StreamInfo xinfoStream(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.xinfoStream(key);
        });
    }

    default List<StreamGroupInfo> xinfoGroup(String key) {
        return jedison().withPool((jedis)-> {
            return jedis.xinfoGroup(key);
        });
    }

    default List<StreamConsumersInfo> xinfoConsumers(String key, String group) {
        return jedison().withPool((jedis)-> {
            return jedis.xinfoConsumers(key, group);
        });
    }

    default Object sendCommand(ProtocolCommand cmd, String... args) {
        return jedison().withPool((jedis)-> {
            return jedis.sendCommand(cmd, args);
        });
    }


}
