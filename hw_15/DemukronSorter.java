package hw_15;

import java.util.Arrays;

public class DemukronSorter {
    private final int[][] graph;
    private final int N;

    public DemukronSorter(int[][] graph, int N) {
        this.graph = graph;
        this.N = N;
    }

    public int[][] sort() {
        final var inDegree = new int[N]; // Количество входящих рёбер
        for (int i = 0; i < N; i++) {
            for (int v : graph[i]) {
                if (v != -1) { // -1 означает отсутствие вершины в строке
                    inDegree[v - 1]++;
                }
            }
        }

        var queue = new MyQueue(N);
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.enqueue(i);
            }
        }

        int[][] levels = new int[N][];  // Массив для уровней
        var levelIndex = 0;

        while (queue.isNotEmpty()) {
            final var nextQueue = new MyQueue(N);
            final var levelNodes = new int[queue.size()];
            int count = 0;

            while (queue.isNotEmpty()) {
                int node = queue.dequeue();
                levelNodes[count++] = node + 1;

                for (int neighbor : graph[node]) {
                    if (neighbor != -1) {
                        inDegree[neighbor - 1]--;
                        if (inDegree[neighbor - 1] == 0) {
                            nextQueue.enqueue(neighbor - 1);
                        }
                    }
                }
            }

            // Добавляем уровень в массив
            levels[levelIndex++] = Arrays.copyOf(levelNodes, count);
            queue = nextQueue;
        }

        // Возвращаем только заполняемые уровни
        return Arrays.copyOf(levels, levelIndex);
    }
}