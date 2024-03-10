package hw_6;

import utils.ArrayUtils;

public class Application {

    public static void main(String[] args) {
        final var sorter = new Sorter();
        var arr = ArrayUtils.generateIntArray(100);
        sorter.bubbleSort(arr);
        arr = ArrayUtils.generateIntArray(1000);
        sorter.bubbleSort(arr);
        arr = ArrayUtils.generateIntArray(10000);
        sorter.bubbleSort(arr);
        arr = ArrayUtils.generateIntArray(100);
        sorter.insertionSort(arr);
        arr = ArrayUtils.generateIntArray(1000);
        sorter.insertionSort(arr);
        arr = ArrayUtils.generateIntArray(10000);
        sorter.insertionSort(arr);
        arr = ArrayUtils.generateIntArray(100);
        sorter.shellSort(arr);
        arr = ArrayUtils.generateIntArray(1000);
        sorter.shellSort(arr);
        arr = ArrayUtils.generateIntArray(10000);
        sorter.shellSort(arr);
    }
}
