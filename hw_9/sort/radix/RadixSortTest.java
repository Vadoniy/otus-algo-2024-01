package hw_9.sort.radix;

import utils.ArrayUtils;

import java.util.Arrays;

public class RadixSortTest {
    public static void main(String[] args) {
        final var inputArray = ArrayUtils.generateIntArray(20, 100);
        final var sorter = new RadixSort();

        sorter.sort(inputArray, 100);

        System.out.println("RadixSort result array: " + Arrays.toString(inputArray));

    }
}
