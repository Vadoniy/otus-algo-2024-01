package hw_9.sort.counting;

import hw_9.sort.LinearSorter;

public class CountingSort implements LinearSorter {

    public void sort(int[] inputArray, int maxValue) {
//        Создаём массив счётчиков
        final var countArray = new int[maxValue + 1]; //+1 чтобы учесть само максимальное значение
//        Считаем количество элементов для каждого значения
        for (int k : inputArray) {
//            Прибавляем 1 к счётчику данного значения
            countArray[k]++;
        }

//        Копируем значения в исходный массив
        var index = 0;

        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                inputArray[index] = i;
                index++;
            }
        }
    }
}