package hw_10;

import java.util.Objects;

public class BinarySearchTree {

    private Node root;
    private int insertionCounter = 0;
    private int collisionCounter = 0;

    public void insert(int value) {
        root = insert(value, root);
        insertionCounter++;
    }

    public boolean search(int x) {
        return searchR(x, root);
    }

    public void remove(Integer x) {
        removeR(x, root);
    }

    private Node insert(int value, Node node) {
        if (node == null) {
            return new Node(value);
        }

        if (value > node.getValue()) {
            node.setRight(insert(value, node.getRight()));
        } else if (value < node.getValue()) {
            node.setLeft(insert(value, node.getLeft()));
        } else {
//            System.out.println("Values are equal, no action is required");
            collisionCounter++;
        }

        return node;
    }

    private boolean searchR(Integer x, Node node) {
        if (node == null) {
            return false;
        }
        if (Objects.equals(x, node.getValue())) {
            return true;
        }
        if (x > node.getValue()) {
            return searchR(x, node.getRight());
        } else {
            return searchR(x, node.getLeft());
        }
    }

    private Node removeR(Integer x, Node node) {
//        parent node является листом, листья можно удалять без лишних манипуляция
        if (node == null) {
            return null;
        }

//        удаляемый параметр меньше текущего значения узла => "спускаемся" влево
        if (x < node.getValue()) {
            node.setLeft(removeR(x, node.getLeft()));
            return node;
        }
//        удаляемый параметр больше текущего значения узла => "спускаемся" вправо
        else if (x > node.getValue()) {
            node.setRight(removeR(x, node.getRight()));
            return node;
        }
//        В коде ниже мы попали на тот узел, значение которого равно x и мы хотим его удалить
        else {
//        Если "левого" (мЕньшего по значению) потомка нет, то на место удаляемого узла мы ставим "правого" потомка - бОльшего
            if (node.getLeft() == null) {
                return node.getRight();
            } else
//        Если "левый" (мЕньший по значению) потомок есть, но нет "правого" (бОльшего),
//        то на место удаляемого узла мы ставим "левого" потомка - мЕньшего
                if (node.getRight() == null) {
                    return node.getLeft();
                }
//        Если у удаляемого узла оба потомка не null, то на место удаляемого значения необходимо поставить либо самый
//        большой элемент из левого поддерева, либо самый маленький из правого поддерева. В таком случае на месте удалённого
//        элемента будет элемент, у которого все элементы слева будут меньше него по значению, а справа - больше и
//        все свойства бинарного дерева поиска сохранятся
            node.setValue(minimumRightNode(node.getRight()));
//        После того, как мы поставили на место удаляемого элемента минимальное значение из правого или максимальное значение
//        из левого поддерева, нам надо удалить этот элемент из правого или левого поддерева соответственно. Т е на этом этапе
//        node уже содержит не то значение, которое было на входе в метод, а которое было получено при поиске минимального/максимального
//        значения в поддеревьях.
            node.setRight(removeR(node.getValue(), node.getRight()));

//        Можно раскомментировать следующие 2 строки, чтобы замена произошла максимальным элементом слева, а не минимальным справа
//            node.setValue(maximumLeftNode(node.getLeft()));
//            node.setLeft(removeR(node.getValue(), node.getLeft()));
        }

        return node;
    }

    private Integer minimumRightNode(Node node) {
        Integer minValue = node.getValue();
        while (node.getLeft() != null) {
            minValue = node.getLeft().getValue();
            node = node.getLeft();
        }
        return minValue;
    }

    private Integer maximumLeftNode(Node node) {
        Integer maxValue = node.getValue();
        while (node.getRight() != null) {
            maxValue = node.getRight().getValue();
            node = node.getRight();
        }
        return maxValue;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getInsertionCounter() {
        return insertionCounter;
    }

    public int getCollisionCounter() {
        return collisionCounter;
    }
}
