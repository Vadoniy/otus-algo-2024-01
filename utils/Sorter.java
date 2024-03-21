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

    public void quickSort(int[] arr) {
        final var start = System.nanoTime();
        final var size = arr.length;

        quickSortR(arr, 0, size - 1);

        final var stop = System.nanoTime();
        System.out.println("Quick sort completed in " + (stop - start) + " for " + size + " elements");
    }

    public void mergeSort(int[] arr) {
        final var start = System.nanoTime();
        final var size = arr.length;

        mergeSortR(arr, 0, arr.length - 1);

        final var stop = System.nanoTime();
        System.out.println("Merge sort completed in " + (stop - start) + " for " + size + " elements");
    }

    private void mergeSortR(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = (left + right) / 2;
        mergeSortR(arr, left, middle);
        mergeSortR(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    private void merge(int[] arr, int left, int middle, int right) {
        int[] tempArr = new int[right - left + 1];
        int a = left;
        int b = middle + 1;
        int m = 0;

        while (a <= middle && b <= right) {
            if (arr[a] > arr[b]) {
                tempArr[m++] = arr[b++];
            } else {
                tempArr[m++] = arr[a++];
            }
        }
        while (a <= middle) {
            tempArr[m++] = arr[a++];
        }

        while (b <= right) {
            tempArr[m++] = arr[b++];
        }

        if (right - left >= 0) {
            System.arraycopy(tempArr, 0, arr, left, right - left + 1);
        }
//        for (int i = left; i <= right; i++) {
//            arr[i] = tempArr[i - left];
//        }
    }

    private void quickSortR(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int m = split(arr, l, r);
        quickSortR(arr, l, m - 1);
        quickSortR(arr, m + 1, r);
    }

    private int split(int[] arr, int l, int r) {
//        p - опорный элемент
        var p = arr[r];
        var m = l - 1;

        for (int j = l; j <= r; j++) {
            if (arr[j] <= p) {
                m++;
                swap(arr, m, j);
            }
        }

        return m;
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
