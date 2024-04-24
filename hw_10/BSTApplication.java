package hw_10;

import java.util.ArrayList;
import java.util.Random;

public class BSTApplication {

    private static final int RANDOM_TEST_AMOUNT_OF_ELEMENTS = 32_000_000;
    private static final int ORDERED_TEST_AMOUNT_OF_ELEMENTS = 45_000;

    public static void main(String[] args) {
        final var bstRandom = new BinarySearchTree();
        final var random = new Random();

        var list = new ArrayList<Integer>(RANDOM_TEST_AMOUNT_OF_ELEMENTS);
        for (int i = 0; i < RANDOM_TEST_AMOUNT_OF_ELEMENTS; i++) {
            list.add(random.nextInt());
        }


        var start = System.currentTimeMillis();
        list.forEach(bstRandom::insert);
        var stop = System.currentTimeMillis();
        System.out.println("========================");
        System.out.println("Amount of insertions RANDOM: " + bstRandom.getInsertionCounter());
        System.out.println("Amount of collisions RANDOM: " + bstRandom.getCollisionCounter());
        System.out.println("Time of insertions RANDOM: " + (stop - start));
        System.out.println("========================");


        start = System.currentTimeMillis();
        for (int i = 0; i < RANDOM_TEST_AMOUNT_OF_ELEMENTS/10; i++) {
            bstRandom.search(random.nextInt());
        }
        stop = System.currentTimeMillis();
        System.out.println("Time of SEARCH of " + RANDOM_TEST_AMOUNT_OF_ELEMENTS/10
                + " random elements from RANDOM tree: " + (stop - start));
        System.out.println("========================");

        start = System.currentTimeMillis();
        for (int i = 0; i < RANDOM_TEST_AMOUNT_OF_ELEMENTS/10; i++) {
            bstRandom.remove(random.nextInt());
        }
        stop = System.currentTimeMillis();
        System.out.println("Time of REMOVE of " + RANDOM_TEST_AMOUNT_OF_ELEMENTS/10
                + " random elements from RANDOM tree: " + (stop - start));
        System.out.println("========================");





        final var bstOrdered = new BinarySearchTree();
        list = new ArrayList<>(ORDERED_TEST_AMOUNT_OF_ELEMENTS);
        for (int i = 0; i < ORDERED_TEST_AMOUNT_OF_ELEMENTS; i++) {
            list.add(random.nextInt());
        }
        final var sortedList = list.stream().sorted().toList();

        start = System.currentTimeMillis();
        sortedList.forEach(bstOrdered::insert);
        stop = System.currentTimeMillis();

        System.out.println("========================");
        System.out.println("Amount on insertions ORDERED: " + bstOrdered.getInsertionCounter());
        System.out.println("Amount on collisions ORDERED: " + bstOrdered.getCollisionCounter());
        System.out.println("Time of insertions ORDERED: " + (stop - start));
        System.out.println("========================");

        start = System.currentTimeMillis();
        for (int i = 0; i < ORDERED_TEST_AMOUNT_OF_ELEMENTS/10; i++) {
            bstOrdered.search(random.nextInt());
        }
        stop = System.currentTimeMillis();
        System.out.println("Time of search of " + ORDERED_TEST_AMOUNT_OF_ELEMENTS/10
                + " random elements from ORDERED tree: " + (stop - start));
        System.out.println("========================");

        start = System.currentTimeMillis();
        for (int i = 0; i < ORDERED_TEST_AMOUNT_OF_ELEMENTS/10; i++) {
            bstOrdered.remove(random.nextInt());
        }
        stop = System.currentTimeMillis();
        System.out.println("Time of REMOVE of " + ORDERED_TEST_AMOUNT_OF_ELEMENTS/10
                + " random elements from ORDERED tree: " + (stop - start));
        System.out.println("========================");
    }
}

//        final var bst = new BinarySearchTree();
//        final var valueToFind = 12;
//        final var notInTheTreeValue = 2000;
//        System.out.println("========================");
//
//        bst.insert(9);
//        bst.insert(6);
//        bst.insert(17);
//        bst.insert(3);
//        bst.insert(8);
//        bst.insert(16);
//        bst.insert(20);
//        bst.insert(1);
//        bst.insert(4);
//        bst.insert(7);
//        bst.insert(12);
//        bst.insert(19);
//        bst.insert(21);
//        bst.insert(2);
//        bst.insert(5);
//        bst.insert(11);
//        bst.insert(14);
//        bst.insert(18);
//        bst.insert(10);
//        bst.insert(13);
//        bst.insert(15);
//
//        System.out.println("========================");
//        BTreeUtils.printNode(bst.getRoot());
//        System.out.println("========================");
//        System.out.println("Is value " + valueToFind + " is in the tree: " + bst.search(valueToFind));
//        System.out.println("========================");
//        System.out.println("Is value " + notInTheTreeValue + " is in the tree: " + bst.search(notInTheTreeValue));
//        System.out.println("========================");
//        System.out.println("Remove " + valueToFind + " from the tree");
//        bst.remove(valueToFind);
//        System.out.println("========================");
//        BTreeUtils.printNode(bst.getRoot());
//        System.out.println("========================");