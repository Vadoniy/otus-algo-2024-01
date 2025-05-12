package hw_22;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

public class BloomFilterApp {
    private static final double ERROR_RATE = 0.005f;
    private static final int MAX_KEYS = 1_000_000;
    private static final Set<byte[]> DATA_SET = new HashSet<>();

    public static void main(String[] args) {

        for (int i = 0; i < MAX_KEYS; i++) {
            DATA_SET.add(ByteBuffer.allocate(4).putInt(i).array());
        }

        final var bloomFilter = new BloomFilter(MAX_KEYS, ERROR_RATE, 0);
        int falsePositiveCount = 0;

        DATA_SET.forEach(bloomFilter::add);

        for (int i = MAX_KEYS + 1; i < 2 * MAX_KEYS; i++) {
            if (bloomFilter.contains(ByteBuffer.allocate(4).putInt(i).array()))
                falsePositiveCount++;
        }

        final var limit = MAX_KEYS * ERROR_RATE;
        System.out.println("falsePositiveCount = " + falsePositiveCount + ", limit = " + limit);
        if (falsePositiveCount < limit) {
            throw new RuntimeException("False positive count = " + falsePositiveCount + ", but must be bigger than " + limit);
        }
    }
}