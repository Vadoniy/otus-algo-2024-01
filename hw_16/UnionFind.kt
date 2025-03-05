package hw_16

class UnionFind(n: Int) {
    private val parent = IntArray(n) { it }
    private val rank = IntArray(n) { 0 }        //"Глубина" дерева

    fun union(x: Int, y: Int): Boolean {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX == rootY) return false  // Вершины уже соединены

        // Union by rank
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY
        } else {
            parent[rootY] = rootX
            rank[rootX]++
        }
        return true
    }

    private fun find(x: Int): Int {
        if (parent[x] != x) parent[x] = find(parent[x])  //Находим корень и сжимаем путь
        return parent[x]
    }
}