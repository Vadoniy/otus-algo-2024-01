package hw_7;

import utils.ArrayUtils;
import utils.Sorter;

public class Application7 {

    public static void main(String[] args) {
        final var sorter = new Sorter();

        for (int i = 2; i <= 6; i++) {
            sorter.selectionSort(ArrayUtils.generateIntArray((int) Math.pow(10, i)));
        }

        for (int i = 2; i <= 6; i++) {
            sorter.heapSort(ArrayUtils.generateIntArray((int) Math.pow(10, i)));
        }
    }
}
