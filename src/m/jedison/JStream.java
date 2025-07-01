package m.jedison;

public class JStream<K, V> implements JSpacedSerializable {
    
    protected final JSpaceStream            space;
    protected final JedisonIdConsumerStream consumer;
    
    public JStream(JSpaceStream space) {
        this.space    = space;
        this.consumer = new JedisonIdConsumerStream(jedison().id());
    }

    public JStream(JSpaceStream space, JedisonIdConsumerStream consumer) {
        this.space    = space;
        this.consumer = consumer;
    }
    
    public JStreamGroup<K, V> group(String key) {
        return this.group( new JSpaceStreamGroup(this.space(), key) );
    }

    public JStreamGroup<K, V> group(JSpaceStreamGroup group) {
        return new JStreamGroup<K, V>(this, group);
    }

    public JStreamMessage add(K key, V val) {
        return add(serializeVal(key), serializeVal(val));
    }

    public JStreamMessage add(byte[] key, byte[] val) {
        byte[] bytes = jedison().xadd(name(), key, val);

        return new JStreamMessageQueued( deserializeKey(bytes) );
    }

    public JSpaceStream space() {
        return space;
    }
    
    public JedisonIdConsumerStream consumer() {
        return consumer;
    }
}
