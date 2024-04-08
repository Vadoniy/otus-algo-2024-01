package utils;

import hw_10.Node;

public class BTreeUtils {

    public static void printNode(Node node) {
        while (node != null) {
            printNode(node.getLeft());
            System.out.println(node.getValue());
            printNode(node.getRight());
            return;
        }
    }
}
