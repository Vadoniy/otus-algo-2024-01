package hw_9.sort.radix;

import hw_9.sort.LinearSorter;

public class RadixSort implements LinearSorter {

    public void sort(int[] array, int maxValue) {
        // Сортируем по каждому разряду
        for (int exp = 1; maxValue / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
        }
    }

    private void countingSortByDigit(int[] array, int exp) {
        final var inputArrayLength = array.length;
        final var output = new int[inputArrayLength]; // Временный массив для результата
        final var count = new int[10]; // Для цифр (от 0 до 9) - считаем количество каждоый цифры в массиве.
        // Индекс этого массива - это цифра, которую мы считаем, а значение - количество этой цифры в массиве.

//        Количество элементов для каждой цифры
        for (int j : array) {
            final var digit = (j / exp) % 10;
            count[digit]++;
        }

        /*Преобразуем count[i] так, чтобы в count[i] содержалась позиция, куда надо будет записать цифру i в массиве output.
        Этот шаг обеспечивает СТАБИЛЬНОСТЬ массива, чтобы повторяющиеся числа оставалист на своих местах при сортировке.
        Т е, например, после этого цикла массив count [3, 1, 1, 3, 1, 2, 3, 2, 3, 1] будет
        выглядеть [3, 4, 5, 8, 9, 11, 14, 16, 19, 20], это значит:
        count[0] = 3: первые три позиции (0-2) в output будут заполнены элементами с цифрой 0
        count[1] = 4: следующие count[1]-count[0] (4-3=1) позиции (3) в output будут заполнены элементами с цифрой 1
        count[2] = 5: следующие count[2]-count[1] (5-4=1) позиции (4) в output будут заполнены элементами с цифрой 2
        count[3] = 8: следующие count[3]-count[2] (8-5=3) позиции (5-7) в output будут заполнены элементами с цифрой 3
        count[4] = 9: следующие count[4]-count[3] (9-8=1) позиции (8) в output будут заполнены элементами с цифрой 4
        count[5] = 11: следующие count[5]-count[4] (11-9=2) позиции (9-10) в output будут заполнены элементами с цифрой 5
        count[6] = 14: следующие count[6]-count[5] (14-11=3) позиции (11-13) в output будут заполнены элементами с цифрой 6
        count[7] = 16: следующие count[7]-count[6] (16-14=2) позиции (14-15) в output будут заполнены элементами с цифрой 7
        count[8] = 19: следующие count[8]-count[7] (19-16=3) позиции (16-18) в output будут заполнены элементами с цифрой 8
        count[9] = 20: следующие count[9]-count[8] (20-19=1) позиции (19) в output будут заполнены элементами с цифрой 9
         */
        for (int i = 1; i < 10; i++) {
            count[i] = count[i] + count[i - 1];
        }

//        Сортируем массив по текущему разряду
        for (int i = inputArrayLength - 1; i >= 0; i--) {
            final var digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

//        Копируем отсортированные данные в исходный массив
        System.arraycopy(output, 0, array, 0, inputArrayLength);
    }
}
