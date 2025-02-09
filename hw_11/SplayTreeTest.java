package hw_11;

import hw_11.tree.splay.SplayTree;

import java.util.Random;

public class SplayTreeTest {

    public static void main(String[] args) {
        final var splayTree = new SplayTree<Integer>();
        final var random = new Random();
//        Создаём значение, которое добавим в дерево и потом найдём
        final var valueToFindTrue = random.nextInt(100);

//        Наполняем дерево значениями от 0 до 100
        splayTree.insert(random.nextInt(100));
        splayTree.insert(random.nextInt(100));
        splayTree.insert(random.nextInt(100));
        splayTree.insert(random.nextInt(100));
        splayTree.insert(valueToFindTrue);
        splayTree.insert(random.nextInt(100));
        splayTree.insert(random.nextInt(100));
        splayTree.insert(random.nextInt(100));
        splayTree.insert(random.nextInt(100));
        splayTree.insert(random.nextInt(100));

        System.out.println("Traversal of the constructed splayTree:");
        splayTree.inOrderTraversal();
        var initialRootOfTheTree = splayTree.getRoot().getKey();
        System.out.println("The initial root of the splayTree: " + splayTree.getRoot().getKey());

        System.out.println("Searching for " + valueToFindTrue + ":");
        final var positiveFindResult = splayTree.search(valueToFindTrue);
        System.out.println(positiveFindResult);

        if (!positiveFindResult) {
            throw new RuntimeException(valueToFindTrue + " must be in the tree");
        }

        System.out.println("Traversal after search:");
        splayTree.inOrderTraversal();

        var newRootOfTheTree = splayTree.getRoot().getKey();
        System.out.println("The root of the splayTree after search: " + newRootOfTheTree);
        if (valueToFindTrue != initialRootOfTheTree && valueToFindTrue != newRootOfTheTree) {
            throw new RuntimeException(newRootOfTheTree + " must be changed after search");
        }

        //        Создаём значение, которое не должно быть найдено в дереве
        final var valueToFindFalse = 101;

        System.out.println("Searching for " + valueToFindFalse + ":");
        final var negativeFindResult = splayTree.search(valueToFindFalse);
        System.out.println(negativeFindResult);
        if (negativeFindResult) {
            throw new RuntimeException(valueToFindFalse + " mustn't be in the tree");
        }

        System.out.println("Traversal after search:");
        splayTree.inOrderTraversal();

        System.out.println("Deleting " + valueToFindTrue + ":");
        splayTree.delete(valueToFindTrue);

        System.out.println("Traversal after deletion:");
        splayTree.inOrderTraversal();
    }
}
