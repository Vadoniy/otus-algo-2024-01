package hw_17;

public class DijkstraTest {

    public static void main(String[] args) {
        int[][] graph = {
                {2, 3, 6, -1, -1, -1},  // Вершина 1 → {2, 3, 6}
                {1, 3, 4, -1, -1, -1},  // Вершина 2 → {1, 3, 4}
                {1, 2, 4, 6, -1, -1},   // Вершина 3 → {1, 2, 4, 6}
                {2, 3, 5, -1, -1, -1},  // Вершина 4 → {2, 3, 5}
                {4, 6, -1, -1, -1, -1}, // Вершина 5 → {4, 6}
                {1, 3, 5, -1, -1, -1}   // Вершина 6 → {1, 3, 5}
        };

        final int vertexCount = graph.length;
        final var dijkstra = new Dijkstra();

        for (int i = 1; i < graph.length; i++) {
            final var distances = dijkstra.sort(graph, vertexCount, i);
            System.out.println("Кратчайший путь при старте из вершины " + i + ":");
            for (Edge edge : distances) {
                if (edge != null) {
                    System.out.println(edge);
                }
            }
        }
    }
}