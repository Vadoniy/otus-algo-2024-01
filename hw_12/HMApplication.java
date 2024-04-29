package hw_12;

import hw_12.otus_hash_table.HashTable;

public class HMApplication {

    public static final String ONE = "one";
    public static final String TWO = "two";
    public static final String THREE = "three";
    public static final String ONE_TWO = "one_two";
    public static final String TWO_THREE = "two_three";
    public static final String THREE_FOUR = "three_four";

    public static void main(String[] args) {
        final var table = new HashTable<String, String>();
        System.out.println(table.get(ONE) == null && table.getSize() == 0);
        table.put(ONE, ONE_TWO);
        System.out.println(ONE_TWO.equals(table.get(ONE)) && table.getSize() == 1);
        table.put(ONE, TWO_THREE);
        System.out.println(TWO_THREE.equals(table.get(ONE)) && table.getSize() == 1);
        table.remove(ONE);
        System.out.println(table.get(ONE) == null && table.getSize() == 0);
        table.put(ONE, THREE_FOUR);
        System.out.println(THREE_FOUR.equals(table.get(ONE)) && table.getSize() == 1);
        table.remove(ONE);
        System.out.println(table.get(ONE) == null && table.getSize() == 0);
        table.put(ONE, ONE_TWO);
        table.put(TWO, TWO_THREE);
        table.put(THREE, THREE_FOUR);
        System.out.println(
                ONE_TWO.equals(table.get(ONE))
                && TWO_THREE.equals(table.get(TWO))
                && THREE_FOUR.equals(table.get(THREE))
                && table.getSize() == 3);
    }
}
