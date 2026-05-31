package m.jedison;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Client;
import redis.clients.jedis.DebugParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.params.ClientKillParams;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

public interface JedisonClientBare extends JedisonDeclaration {

    public static Client client(Jedis jedis) {
        return jedis.getClient();
    }

    default boolean isInMulti() {
        return jedison().withPool((jedis)-> {
            return client(jedis).isInMulti();
        });
    }

    default boolean isInWatch() {
        return jedison().withPool((jedis)-> {
            return client(jedis).isInWatch();
        });
    }

    default void setUser(String user) {
        jedison().withPool((jedis)-> {
            client(jedis).setUser(user);
        });
    }

    default void setPassword(String password) {
        jedison().withPool((jedis)-> {
            client(jedis).setPassword(password);
        });
    }

    default void setDb(int db) {
        jedison().withPool((jedis)-> {
            client(jedis).setDb(db);
        });
    }

    default void connect() {
        jedison().withPool((jedis)-> {
            client(jedis).connect();
        });
    }

    default void ping() {
        jedison().withPool((jedis)-> {
            client(jedis).ping();
        });
    }

    default void ping(byte[] message) {
        jedison().withPool((jedis)-> {
            client(jedis).ping(message);
        });
    }

    default void set(byte[] key, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).set(key, value);
        });
    }

    default void set(byte[] key, byte[] value, SetParams params) {
        jedison().withPool((jedis)-> {
            client(jedis).set(key, value, params);
        });
    }

    default void get(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).get(key);
        });
    }

    default void quit() {
        jedison().withPool((jedis)-> {
            client(jedis).quit();
        });
    }

    default void exists(byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).exists(keys);
        });
    }

    default void del(byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).del(keys);
        });
    }

    default void unlink(byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).unlink(keys);
        });
    }

    default void type(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).type(key);
        });
    }

    default void flushDB() {
        jedison().withPool((jedis)-> {
            client(jedis).flushDB();
        });
    }

    default void keys(byte[] pattern) {
        jedison().withPool((jedis)-> {
            client(jedis).keys(pattern);
        });
    }

    default void randomKey() {
        jedison().withPool((jedis)-> {
            client(jedis).randomKey();
        });
    }

    default void rename(byte[] oldkey, byte[] newkey) {
        jedison().withPool((jedis)-> {
            client(jedis).rename(oldkey, newkey);
        });
    }

    default void renamenx(byte[] oldkey, byte[] newkey) {
        jedison().withPool((jedis)-> {
            client(jedis).renamenx(oldkey, newkey);
        });
    }

    default void dbSize() {
        jedison().withPool((jedis)-> {
            client(jedis).dbSize();
        });
    }

    default void expire(byte[] key, int seconds) {
        jedison().withPool((jedis)-> {
            client(jedis).expire(key, seconds);
        });
    }

    default void expireAt(byte[] key, long unixTime) {
        jedison().withPool((jedis)-> {
            client(jedis).expireAt(key, unixTime);
        });
    }

    default void ttl(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).ttl(key);
        });
    }

    default void touch(byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).touch(keys);
        });
    }

    default void select(int index) {
        jedison().withPool((jedis)-> {
            client(jedis).select(index);
        });
    }

    default void swapDB(int index1, int index2) {
        jedison().withPool((jedis)-> {
            client(jedis).swapDB(index1, index2);
        });
    }

    default void move(byte[] key, int dbIndex) {
        jedison().withPool((jedis)-> {
            client(jedis).move(key, dbIndex);
        });
    }

    default void flushAll() {
        jedison().withPool((jedis)-> {
            client(jedis).flushAll();
        });
    }

    default void getSet(byte[] key, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).getSet(key, value);
        });
    }

    default void mget(byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).mget(keys);
        });
    }

    default void setnx(byte[] key, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).setnx(key, value);
        });
    }

    default void setex(byte[] key, int seconds, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).setex(key, seconds, value);
        });
    }

    default void mset(byte[]... keysvalues) {
        jedison().withPool((jedis)-> {
            client(jedis).mset(keysvalues);
        });
    }

    default void msetnx(byte[]... keysvalues) {
        jedison().withPool((jedis)-> {
            client(jedis).msetnx(keysvalues);
        });
    }

    default void decrBy(byte[] key, long decrement) {
        jedison().withPool((jedis)-> {
            client(jedis).decrBy(key, decrement);
        });
    }

    default void decr(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).decr(key);
        });
    }

    default void incrBy(byte[] key, long increment) {
        jedison().withPool((jedis)-> {
            client(jedis).incrBy(key, increment);
        });
    }

    default void incrByFloat(byte[] key, double increment) {
        jedison().withPool((jedis)-> {
            client(jedis).incrByFloat(key, increment);
        });
    }

    default void incr(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).incr(key);
        });
    }

    default void append(byte[] key, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).append(key, value);
        });
    }

    default void substr(byte[] key, int start, int end) {
        jedison().withPool((jedis)-> {
            client(jedis).substr(key, start, end);
        });
    }

    default void hset(byte[] key, byte[] field, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).hset(key, field, value);
        });
    }

    default void hset(byte[] key, Map<byte[], byte[]> hash) {
        jedison().withPool((jedis)-> {
            client(jedis).hset(key, hash);
        });
    }

    default void hget(byte[] key, byte[] field) {
        jedison().withPool((jedis)-> {
            client(jedis).hget(key, field);
        });
    }

    default void hsetnx(byte[] key, byte[] field, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).hsetnx(key, field, value);
        });
    }

    default void hmset(byte[] key, Map<byte[], byte[]> hash) {
        jedison().withPool((jedis)-> {
            client(jedis).hmset(key, hash);
        });
    }

    default void hmget(byte[] key, byte[]... fields) {
        jedison().withPool((jedis)-> {
            client(jedis).hmget(key, fields);
        });
    }

    default void hincrBy(byte[] key, byte[] field, long value) {
        jedison().withPool((jedis)-> {
            client(jedis).hincrBy(key, field, value);
        });
    }

    default void hexists(byte[] key, byte[] field) {
        jedison().withPool((jedis)-> {
            client(jedis).hexists(key, field);
        });
    }

    default void hdel(byte[] key, byte[]... fields) {
        jedison().withPool((jedis)-> {
            client(jedis).hdel(key, fields);
        });
    }

    default void hlen(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).hlen(key);
        });
    }

    default void hkeys(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).hkeys(key);
        });
    }

    default void hvals(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).hvals(key);
        });
    }

    default void hgetAll(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).hgetAll(key);
        });
    }

    default void rpush(byte[] key, byte[]... strings) {
        jedison().withPool((jedis)-> {
            client(jedis).rpush(key, strings);
        });
    }

    default void lpush(byte[] key, byte[]... strings) {
        jedison().withPool((jedis)-> {
            client(jedis).lpush(key, strings);
        });
    }

    default void llen(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).llen(key);
        });
    }

    default void lrange(byte[] key, long start, long stop) {
        jedison().withPool((jedis)-> {
            client(jedis).lrange(key, start, stop);
        });
    }

    default void ltrim(byte[] key, long start, long stop) {
        jedison().withPool((jedis)-> {
            client(jedis).ltrim(key, start, stop);
        });
    }

    default void lindex(byte[] key, long index) {
        jedison().withPool((jedis)-> {
            client(jedis).lindex(key, index);
        });
    }

    default void lset(byte[] key, long index, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).lset(key, index, value);
        });
    }

    default void lrem(byte[] key, long count, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).lrem(key, count, value);
        });
    }

    default void lpop(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).lpop(key);
        });
    }

    default void rpop(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).rpop(key);
        });
    }

    default void rpoplpush(byte[] srckey, byte[] dstkey) {
        jedison().withPool((jedis)-> {
            client(jedis).rpoplpush(srckey, dstkey);
        });
    }

    default void sadd(byte[] key, byte[]... members) {
        jedison().withPool((jedis)-> {
            client(jedis).sadd(key, members);
        });
    }

    default void smembers(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).smembers(key);
        });
    }

    default void srem(byte[] key, byte[]... members) {
        jedison().withPool((jedis)-> {
            client(jedis).srem(key, members);
        });
    }

    default void spop(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).spop(key);
        });
    }

    default void spop(byte[] key, long count) {
        jedison().withPool((jedis)-> {
            client(jedis).spop(key, count);
        });
    }

    default void smove(byte[] srckey, byte[] dstkey, byte[] member) {
        jedison().withPool((jedis)-> {
            client(jedis).smove(srckey, dstkey, member);
        });
    }

    default void scard(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).scard(key);
        });
    }

    default void sismember(byte[] key, byte[] member) {
        jedison().withPool((jedis)-> {
            client(jedis).sismember(key, member);
        });
    }

    default void sinter(byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).sinter(keys);
        });
    }

    default void sinterstore(byte[] dstkey, byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).sinterstore(dstkey, keys);
        });
    }

    default void sunion(byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).sunion(keys);
        });
    }

    default void sunionstore(byte[] dstkey, byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).sunionstore(dstkey, keys);
        });
    }

    default void sdiff(byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).sdiff(keys);
        });
    }

    default void sdiffstore(byte[] dstkey, byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).sdiffstore(dstkey, keys);
        });
    }

    default void srandmember(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).srandmember(key);
        });
    }

    default void zadd(byte[] key, double score, byte[] member) {
        jedison().withPool((jedis)-> {
            client(jedis).zadd(key, score, member);
        });
    }

    default void zadd(byte[] key, double score, byte[] member, ZAddParams params) {
        jedison().withPool((jedis)-> {
            client(jedis).zadd(key, score, member, params);
        });
    }

    default void zadd(byte[] key, Map<byte[], Double> scoreMembers) {
        jedison().withPool((jedis)-> {
            client(jedis).zadd(key, scoreMembers);
        });
    }

    default void zadd(byte[] key, Map<byte[], Double> scoreMembers, ZAddParams params) {
        jedison().withPool((jedis)-> {
            client(jedis).zadd(key, scoreMembers, params);
        });
    }

    default void zrange(byte[] key, long start, long stop) {
        jedison().withPool((jedis)-> {
            client(jedis).zrange(key, start, stop);
        });
    }

    default void zrem(byte[] key, byte[]... members) {
        jedison().withPool((jedis)-> {
            client(jedis).zrem(key, members);
        });
    }

    default void zincrby(byte[] key, double increment, byte[] member) {
        jedison().withPool((jedis)-> {
            client(jedis).zincrby(key, increment, member);
        });
    }

    default void zincrby(byte[] key, double increment, byte[] member, ZIncrByParams params) {
        jedison().withPool((jedis)-> {
            client(jedis).zincrby(key, increment, member, params);
        });
    }

    default void zrank(byte[] key, byte[] member) {
        jedison().withPool((jedis)-> {
            client(jedis).zrank(key, member);
        });
    }

    default void zrevrank(byte[] key, byte[] member) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrank(key, member);
        });
    }

    default void zrevrange(byte[] key, long start, long stop) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrange(key, start, stop);
        });
    }

    default void zrangeWithScores(byte[] key, long start, long stop) {
        jedison().withPool((jedis)-> {
            client(jedis).zrangeWithScores(key, start, stop);
        });
    }

    default void zrevrangeWithScores(byte[] key, long start, long stop) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrangeWithScores(key, start, stop);
        });
    }

    default void zcard(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).zcard(key);
        });
    }

    default void zscore(byte[] key, byte[] member) {
        jedison().withPool((jedis)-> {
            client(jedis).zscore(key, member);
        });
    }

    default void zpopmax(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).zpopmax(key);
        });
    }

    default void zpopmax(byte[] key, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).zpopmax(key, count);
        });
    }

    default void zpopmin(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).zpopmin(key);
        });
    }

    default void zpopmin(byte[] key, long count) {
        jedison().withPool((jedis)-> {
            client(jedis).zpopmin(key, count);
        });
    }

    default void multi() {
        jedison().withPool((jedis)-> {
            client(jedis).multi();
        });
    }

    default void discard() {
        jedison().withPool((jedis)-> {
            client(jedis).discard();
        });
    }

    default void exec() {
        jedison().withPool((jedis)-> {
            client(jedis).exec();
        });
    }

    default void watch(byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).watch(keys);
        });
    }

    default void unwatch() {
        jedison().withPool((jedis)-> {
            client(jedis).unwatch();
        });
    }

    default void sort(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).sort(key);
        });
    }

    default void sort(byte[] key, SortingParams sortingParameters) {
        jedison().withPool((jedis)-> {
            client(jedis).sort(key, sortingParameters);
        });
    }

    default void blpop(byte[][] args) {
        jedison().withPool((jedis)-> {
            client(jedis).blpop(args);
        });
    }

    default void blpop(int timeout, byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).blpop(timeout, keys);
        });
    }

    default void sort(byte[] key, SortingParams sortingParameters, byte[] dstkey) {
        jedison().withPool((jedis)-> {
            client(jedis).sort(key, sortingParameters, dstkey);
        });
    }

    default void sort(byte[] key, byte[] dstkey) {
        jedison().withPool((jedis)-> {
            client(jedis).sort(key, dstkey);
        });
    }

    default void brpop(byte[][] args) {
        jedison().withPool((jedis)-> {
            client(jedis).brpop(args);
        });
    }

    default void brpop(int timeout, byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).brpop(timeout, keys);
        });
    }

    default void auth(String password) {
        jedison().withPool((jedis)-> {
            client(jedis).auth(password);
        });
    }

    default void auth(String user, String password) {
        jedison().withPool((jedis)-> {
            client(jedis).auth(user, password);
        });
    }

    default void subscribe(byte[]... channels) {
        jedison().withPool((jedis)-> {
            client(jedis).subscribe(channels);
        });
    }

    default void publish(byte[] channel, byte[] message) {
        jedison().withPool((jedis)-> {
            client(jedis).publish(channel, message);
        });
    }

    default void unsubscribe() {
        jedison().withPool((jedis)-> {
            client(jedis).unsubscribe();
        });
    }

    default void unsubscribe(byte[]... channels) {
        jedison().withPool((jedis)-> {
            client(jedis).unsubscribe(channels);
        });
    }

    default void psubscribe(byte[]... patterns) {
        jedison().withPool((jedis)-> {
            client(jedis).psubscribe(patterns);
        });
    }

    default void punsubscribe() {
        jedison().withPool((jedis)-> {
            client(jedis).punsubscribe();
        });
    }

    default void punsubscribe(byte[]... patterns) {
        jedison().withPool((jedis)-> {
            client(jedis).punsubscribe(patterns);
        });
    }

    default void pubsub(byte[]... args) {
        jedison().withPool((jedis)-> {
            client(jedis).pubsub(args);
        });
    }

    default void zcount(byte[] key, double min, double max) {
        jedison().withPool((jedis)-> {
            client(jedis).zcount(key, min, max);
        });
    }

    default void zcount(byte[] key, byte[] min, byte[] max) {
        jedison().withPool((jedis)-> {
            client(jedis).zcount(key, min, max);
        });
    }

    default void zrangeByScore(byte[] key, double min, double max) {
        jedison().withPool((jedis)-> {
            client(jedis).zrangeByScore(key, min, max);
        });
    }

    default void zrangeByScore(byte[] key, byte[] min, byte[] max) {
        jedison().withPool((jedis)-> {
            client(jedis).zrangeByScore(key, min, max);
        });
    }

    default void zrevrangeByScore(byte[] key, double max, double min) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrangeByScore(key, max, min);
        });
    }

    default void zrevrangeByScore(byte[] key, byte[] max, byte[] min) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrangeByScore(key, max, min);
        });
    }

    default void zrangeByScore(byte[] key, double min, double max, int offset, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).zrangeByScore(key, min, max, offset, count);
        });
    }

    default void zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrangeByScore(key, max, min, offset, count);
        });
    }

    default void zrangeByScoreWithScores(byte[] key, double min, double max) {
        jedison().withPool((jedis)-> {
            client(jedis).zrangeByScoreWithScores(key, min, max);
        });
    }

    default void zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrangeByScoreWithScores(key, max, min);
        });
    }

    default void zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).zrangeByScoreWithScores(key, min, max, offset, count);
        });
    }

    default void zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrangeByScoreWithScores(key, max, min, offset, count);
        });
    }

    default void zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).zrangeByScore(key, min, max, offset, count);
        });
    }

    default void zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrangeByScore(key, max, min, offset, count);
        });
    }

    default void zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max) {
        jedison().withPool((jedis)-> {
            client(jedis).zrangeByScoreWithScores(key, min, max);
        });
    }

    default void zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrangeByScoreWithScores(key, max, min);
        });
    }

    default void zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).zrangeByScoreWithScores(key, min, max, offset, count);
        });
    }

    default void zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrangeByScoreWithScores(key, max, min, offset, count);
        });
    }

    default void zremrangeByRank(byte[] key, long start, long stop) {
        jedison().withPool((jedis)-> {
            client(jedis).zremrangeByRank(key, start, stop);
        });
    }

    default void zremrangeByScore(byte[] key, double min, double max) {
        jedison().withPool((jedis)-> {
            client(jedis).zremrangeByScore(key, min, max);
        });
    }

    default void zremrangeByScore(byte[] key, byte[] min, byte[] max) {
        jedison().withPool((jedis)-> {
            client(jedis).zremrangeByScore(key, min, max);
        });
    }

    default void zunionstore(byte[] dstkey, byte[]... sets) {
        jedison().withPool((jedis)-> {
            client(jedis).zunionstore(dstkey, sets);
        });
    }

    default void zunionstore(byte[] dstkey, ZParams params, byte[]... sets) {
        jedison().withPool((jedis)-> {
            client(jedis).zunionstore(dstkey, params, sets);
        });
    }

    default void zinterstore(byte[] dstkey, byte[]... sets) {
        jedison().withPool((jedis)-> {
            client(jedis).zinterstore(dstkey, sets);
        });
    }

    default void zinterstore(byte[] dstkey, ZParams params, byte[]... sets) {
        jedison().withPool((jedis)-> {
            client(jedis).zinterstore(dstkey, params, sets);
        });
    }

    default void zlexcount(byte[] key, byte[] min, byte[] max) {
        jedison().withPool((jedis)-> {
            client(jedis).zlexcount(key, min, max);
        });
    }

    default void zrangeByLex(byte[] key, byte[] min, byte[] max) {
        jedison().withPool((jedis)-> {
            client(jedis).zrangeByLex(key, min, max);
        });
    }

    default void zrangeByLex(byte[] key, byte[] min, byte[] max, int offset, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).zrangeByLex(key, min, max, offset, count);
        });
    }

    default void zrevrangeByLex(byte[] key, byte[] max, byte[] min) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrangeByLex(key, max, min);
        });
    }

    default void zrevrangeByLex(byte[] key, byte[] max, byte[] min, int offset, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).zrevrangeByLex(key, max, min, offset, count);
        });
    }

    default void zremrangeByLex(byte[] key, byte[] min, byte[] max) {
        jedison().withPool((jedis)-> {
            client(jedis).zremrangeByLex(key, min, max);
        });
    }

    default void save() {
        jedison().withPool((jedis)-> {
            client(jedis).save();
        });
    }

    default void bgsave() {
        jedison().withPool((jedis)-> {
            client(jedis).bgsave();
        });
    }

    default void bgrewriteaof() {
        jedison().withPool((jedis)-> {
            client(jedis).bgrewriteaof();
        });
    }

    default void lastsave() {
        jedison().withPool((jedis)-> {
            client(jedis).lastsave();
        });
    }

    default void shutdown() {
        jedison().withPool((jedis)-> {
            client(jedis).shutdown();
        });
    }

    default void info() {
        jedison().withPool((jedis)-> {
            client(jedis).info();
        });
    }

    default void info(String section) {
        jedison().withPool((jedis)-> {
            client(jedis).info(section);
        });
    }

    default void monitor() {
        jedison().withPool((jedis)-> {
            client(jedis).monitor();
        });
    }

    default void slaveof(String host, int port) {
        jedison().withPool((jedis)-> {
            client(jedis).slaveof(host, port);
        });
    }

    default void slaveofNoOne() {
        jedison().withPool((jedis)-> {
            client(jedis).slaveofNoOne();
        });
    }

    default void configGet(byte[] pattern) {
        jedison().withPool((jedis)-> {
            client(jedis).configGet(pattern);
        });
    }

    default void configSet(byte[] parameter, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).configSet(parameter, value);
        });
    }

    default void strlen(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).strlen(key);
        });
    }

    default void sync() {
        jedison().withPool((jedis)-> {
            client(jedis).sync();
        });
    }

    default void lpushx(byte[] key, byte[]... string) {
        jedison().withPool((jedis)-> {
            client(jedis).lpushx(key, string);
        });
    }

    default void persist(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).persist(key);
        });
    }

    default void rpushx(byte[] key, byte[]... string) {
        jedison().withPool((jedis)-> {
            client(jedis).rpushx(key, string);
        });
    }

    default void echo(byte[] string) {
        jedison().withPool((jedis)-> {
            client(jedis).echo(string);
        });
    }

    default void linsert(byte[] key, ListPosition where, byte[] pivot, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).linsert(key, where, pivot, value);
        });
    }

    default void debug(DebugParams params) {
        jedison().withPool((jedis)-> {
            client(jedis).debug(params);
        });
    }

    default void brpoplpush(byte[] source, byte[] destination, int timeout) {
        jedison().withPool((jedis)-> {
            client(jedis).brpoplpush(source, destination, timeout);
        });
    }

    default void configResetStat() {
        jedison().withPool((jedis)-> {
            client(jedis).configResetStat();
        });
    }

    default void configRewrite() {
        jedison().withPool((jedis)-> {
            client(jedis).configRewrite();
        });
    }

    default void setbit(byte[] key, long offset, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).setbit(key, offset, value);
        });
    }

    default void setbit(byte[] key, long offset, boolean value) {
        jedison().withPool((jedis)-> {
            client(jedis).setbit(key, offset, value);
        });
    }

    default void getbit(byte[] key, long offset) {
        jedison().withPool((jedis)-> {
            client(jedis).getbit(key, offset);
        });
    }

    default void bitpos(byte[] key, boolean value, BitPosParams params) {
        jedison().withPool((jedis)-> {
            client(jedis).bitpos(key, value, params);
        });
    }

    default void setrange(byte[] key, long offset, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).setrange(key, offset, value);
        });
    }

    default void getrange(byte[] key, long startOffset, long endOffset) {
        jedison().withPool((jedis)-> {
            client(jedis).getrange(key, startOffset, endOffset);
        });
    }

    default int getDB() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getDB();
        });
    }

    default void disconnect() {
        jedison().withPool((jedis)-> {
            client(jedis).disconnect();
        });
    }

    default void close() {
        jedison().withPool((jedis)-> {
            client(jedis).close();
        });
    }

    default void resetState() {
        jedison().withPool((jedis)-> {
            client(jedis).resetState();
        });
    }

    default void eval(byte[] script, byte[] keyCount, byte[][] params) {
        jedison().withPool((jedis)-> {
            client(jedis).eval(script, keyCount, params);
        });
    }

    default void eval(byte[] script, int keyCount, byte[]... params) {
        jedison().withPool((jedis)-> {
            client(jedis).eval(script, keyCount, params);
        });
    }

    default void evalsha(byte[] sha1, byte[] keyCount, byte[]... params) {
        jedison().withPool((jedis)-> {
            client(jedis).evalsha(sha1, keyCount, params);
        });
    }

    default void evalsha(byte[] sha1, int keyCount, byte[]... params) {
        jedison().withPool((jedis)-> {
            client(jedis).evalsha(sha1, keyCount, params);
        });
    }

    default void scriptFlush() {
        jedison().withPool((jedis)-> {
            client(jedis).scriptFlush();
        });
    }

    default void scriptExists(byte[]... sha1) {
        jedison().withPool((jedis)-> {
            client(jedis).scriptExists(sha1);
        });
    }

    default void scriptLoad(byte[] script) {
        jedison().withPool((jedis)-> {
            client(jedis).scriptLoad(script);
        });
    }

    default void scriptKill() {
        jedison().withPool((jedis)-> {
            client(jedis).scriptKill();
        });
    }

    default void slowlogGet() {
        jedison().withPool((jedis)-> {
            client(jedis).slowlogGet();
        });
    }

    default void slowlogGet(long entries) {
        jedison().withPool((jedis)-> {
            client(jedis).slowlogGet(entries);
        });
    }

    default void slowlogReset() {
        jedison().withPool((jedis)-> {
            client(jedis).slowlogReset();
        });
    }

    default void slowlogLen() {
        jedison().withPool((jedis)-> {
            client(jedis).slowlogLen();
        });
    }

    default void objectRefcount(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).objectRefcount(key);
        });
    }

    default void objectIdletime(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).objectIdletime(key);
        });
    }

    default void objectEncoding(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).objectEncoding(key);
        });
    }

    default void objectHelp() {
        jedison().withPool((jedis)-> {
            client(jedis).objectHelp();
        });
    }

    default void objectFreq(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).objectFreq(key);
        });
    }

    default void bitcount(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).bitcount(key);
        });
    }

    default void bitcount(byte[] key, long start, long end) {
        jedison().withPool((jedis)-> {
            client(jedis).bitcount(key, start, end);
        });
    }

    default void bitop(BitOP op, byte[] destKey, byte[]... srcKeys) {
        jedison().withPool((jedis)-> {
            client(jedis).bitop(op, destKey, srcKeys);
        });
    }

    default void sentinel(byte[]... args) {
        jedison().withPool((jedis)-> {
            client(jedis).sentinel(args);
        });
    }

    default void dump(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).dump(key);
        });
    }

    default void restore(byte[] key, int ttl, byte[] serializedValue) {
        jedison().withPool((jedis)-> {
            client(jedis).restore(key, ttl, serializedValue);
        });
    }

    default void restoreReplace(byte[] key, int ttl, byte[] serializedValue) {
        jedison().withPool((jedis)-> {
            client(jedis).restoreReplace(key, ttl, serializedValue);
        });
    }

    default void pexpire(byte[] key, long milliseconds) {
        jedison().withPool((jedis)-> {
            client(jedis).pexpire(key, milliseconds);
        });
    }

    default void pexpireAt(byte[] key, long millisecondsTimestamp) {
        jedison().withPool((jedis)-> {
            client(jedis).pexpireAt(key, millisecondsTimestamp);
        });
    }

    default void pttl(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).pttl(key);
        });
    }

    default void psetex(byte[] key, long milliseconds, byte[] value) {
        jedison().withPool((jedis)-> {
            client(jedis).psetex(key, milliseconds, value);
        });
    }

    default void srandmember(byte[] key, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).srandmember(key, count);
        });
    }

    default void memoryDoctor() {
        jedison().withPool((jedis)-> {
            client(jedis).memoryDoctor();
        });
    }

    default void clientKill(byte[] ipPort) {
        jedison().withPool((jedis)-> {
            client(jedis).clientKill(ipPort);
        });
    }

    default void clientKill(String ip, int port) {
        jedison().withPool((jedis)-> {
            client(jedis).clientKill(ip, port);
        });
    }

    default void clientKill(ClientKillParams params) {
        jedison().withPool((jedis)-> {
            client(jedis).clientKill(params);
        });
    }

    default void clientGetname() {
        jedison().withPool((jedis)-> {
            client(jedis).clientGetname();
        });
    }

    default void clientList() {
        jedison().withPool((jedis)-> {
            client(jedis).clientList();
        });
    }

    default void clientSetname(byte[] name) {
        jedison().withPool((jedis)-> {
            client(jedis).clientSetname(name);
        });
    }

    default void clientPause(long timeout) {
        jedison().withPool((jedis)-> {
            client(jedis).clientPause(timeout);
        });
    }

    default void clientId() {
        jedison().withPool((jedis)-> {
            client(jedis).clientId();
        });
    }

    default void time() {
        jedison().withPool((jedis)-> {
            client(jedis).time();
        });
    }

    default void migrate(String host, int port, byte[] key, int destinationDb, int timeout) {
        jedison().withPool((jedis)-> {
            client(jedis).migrate(host, port, key, destinationDb, timeout);
        });
    }

    default void migrate(String host, int port, int destinationDB, int timeout, MigrateParams params, byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).migrate(host, port, destinationDB, timeout, params, keys);
        });
    }

    default void hincrByFloat(byte[] key, byte[] field, double increment) {
        jedison().withPool((jedis)-> {
            client(jedis).hincrByFloat(key, field, increment);
        });
    }

    default void scan(byte[] cursor, ScanParams params) {
        jedison().withPool((jedis)-> {
            client(jedis).scan(cursor, params);
        });
    }

    default void hscan(byte[] key, byte[] cursor, ScanParams params) {
        jedison().withPool((jedis)-> {
            client(jedis).hscan(key, cursor, params);
        });
    }

    default void sscan(byte[] key, byte[] cursor, ScanParams params) {
        jedison().withPool((jedis)-> {
            client(jedis).sscan(key, cursor, params);
        });
    }

    default void zscan(byte[] key, byte[] cursor, ScanParams params) {
        jedison().withPool((jedis)-> {
            client(jedis).zscan(key, cursor, params);
        });
    }

    default void waitReplicas(int replicas, long timeout) {
        jedison().withPool((jedis)-> {
            client(jedis).waitReplicas(replicas, timeout);
        });
    }

    default void cluster(byte[]... args) {
        jedison().withPool((jedis)-> {
            client(jedis).cluster(args);
        });
    }

    default void asking() {
        jedison().withPool((jedis)-> {
            client(jedis).asking();
        });
    }

    default void pfadd(byte[] key, byte[]... elements) {
        jedison().withPool((jedis)-> {
            client(jedis).pfadd(key, elements);
        });
    }

    default void pfcount(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).pfcount(key);
        });
    }

    default void pfcount(byte[]... keys) {
        jedison().withPool((jedis)-> {
            client(jedis).pfcount(keys);
        });
    }

    default void pfmerge(byte[] destkey, byte[]... sourcekeys) {
        jedison().withPool((jedis)-> {
            client(jedis).pfmerge(destkey, sourcekeys);
        });
    }

    default void readonly() {
        jedison().withPool((jedis)-> {
            client(jedis).readonly();
        });
    }

    default void geoadd(byte[] key, double longitude, double latitude, byte[] member) {
        jedison().withPool((jedis)-> {
            client(jedis).geoadd(key, longitude, latitude, member);
        });
    }

    default void geoadd(byte[] key, Map<byte[], GeoCoordinate> memberCoordinateMap) {
        jedison().withPool((jedis)-> {
            client(jedis).geoadd(key, memberCoordinateMap);
        });
    }

    default void geodist(byte[] key, byte[] member1, byte[] member2) {
        jedison().withPool((jedis)-> {
            client(jedis).geodist(key, member1, member2);
        });
    }

    default void geodist(byte[] key, byte[] member1, byte[] member2, GeoUnit unit) {
        jedison().withPool((jedis)-> {
            client(jedis).geodist(key, member1, member2, unit);
        });
    }

    default void geohash(byte[] key, byte[]... members) {
        jedison().withPool((jedis)-> {
            client(jedis).geohash(key, members);
        });
    }

    default void geopos(byte[] key, byte[][] members) {
        jedison().withPool((jedis)-> {
            client(jedis).geopos(key, members);
        });
    }

    default void georadius(byte[] key, double longitude, double latitude, double radius, GeoUnit unit) {
        jedison().withPool((jedis)-> {
            client(jedis).georadius(key, longitude, latitude, radius, unit);
        });
    }

    default void georadiusReadonly(byte[] key, double longitude, double latitude, double radius, GeoUnit unit) {
        jedison().withPool((jedis)-> {
            client(jedis).georadiusReadonly(key, longitude, latitude, radius, unit);
        });
    }

    default void georadius(byte[] key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        jedison().withPool((jedis)-> {
            client(jedis).georadius(key, longitude, latitude, radius, unit, param);
        });
    }

    default void georadiusReadonly(byte[] key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        jedison().withPool((jedis)-> {
            client(jedis).georadiusReadonly(key, longitude, latitude, radius, unit, param);
        });
    }

    default void georadiusByMember(byte[] key, byte[] member, double radius, GeoUnit unit) {
        jedison().withPool((jedis)-> {
            client(jedis).georadiusByMember(key, member, radius, unit);
        });
    }

    default void georadiusByMemberReadonly(byte[] key, byte[] member, double radius, GeoUnit unit) {
        jedison().withPool((jedis)-> {
            client(jedis).georadiusByMemberReadonly(key, member, radius, unit);
        });
    }

    default void georadiusByMember(byte[] key, byte[] member, double radius, GeoUnit unit, GeoRadiusParam param) {
        jedison().withPool((jedis)-> {
            client(jedis).georadiusByMember(key, member, radius, unit, param);
        });
    }

    default void georadiusByMemberReadonly(byte[] key, byte[] member, double radius, GeoUnit unit, GeoRadiusParam param) {
        jedison().withPool((jedis)-> {
            client(jedis).georadiusByMemberReadonly(key, member, radius, unit, param);
        });
    }

    default void moduleLoad(byte[] path) {
        jedison().withPool((jedis)-> {
            client(jedis).moduleLoad(path);
        });
    }

    default void moduleList() {
        jedison().withPool((jedis)-> {
            client(jedis).moduleList();
        });
    }

    default void moduleUnload(byte[] name) {
        jedison().withPool((jedis)-> {
            client(jedis).moduleUnload(name);
        });
    }

    default void aclWhoAmI() {
        jedison().withPool((jedis)-> {
            client(jedis).aclWhoAmI();
        });
    }

    default void aclGenPass() {
        jedison().withPool((jedis)-> {
            client(jedis).aclGenPass();
        });
    }

    default void aclList() {
        jedison().withPool((jedis)-> {
            client(jedis).aclList();
        });
    }

    default void aclUsers() {
        jedison().withPool((jedis)-> {
            client(jedis).aclUsers();
        });
    }

    default void aclCat() {
        jedison().withPool((jedis)-> {
            client(jedis).aclCat();
        });
    }

    default void aclCat(byte[] category) {
        jedison().withPool((jedis)-> {
            client(jedis).aclCat(category);
        });
    }

    default void aclSetUser(byte[] name) {
        jedison().withPool((jedis)-> {
            client(jedis).aclSetUser(name);
        });
    }

    default void aclGetUser(byte[] name) {
        jedison().withPool((jedis)-> {
            client(jedis).aclGetUser(name);
        });
    }

    default void aclSetUser(byte[] name, byte[][] parameters) {
        jedison().withPool((jedis)-> {
            client(jedis).aclSetUser(name, parameters);
        });
    }

    default void aclDelUser(byte[] name) {
        jedison().withPool((jedis)-> {
            client(jedis).aclDelUser(name);
        });
    }

    default void bitfield(byte[] key, byte[]... value) {
        jedison().withPool((jedis)-> {
            client(jedis).bitfield(key, value);
        });
    }

    default void bitfieldReadonly(byte[] key, byte[]... arguments) {
        jedison().withPool((jedis)-> {
            client(jedis).bitfieldReadonly(key, arguments);
        });
    }

    default void hstrlen(byte[] key, byte[] field) {
        jedison().withPool((jedis)-> {
            client(jedis).hstrlen(key, field);
        });
    }

    default void xadd(byte[] key, byte[] id, Map<byte[], byte[]> hash, long maxLen, boolean approximateLength) {
        jedison().withPool((jedis)-> {
            client(jedis).xadd(key, id, hash, maxLen, approximateLength);
        });
    }

    default void xlen(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).xlen(key);
        });
    }

    default void xrange(byte[] key, byte[] start, byte[] end, long count) {
        jedison().withPool((jedis)-> {
            client(jedis).xrange(key, start, end, count);
        });
    }

    default void xrevrange(byte[] key, byte[] end, byte[] start, int count) {
        jedison().withPool((jedis)-> {
            client(jedis).xrevrange(key, end, start, count);
        });
    }

    default void xread(int count, long block, Map<byte[], byte[]> streams) {
        jedison().withPool((jedis)-> {
            client(jedis).xread(count, block, streams);
        });
    }

    default void xack(byte[] key, byte[] group, byte[]... ids) {
        jedison().withPool((jedis)-> {
            client(jedis).xack(key, group, ids);
        });
    }

    default void xgroupCreate(byte[] key, byte[] groupname, byte[] id, boolean makeStream) {
        jedison().withPool((jedis)-> {
            client(jedis).xgroupCreate(key, groupname, id, makeStream);
        });
    }

    default void xgroupSetID(byte[] key, byte[] groupname, byte[] id) {
        jedison().withPool((jedis)-> {
            client(jedis).xgroupSetID(key, groupname, id);
        });
    }

    default void xgroupDestroy(byte[] key, byte[] groupname) {
        jedison().withPool((jedis)-> {
            client(jedis).xgroupDestroy(key, groupname);
        });
    }

    default void xgroupDelConsumer(byte[] key, byte[] groupname, byte[] consumerName) {
        jedison().withPool((jedis)-> {
            client(jedis).xgroupDelConsumer(key, groupname, consumerName);
        });
    }

    default void xdel(byte[] key, byte[]... ids) {
        jedison().withPool((jedis)-> {
            client(jedis).xdel(key, ids);
        });
    }

    default void xtrim(byte[] key, long maxLen, boolean approximateLength) {
        jedison().withPool((jedis)-> {
            client(jedis).xtrim(key, maxLen, approximateLength);
        });
    }

    default void xreadGroup(byte[] groupname, byte[] consumer, int count, long block, boolean noAck, Map<byte[], byte[]> streams) {
        jedison().withPool((jedis)-> {
            client(jedis).xreadGroup(groupname, consumer, count, block, noAck, streams);
        });
    }

    default void xpending(byte[] key, byte[] groupname, byte[] start, byte[] end, int count, byte[] consumername) {
        jedison().withPool((jedis)-> {
            client(jedis).xpending(key, groupname, start, end, count, consumername);
        });
    }

    default void xclaim(byte[] key, byte[] groupname, byte[] consumername, long minIdleTime, long newIdleTime, int retries, boolean force, byte[][] ids) {
        jedison().withPool((jedis)-> {
            client(jedis).xclaim(key, groupname, consumername, minIdleTime, newIdleTime, retries, force, ids);
        });
    }

    default void xinfoStream(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).xinfoStream(key);
        });
    }

    default void xinfoGroup(byte[] key) {
        jedison().withPool((jedis)-> {
            client(jedis).xinfoGroup(key);
        });
    }

    default void xinfoConsumers(byte[] key, byte[] group) {
        jedison().withPool((jedis)-> {
            client(jedis).xinfoConsumers(key, group);
        });
    }

    default Socket getSocket() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getSocket();
        });
    }

    default int getConnectionTimeout() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getConnectionTimeout();
        });
    }

    default int getSoTimeout() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getSoTimeout();
        });
    }

    default void setConnectionTimeout(int connectionTimeout) {
        jedison().withPool((jedis)-> {
            client(jedis).setConnectionTimeout(connectionTimeout);
        });
    }

    default void setSoTimeout(int soTimeout) {
        jedison().withPool((jedis)-> {
            client(jedis).setSoTimeout(soTimeout);
        });
    }

    default void setTimeoutInfinite() {
        jedison().withPool((jedis)-> {
            client(jedis).setTimeoutInfinite();
        });
    }

    default void rollbackTimeout() {
        jedison().withPool((jedis)-> {
            client(jedis).rollbackTimeout();
        });
    }

    default void sendCommand(ProtocolCommand cmd, String... args) {
        jedison().withPool((jedis)-> {
            client(jedis).sendCommand(cmd, args);
        });
    }

    default void sendCommand(ProtocolCommand cmd) {
        jedison().withPool((jedis)-> {
            client(jedis).sendCommand(cmd);
        });
    }

    default void sendCommand(ProtocolCommand cmd, byte[]... args) {
        jedison().withPool((jedis)-> {
            client(jedis).sendCommand(cmd, args);
        });
    }

    default String getHost() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getHost();
        });
    }

    default void setHost(String host) {
        jedison().withPool((jedis)-> {
            client(jedis).setHost(host);
        });
    }

    default int getPort() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getPort();
        });
    }

    default void setPort(int port) {
        jedison().withPool((jedis)-> {
            client(jedis).setPort(port);
        });
    }

    default boolean isConnected() {
        return jedison().withPool((jedis)-> {
            return client(jedis).isConnected();
        });
    }

    default String getStatusCodeReply() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getStatusCodeReply();
        });
    }

    default String getBulkReply() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getBulkReply();
        });
    }

    default byte[] getBinaryBulkReply() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getBinaryBulkReply();
        });
    }

    default Long getIntegerReply() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getIntegerReply();
        });
    }

    default List<String> getMultiBulkReply() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getMultiBulkReply();
        });
    }

    default List<byte[]> getBinaryMultiBulkReply() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getBinaryMultiBulkReply();
        });
    }

    default List<Object> getRawObjectMultiBulkReply() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getRawObjectMultiBulkReply();
        });
    }

    default List<Object> getUnflushedObjectMultiBulkReply() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getUnflushedObjectMultiBulkReply();
        });
    }

    default List<Object> getObjectMultiBulkReply() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getObjectMultiBulkReply();
        });
    }

    default List<Long> getIntegerMultiBulkReply() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getIntegerMultiBulkReply();
        });
    }

    default Object getOne() {
        return jedison().withPool((jedis)-> {
            return client(jedis).getOne();
        });
    }

    default boolean isBroken() {
        return jedison().withPool((jedis)-> {
            return client(jedis).isBroken();
        });
    }

    default List<Object> getMany(int count) {
        return jedison().withPool((jedis)-> {
            return client(jedis).getMany(count);
        });
    }

}
