package hw_11;

import hw_11.tree.b.BTree;

import java.util.Random;

public class BTreeTest {
    public static void main(String[] args) {
        final var random = new Random();
        final var bTree = new BTree<Integer>(random.nextInt(5)); // Порядок дерева

//        Создаём значение, которое добавим в дерево и потом найдём
        final var valueToFindTrue = random.nextInt(100);

//        Наполняем дерево значениями от 0 до 100
        bTree.insert(random.nextInt(100));
        bTree.insert(random.nextInt(100));
        bTree.insert(random.nextInt(100));
        bTree.insert(random.nextInt(100));
        bTree.insert(random.nextInt(100));
        bTree.insert(valueToFindTrue);
        bTree.insert(random.nextInt(100));
        bTree.insert(random.nextInt(100));
        bTree.insert(random.nextInt(100));
        bTree.insert(random.nextInt(100));


        System.out.println("Traversal of the constructed B-Tree:");
        bTree.traverse();

//        Создаём значение, которое не должно быть найдено в дереве
        final var valueToFindFalse = 101;

        System.out.println("Searching for " + valueToFindFalse + ": " + bTree.search(valueToFindFalse));

        if (bTree.search(valueToFindFalse)) {
            throw new RuntimeException(valueToFindFalse + " mustn't be in the tree");
        }

        System.out.println("Searching for " + valueToFindTrue + ": " + bTree.search(valueToFindTrue));

        if (!bTree.search(valueToFindTrue)) {
            throw new RuntimeException(valueToFindTrue + " must be in the tree");
        }
    }
}
