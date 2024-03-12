package hw_8;

import utils.ArrayUtils;
import utils.Sorter;

public class Application8 {

    public static void main(String[] args) {
        final var sorter = new Sorter();

        for (int i = 2; i <= 6; i++) {
            sorter.quickSort(ArrayUtils.generateIntArray((int) Math.pow(10, i)));
        }

//        for (int i = 2; i <= 6; i++) {
//            sorter.mergeSort(ArrayUtils.generateIntArray((int) Math.pow(10, i)));
//        }
//        for (int i = 2; i <= 3; i++) {
////            sorter.quickSort(ArrayUtils.generateIntArray((int) Math.pow(10, i)));
//            var arr = ArrayUtils.generateIntArray((int) Math.pow(10, 1));
//            System.out.println(Arrays.toString(arr));
//            sorter.mergeSort(arr);
//            System.out.println(Arrays.toString(arr));
//        }
    }
}
