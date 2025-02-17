package hw_16

class KruskalAlgorithmTest

fun main() {
    /*    Граф с лекции
        1-----2
        |    /|\
        |   / | \
        |  /  |  5
        | /   | /
        |/    |/
        3-----4

     */
    val graph = arrayOf(
        intArrayOf(2, 3, -1, -1),    // 1 соединена с 2 и 3
        intArrayOf(1, 3, 4, 5),      // 2 соединена с 1, 3, 4, 5
        intArrayOf(1, 2, 4, -1),     // 3 соединена с 1, 2, 4
        intArrayOf(2, 3, 5, -1),     // 4 соединена с 2, 3, 5
        intArrayOf(2, 4, -1, -1)     // 5 соединена с 2, 4
    )

    val kruskalAlgorithm = KruskalAlgorithm()
    val mst: Array<Edge> = kruskalAlgorithm.sort(graph, 5)

    println("Минимальное остовное дерево:")
    for (edge in mst) {
        println("${edge.v1} - ${edge.v2}")
    }
}
