package hw_17;

public class Dijkstra {

    public int[] sort(int[][] graph, int vertexCount, int startVertex) {
        final var distances = new int[vertexCount];
        final var visited = new boolean[vertexCount];

        // Инициализация расстояний
        for (var i = 0; i < vertexCount; i++) {
            distances[i] = 1_000_000_000;
        }
        distances[startVertex] = 0;

        // Основной цикл алгоритма
        for (var k = 0; k < vertexCount; k++) {
            final var minVertex = getMinDistanceVertex(distances, visited);
            if (minVertex == -1) {
                break; // Если остались недостижимые вершины
            }

            visited[minVertex] = true;
            for (var neighbor = 0; neighbor < vertexCount; neighbor++) {
                if (!visited[neighbor] && graph[minVertex][neighbor] != 0) {
                    final var newDistance = distances[minVertex] + graph[minVertex][neighbor];
                    if (newDistance < distances[neighbor]) {
                        distances[neighbor] = newDistance;
                    }
                }
            }
        }
        return distances;
    }

    private int getMinDistanceVertex(int[] distances, boolean[] visited) {
        var minIndex = -1;
        for (var i = 0; i < distances.length; i++) {
            if (!visited[i] && (minIndex == -1 || distances[i] < distances[minIndex])) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}