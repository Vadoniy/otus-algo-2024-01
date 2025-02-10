package hw_15;

import java.util.Arrays;

public class DemukronSortTest {
    public static void main(String[] args) {
        int[][] graph = {
                {2, 3, -1, -1},
                {3, 4, 5, -1},
                {4, -1, -1, -1},
                {5, -1, -1, -1},
                {-1, -1, -1, -1}
        };

        final var sorter = new DemukronSorter(graph, 5);
        final var levels = sorter.sort();

        System.out.println("Вершины по уровням:");
        for (int i = 0; i < levels.length; i++) {
            System.out.println("Уровень " + i + ": " + Arrays.toString(levels[i]));
        }
    }
}