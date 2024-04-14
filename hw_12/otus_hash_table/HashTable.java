package hw_12.otus_hash_table;

public class HashTable<K, V> {
    private static final int TABLE_SIZE = 16;
    private static final int INITIAL_HASH = -1;
    private final HashEntry<K, V>[] table;
    private int elementsCounter = 0;

    public HashTable() {
        table = new HashEntry[TABLE_SIZE];

        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = null;
        }
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }

        int hash = calculateHash(key);
        int initialHash = INITIAL_HASH;

        while (hash != initialHash
                && (!(table[hash] instanceof DummyEntry) || table[hash] != null
                && table[hash].getKey() != key)) {
            if (initialHash == INITIAL_HASH) {
                initialHash = hash;
            }
            hash = (hash + 1) % TABLE_SIZE;
        }

        if (table[hash] == null) {
            return null;
        } else {
            return table[hash].getValue();
        }
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        int hash = calculateHash(key);
        int initialHash = INITIAL_HASH;
        int indexOfDeletedEntry = INITIAL_HASH;

        while (hash != initialHash
                && (table[hash] == DummyEntry.getInstance() || table[hash] != null
                && table[hash].getKey() != key)) {
            if (initialHash == INITIAL_HASH) {
                initialHash = hash;
            }
            if (table[hash] != DummyEntry.getInstance()) {
                indexOfDeletedEntry = hash;
            }
            hash = (hash + 1) % TABLE_SIZE;
        }
        if ((table[hash] == null || hash == initialHash)
                && indexOfDeletedEntry != INITIAL_HASH) {
            table[indexOfDeletedEntry] = new HashEntry(key, value);
            elementsCounter++;
        } else if (initialHash != hash) {
            if (table[hash] != DummyEntry.getInstance()
                    && table[hash] != null && table[hash].getKey() == key) {
                table[hash].setValue(value);
            } else {
                table[hash] = new HashEntry(key, value);
                elementsCounter++;
            }
        }
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        int hash = calculateHash(key);
        int initialHash = INITIAL_HASH;
        while (hash != initialHash
                && (table[hash] == DummyEntry.getInstance() || table[hash] != null
                && table[hash].getKey() != key)) {
            if (initialHash == INITIAL_HASH) {
                initialHash = hash;
            }
            hash = (hash + 1) % TABLE_SIZE;
        }
        if (hash != initialHash && table[hash] != null) {
            table[hash] = DummyEntry.getInstance();
            elementsCounter--;
        }
    }

    public int getSize() {
        return this.elementsCounter;
    }

    private int calculateHash(K key) {
        return Math.abs(key.hashCode()) % TABLE_SIZE;
    }
}
