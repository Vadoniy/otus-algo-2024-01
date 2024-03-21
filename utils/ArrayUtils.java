package utils;

import java.util.Random;

public class ArrayUtils {

    public static int[] generateIntArray(int amountOfElements) {
        final var arr = new int[amountOfElements];
        final var random = new Random(12345);

        for (int i = 0; i < amountOfElements; i++) {
            arr[i] = random.nextInt(1000);
        }

        return arr;
    }
}
