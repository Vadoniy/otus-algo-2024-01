package hw_9.sort;

import hw_9.sort.bucket.BucketSort;
import hw_9.sort.counting.CountingSort;
import hw_9.sort.radix.RadixSort;
import utils.ArrayUtils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinearSortersTest {

    private static final Map<LinearSorter, int[]> SORTER_TO_ARRAY_MAP = new HashMap<>(3);
    private static final StringBuffer SB = new StringBuffer();
    private static final String RESULT_FORMAT_PATTER = "    %s  :   %s  for %s  values  with    %s  maxValue\n";
    private static final int MAX_VALUE = 1000;
    private static final List<Integer> AMOUNT_OF_ELEMENTS = List.of(
            BigInteger.valueOf(10).pow(2).intValue(),
            BigInteger.valueOf(10).pow(3).intValue(),
            BigInteger.valueOf(10).pow(4).intValue(),
            BigInteger.valueOf(10).pow(5).intValue(),
            BigInteger.valueOf(10).pow(6).intValue(),
            BigInteger.valueOf(10).pow(7).intValue()
    );

    public static void main(String[] args) {
        final var bucketSort = new BucketSort();
        final var radixSort = new RadixSort();
        final var countingSort = new CountingSort();
        AMOUNT_OF_ELEMENTS.forEach(amountOfValues -> {
            SB.append("----------------------------------------------------------------------\n");
                    final var commonInputArray = ArrayUtils.generateIntArray(amountOfValues, MAX_VALUE);
                    SORTER_TO_ARRAY_MAP.put(bucketSort, Arrays.copyOf(commonInputArray, commonInputArray.length));
                    SORTER_TO_ARRAY_MAP.put(radixSort, Arrays.copyOf(commonInputArray, commonInputArray.length));
                    SORTER_TO_ARRAY_MAP.put(countingSort, Arrays.copyOf(commonInputArray, commonInputArray.length));
                    SORTER_TO_ARRAY_MAP.forEach(LinearSortersTest::initTest);
                }
        );
        System.out.println(SB);
    }

    private static void initTest(LinearSorter sorter, int[] inputArray) {
        final var startTime = System.currentTimeMillis();
        sorter.sort(inputArray, MAX_VALUE);
        final var endTime = System.currentTimeMillis();
        SB.append(String.format(RESULT_FORMAT_PATTER, sorter.getClass().getSimpleName(), endTime - startTime, inputArray.length, MAX_VALUE));
    }
}
