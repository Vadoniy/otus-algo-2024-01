package hw_11.tree.splay;

public class SplayTree<T extends Comparable<T>> {

    public Node<T> getRoot() {
        return root;
    }

    private Node<T> root;

//    Splay операция: перемещение вершины в корень при помощи последовательного выполнения трёх операций: Zig, Zig-Zig и Zig-Zag
    private Node<T> splay(Node<T> root, T key) {
        if (root == null || root.getKey().equals(key)) {
            return root;
        }

//        Если новый ключ меньше корня (слева от корня)
        if (key.compareTo(root.getKey()) < 0) {
            if (root.getLeft() == null) {
                return root;
            }

//            Zig-Zig (Left Left)
            if (key.compareTo(root.getLeft().getKey()) < 0) {
                root.getLeft().setLeft(splay(root.getLeft().getLeft(), key));
                root = rotateRight(root);
            }
//            Zig-Zag (Left Right)
            else if (key.compareTo(root.getLeft().getKey()) > 0) {
                root.getLeft().setRight(splay(root.getLeft().getRight(), key));
                if (root.getLeft().getRight() != null) {
                    root.setLeft(rotateLeft(root.getLeft()));
                }
            }

            return root.getLeft() == null ? root : rotateRight(root);
        }

//        Если новый ключ больше корня (справа от корня)
        else {
            if (root.getRight() == null) {
                return root;
            }

//            Zig-Zig (Right Right)
            if (key.compareTo(root.getRight().getKey()) > 0) {
                root.getRight().setRight(splay(root.getRight().getRight(), key));
                root = rotateLeft(root);
            }
//            Zig-Zag (Right Left)
            else if (key.compareTo(root.getRight().getKey()) < 0) {
                root.getRight().setLeft(splay(root.getRight().getLeft(), key));
                if (root.getRight().getLeft() != null) {
                    root.setRight(rotateRight(root.getRight()));
                }
            }

            return root.getRight() == null ? root : rotateLeft(root);
        }
    }

    //    Правый поворот
    private Node<T> rotateRight(Node<T> y) {
        final var x = y.getLeft();
        y.setLeft(x.getRight());
        x.setRight(y);
        return x;
    }

    //    Левый поворот
    private Node<T> rotateLeft(Node<T> x) {
        final var y = x.getRight();
        x.setRight(y.getLeft());
        y.setLeft(x);
        return y;
    }

    public boolean search(T key) {
        root = splay(root, key);
        return root != null && root.getKey().equals(key);
    }

    public void insert(T key) {
        if (root == null) {
            root = new Node<>(key);
            return;
        }

        root = splay(root, key);

        if (root.getKey().equals(key)) {
            return; // Т к такой ключ уже есть
        }

        final var newNode = new Node<>(key);

        if (key.compareTo(root.getKey()) < 0) {
            newNode.setRight(root);
            newNode.setLeft(root.getLeft());
            root.setLeft(null);
        } else {
            newNode.setLeft(root);
            newNode.setRight(root.getRight());
            root.setRight(null);
        }

        root = newNode;
    }

    public void delete(T key) {
        if (root == null) {
            return;
        }

        root = splay(root, key);

        if (!root.getKey().equals(key)) {
            return; // Т е ключа нет в дереве
        }

        if (root.getLeft() == null) {
            root = root.getRight();
        } else {
            Node<T> temp = root.getRight();
            root = root.getLeft();
            splay(root, key); // Перенос самого большого ключа влево
            root.setRight(temp);
        }
    }

    //    Обход дерева
    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node<T> node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(node.getKey() + " ");
            inOrderTraversal(node.getRight());
        }
    }
}
