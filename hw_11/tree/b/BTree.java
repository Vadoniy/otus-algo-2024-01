package hw_11.tree.b;

public class BTree<T extends Comparable<T>> {
    private final int degree; // Минимальная степень
    private Node<T> root;

    public BTree(int degree) {
        this.degree = degree;
        this.root = null;
    }

    public void insert(T key) {
        if (root == null) {
            root = new Node<>(degree, true);
            root.getKeys().add(key);
        } else {
            if (root.getKeys().size() == 2 * degree - 1) {
                var newRoot = new Node<T>(degree, false);
                newRoot.getChildren().add(root);
                newRoot.splitChild(0);

                int i = (key.compareTo(newRoot.getKeys().get(0)) > 0) ? 1 : 0;
                newRoot.getChildren().get(i).insertNonFull(key);

                root = newRoot;
            } else {
                root.insertNonFull(key);
            }
        }
    }

    public boolean search(T key) {
        return root != null && root.search(key);
    }

    public void traverse() {
        if (root != null) {
            root.traverse();
            System.out.println();
        }
    }
}