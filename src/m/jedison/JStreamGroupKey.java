package m.jedison;

public final class JStreamGroupKey<K, V> implements JSpacedSerializable {
    private final JStreamGroup<K, V>   outer;
    private final JSpaceStreamGroupKey space;

    JStreamGroupKey(JStreamGroup<K, V> outer, JSpaceStreamGroupKey space) {
        this.outer = outer;
        this.space = space;
    }

    @Override
    public JSpaceStreamGroupKey space() {
        return space;
    }
    
    public JStreamGroup<K, V> parent() {
        return outer; 
    }
    
}
