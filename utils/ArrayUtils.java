package utils;

import java.util.Arrays;
import java.util.List;
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

    public static int[] generateIntArray(int amountOfElements, int maxValue) {
        return generateIntArray(amountOfElements, maxValue, false);
    }

    public static int[] generateIntArray(int amountOfElements, int maxValue, boolean isPrint) {
        final var arr = new int[amountOfElements];
        final var random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(maxValue);
        }

        if (isPrint) {
            System.out.println("Created array: " + Arrays.toString(arr));
        }
        return arr;
    }

    public static byte[] toByteArray(List<Byte> byteArrayList) {
        final var result = new byte[byteArrayList.size()];

        for (int i = 0; i < byteArrayList.size(); i++) {
            result[i] = byteArrayList.get(i);
        }

        return result;
    }
}
