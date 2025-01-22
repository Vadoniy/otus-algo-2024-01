package hw_11.tree.b;

import java.util.ArrayList;
import java.util.List;

class Node<T extends Comparable<T>> {
    private final int degree; // Минимальная степень (порядок дерева)
    private final List<T> keys;
    private final List<Node<T>> children;
    private final boolean isLeaf;

    public Node(int degree, boolean isLeaf) {
        this.degree = degree;
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    // Вставка ключа в узел (без разделения)
    public void insertNonFull(T key) {
        int i = keys.size() - 1;

        if (isLeaf) {
            // Найти позицию для нового ключа и вставить
            while (i >= 0 && key.compareTo(keys.get(i)) < 0) {
                i--;
            }
            keys.add(i + 1, key);
        } else {
            // Найти поддерево для спуска
            while (i >= 0 && key.compareTo(keys.get(i)) < 0) {
                i--;
            }
            i++;

            if (children.get(i).getKeys().size() == 2 * degree - 1) {
                splitChild(i);

                if (key.compareTo(keys.get(i)) > 0) {
                    i++;
                }
            }
            children.get(i).insertNonFull(key);
        }
    }

    // Разделение переполненного дочернего узла
    public void splitChild(int i) {
        Node<T> fullChild = children.get(i);
        Node<T> newChild = new Node<>(degree, fullChild.isLeaf);

        // Новый узел получает t-1 ключей из полного узла
        for (int j = 0; j < degree - 1; j++) {
            newChild.getKeys().add(fullChild.getKeys().remove(degree));
        }

        if (!fullChild.isLeaf) {
            // Новый узел получает t дочерних указателей
            for (int j = 0; j < degree; j++) {
                newChild.getChildren().add(fullChild.getChildren().remove(degree));
            }
        }

        // Добавить новый узел к дочерним узлам текущего узла
        children.add(i + 1, newChild);

        // Поднять средний ключ в текущий узел
        keys.add(i, fullChild.getKeys().remove(degree - 1));
    }

    // Поиск ключа в поддереве
    public boolean search(T key) {
        int i = 0;
        while (i < keys.size() && key.compareTo(keys.get(i)) > 0) {
            i++;
        }

        if (i < keys.size() && key.equals(keys.get(i))) {
            return true;
        }

        if (isLeaf) {
            return false;
        }

        return children.get(i).search(key);
    }

    public void traverse() {
        int i;
        for (i = 0; i < keys.size(); i++) {
            if (!isLeaf) {
                children.get(i).traverse();
            }
            System.out.print(keys.get(i) + " ");
        }
        if (!isLeaf) {
            children.get(i).traverse();
        }
    }

    public List<T> getKeys() {
        return keys;
    }

    public List<Node<T>> getChildren() {
        return children;
    }
}