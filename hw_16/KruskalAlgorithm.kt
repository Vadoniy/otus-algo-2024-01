package hw_16

class KruskalAlgorithm {
    fun sort(graph: Array<IntArray>, n: Int): Array<Edge> {
        val edges = mutableListOf<Edge>()
        val addedEdges = mutableSetOf<Pair<Int, Int>>()

        for (i in graph.indices) {
            for (j in graph[i].indices step 2) {
                val neighbor = graph[i][j]
                if (neighbor == -1 || j + 1 >= graph[i].size) continue

                val edgePair = if (i + 1 < neighbor) Pair(i + 1, neighbor) else Pair(neighbor, i + 1)
                if (edgePair !in addedEdges) {
                    edges.add(Edge(i + 1, neighbor))
                    addedEdges.add(edgePair)
                }
            }
        }

        edges.sortBy { it.v1 }

        val uf = UnionFind(n)
        val mst = mutableListOf<Edge>()

        for (edge in edges) {
            if (uf.union(edge.v1 - 1, edge.v2 - 1)) {
                mst.add(edge)
                if (mst.size == n - 1) break
            }
        }

        return mst.toTypedArray()
    }
}