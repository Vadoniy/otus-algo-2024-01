package hw_6;

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

    private void swap(int[] arr, int x, int y) {
        final var temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
