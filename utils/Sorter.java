package utils;

public class Sorter {

    public void bubbleSort(int[] arr) {
        final var start = System.nanoTime();

        for (int j = arr.length - 1; j >= 0; j--) {
            for (int i = 0; i < j; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
        final var stop = System.nanoTime();
        System.out.println("Bubble sort completed in " + (stop - start) + " for " + arr.length + " elements");
    }

    public void insertionSort(int[] arr) {
        final var start = System.nanoTime();

        for (int j = 1; j < arr.length; j++) {
            for (int i = j - 1; i >= 0 && (arr[i] > arr[i + 1]); i--) {
                swap(arr, i, i + 1);
            }
        }

        final var stop = System.nanoTime();
        System.out.println("Insertion sort completed in " + (stop - start) + " for " + arr.length + " elements");
    }

    public void shellSort(int[] arr) {
        final var start = System.nanoTime();

        for (int k = arr.length / 2; k > 0; k = k / 2) {
            for (int j = k; j < arr.length; j++) {
                for (int i = j; i >= k && (arr[i - k] > arr[i]); i = i - k) {
                    swap(arr, i - k, i);
                }
            }
        }

        final var stop = System.nanoTime();
        System.out.println("Shell sort completed in " + (stop - start) + " for " + arr.length + " elements");
    }


    public void selectionSort(int[] arr) {
        final var start = System.nanoTime();

        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, maxElement(arr, j), j);
        }

        final var stop = System.nanoTime();
        System.out.println("Selection sort completed in " + (stop - start) + " for " + arr.length + " elements");
    }


    public void heapSort(int[] arr) {
        final var start = System.nanoTime();
        final var size = arr.length;

        for (int h = (size - 1) / 2; h >= 0; h--) {
            heapify(arr, h, size);
        }
        for (int j = size - 1; j > 0; j--) {
            swap(arr, 0, j);
            heapify(arr, 0, j);
        }

        final var stop = System.nanoTime();
        System.out.println("Heap sort completed in " + (stop - start) + " for " + size + " elements");
    }


    private void swap(int[] arr, int x, int y) {
        final var temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private int maxElement(int[] arr, int index) {
        var max = 0;

        for (int i = 1; i <= index; i++) {
            if (arr[i] > arr[max]) {
                max = i;
            }
        }

        return max;
    }

    private void heapify(int[] arr, int rootIndex, int lastIndex) {
//        p - max element index
        var p = rootIndex;
        var l = 2 * p + 1;
        var r = l + 1;
        if (l < lastIndex && arr[l] > arr[p]) {
            p = l;
        }
        if (r < lastIndex && arr[r] > arr[p]) {
            p = r;
        }
        if (p != rootIndex) {
            swap(arr, rootIndex, p);
            heapify(arr, p, lastIndex);
        }
    }
}
