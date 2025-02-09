package hw_9.sort.bucket;

import utils.ArrayUtils;

import java.util.Arrays;

public class BucketSortTest {
    public static void main(String[] args) {
        final var inputArray = ArrayUtils.generateIntArray(20, 100);
        final var sorter = new BucketSort();

        sorter.sort(inputArray, 100);

        System.out.println("BucketSort result array: " + Arrays.toString(inputArray));
    }
}
