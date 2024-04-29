package hw_12.otus_hash_table;

public class DummyEntry<K, V> extends HashEntry<K, V> {

    private static DummyEntry entry;

    private DummyEntry() {
        super(null, null);
    }

    public static DummyEntry getInstance() {
        if (entry == null) {
            return new DummyEntry();
        }
        return entry;
    }
}
