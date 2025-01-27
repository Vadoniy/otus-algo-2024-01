package hw_9.sort.counting;

import utils.ArrayUtils;

import java.util.Arrays;

public class CountingSortTest {
    public static void main(String[] args) {
        final var inputArray = ArrayUtils.generateIntArray(20, 100);
        final var countingSort = new CountingSort();

        countingSort.sort(inputArray, 100);

        System.out.println("CountingSort result array: " + Arrays.toString(inputArray));
    }
}
