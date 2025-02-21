package hw_17;

public class Dijkstra {

    public Edge[] sort(int[][] graph, int vertexCount, int startVertex) {
        final var distances = new int[vertexCount];
        final var visited = new boolean[vertexCount];
        final var edges = new Edge[vertexCount - 1]; // Максимум N-1 рёбер
        var edgeIndex = 0;

        for (var i = 0; i < vertexCount; i++) {
            distances[i] = 1_000_000_000;
        }
        distances[startVertex] = 0;

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
                }
            }
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
}