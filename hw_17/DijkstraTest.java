package hw_17;

public class DijkstraTest {

    public static void main(String[] args) {
        int[][] graph =
                {
                        {0, 7, 9, 0, 0, 14},
                        {7, 0, 10, 15, 0, 0},
                        {9, 10, 0, 11, 0, 2},
                        {0, 15, 11, 0, 6, 0},
                        {0, 0, 0, 6, 0, 9},
                        {14, 0, 2, 0, 9, 0},
                };

        final var dijkstra = new Dijkstra();

        for (var vertex = 0; vertex < graph.length; vertex++) {
            final var distances = dijkstra.sort(graph, 6, vertex);

            System.out.println("Минимальные пути из вершины " + (vertex + 1) + ":");
            for (var i = 0; i < graph.length; i++) {
                // Выводим расстояние от вершины vertex до каждой из других вершин
                if (distances[i] == 1_000_000_000) {
                    System.out.println("До вершины " + (i + 1) + ": нет пути");
                } else {
                    System.out.println("До вершины " + (i + 1) + ": " + distances[i]);
                }
            }
            System.out.println();
        }
    }
}
