package hw_17;

public class Dijkstra {

    public Edge[] sort(int[][] graph, int vertexCount, int startVertex) {
        return this.sort(graph, vertexCount, startVertex, null);
    }

    public Edge[] sort(int[][] graph, int vertexCount, int startVertex, Integer vertexToFind) {
        final var distances = new int[vertexCount];
        final var visited = new boolean[vertexCount];
        final var edges = new Edge[vertexCount - 1]; // Максимум N-1 рёбер
        var edgeIndex = 0;

        final var prev = new int[vertexCount]; // Массив для хранения пути (предыдущих вершин(
        for (var i = 0; i < vertexCount; i++) {
            prev[i] = -1;
        }

        for (var i = 0; i < vertexCount; i++) {
            distances[i] = 1_000_000_000;
        }
        distances[startVertex - 1] = 0;

        for (var k = 0; k < vertexCount; k++) {
            final var minVertex = getMinDistanceVertex(distances, visited);

            if (minVertex == -1) {
                break;
            }

            visited[minVertex] = true;

            for (var j = 0; j < graph[minVertex].length; j++) {
                var neighbor = graph[minVertex][j];
                if (neighbor == -1) {
                    break;
                }

                neighbor = neighbor - 1; //Переходим на индексацию с 0

                if (!visited[neighbor] && distances[minVertex] + 1 < distances[neighbor]) {
                    distances[neighbor] = distances[minVertex] + 1;
                    edges[edgeIndex++] = new Edge(minVertex + 1, neighbor + 1); // 1 - индексация
                    prev[neighbor] = minVertex; // Запоминаем, из какой вершины пришли в текущую
                }
            }

            if (vertexToFind != null && minVertex == vertexToFind - 1) {
                break; //Нашли нужную вершину, выходим
            }
        }
        if (vertexToFind != null) {
            return constructPath(prev, startVertex - 1, vertexToFind - 1);
        }
        return edges;
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

    private Edge[] constructPath(int[] prev, int start, int target) {
        if (prev[target] == -1) {
            return new Edge[0]; // Если пути нет
        }

        final var path = new Edge[prev.length];
        var edgeIndex = 0;
        var current = target;

        while (current != start) {
            if (prev[current] == -1) {
                return new Edge[0];
            }

            path[edgeIndex++] = new Edge(prev[current] + 1, current + 1); // Возвращаем индексацию к 1
            current = prev[current];
        }

        // Разворачиваем массив рёбер, чтобы он шёл в порядке от первой вершины к последней
        final var result = new Edge[edgeIndex];
        for (int i = 0; i < edgeIndex; i++) {
            result[i] = path[edgeIndex - 1 - i];
        }

        return result;
    }
}