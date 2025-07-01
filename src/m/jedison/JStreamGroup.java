package m.jedison;

import m.Lambda;
import m.Numbers;
import redis.clients.jedis.StreamConsumersInfo;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.StreamPendingEntry;

import java.util.AbstractMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Joseph S.
 */
public class JStreamGroup<K, V> implements JSpacedSerializable {
    
    private final JStream<K, V>     outer;
    private final JSpaceStreamGroup space;

    JStreamGroup(JStream<K, V> outer, JSpaceStreamGroup space) {
        this.outer = outer;
        this.space = space;
    }

    @Override
    public final Jedison jedison() {
        return outer.jedison();
    }

    /**
     * Returns true if was just created, false for anything else, including already exists.
     */
    public boolean create(JStreamMessageSentGroupCreate command) {
        try {
            jedison().binary.xgroupCreate(
                serializeKey(outer.name()),
                serializeKey(name()),
                serializeKey(command.toString()),
                true
            );

            return true;
        }
        catch(Throwable e) {
            // We ignore it since it will throw if already created. We don't care.
            return false;
        }
    }

    public JStreamGroupKey<K, V> key(CharSequence key) {
        return new JStreamGroupKey<>(this, new JSpaceStreamGroupKey(this.space, key));
    }
    
    public long ack(JStreamMessage message) {
        return Numbers.toLong(jedison().binary.xack(
                serializeKey(outer.name()),
                serializeKey(name()),
                new byte[][]{ serializeKey(message.toString()) }
            )
        );
    }

    /////////////////////////////////////////////////////////////////////
    
    public List<JStreamMessageRecievedGroupRead<K, V>> read() {
        return this.read(outer.consumer, 1, TimeUnit.DAYS.toMillis(365));
    }

    public List<JStreamMessageRecievedGroupRead<K, V>> read(JedisonIdConsumerStream consumer, int count, long blockInMillis) {
        return jedison().xreadGroup(
            serializeKey(outer.name()),
            serializeKey(name()),
            serializeKey(consumer.id()),
            count,
            blockInMillis,
            false,
            JStreamMessageSentGroupRead.UNRECIEVED
        );
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public final List<StreamPendingEntry> pending(JedisonIdConsumerStream consumer, int count) {
        return jedison().xpending(
            serializeKey(outer.name()),
            serializeKey(name()),
            serializeKey(JedisonConstants.MINUS.toString()),
            serializeKey(JedisonConstants.PLUS.toString()),
            count,
            serializeKey(consumer.id())
        );
    }
    
    /**
     * Remove another consumer. Will attempt claim and process their pendings first.
     */
    public void remove(JedisonIdConsumerStream consumer, Lambda.V1<List<JStreamMessageRecievedGroupRead<K, V>>> lambda) {
        claim(consumer, lambda); remove(consumer);
    }
    
    /**
     * Note, simply just removing a consumer without properly having claimed their pending messages is not recommended.
     * Consider the use of remove(consumer, lambda) instead which will claim first.
     */
    protected long remove(JedisonIdConsumerStream consumer) {
        return Numbers.toLong(
                jedison().binary.xgroupDelConsumer(
                        serializeKey(outer.name()),
                        serializeKey(name()),
                        serializeKey(consumer.id())
                )
        );
    }
    
    /**
     * Claim and process those pending for the consumer one at a time allowing all other consumers to join in and try to claim some.
     * Recurses until there are no more pending messages left.
     */
    public void claim(JedisonIdConsumerStream consumer, Lambda.V1<List<JStreamMessageRecievedGroupRead<K, V>>> lambda) {
        List<StreamPendingEntry> pendings = pending(consumer, 1);
        if ( !pendings.isEmpty() ) {
            List<JStreamMessageRecievedGroupRead<K, V>> claimed = claim(pendings);
    
            // We operate only on the claimed ones
            lambda.call(claimed);
    
            // We recurse to claim any potential leftovers until we get pendings is empty
            claim(consumer, lambda);
        }
    }
    
    /**
     * Only claim these entries. Consider using claim(... lambda) instead. See JQueue usage.
     */
    protected List<JStreamMessageRecievedGroupRead<K, V>> claim(List<StreamPendingEntry> pendings) {
        return jedison().xclaim(
            serializeKey(outer.name()),
            serializeKey(name()),
            serializeKey(outer.consumer.id()),
            0,
            0,
            0,
            false,
            pendings
        );
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public List<StreamConsumersInfo> consumers() {
        return jedison().binary.xinfoConsumers(serializeKey(outer.name()), serializeKey(name()));
    }

    private AbstractMap.SimpleImmutableEntry<String, StreamEntryID> oldEntry(JStreamMessageSentGroupRead id) {
        return new AbstractMap.SimpleImmutableEntry<>(outer.name(), id.jedis);
    }

    @Override
    public JSpaceStreamGroup space() {
        return space;
    }

}
