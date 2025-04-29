package hw_20;

import java.util.Arrays;

public class RleTestApplication {
    public static void main(String[] args) {
        final var rle = new Rle();

        // Тестовый массив
        byte[] originalArray = {5, 5, 5, 7, 7, 8, 9, 9, 9, 9};
        final var originalLength = originalArray.length;

        System.out.println("Original array:");
        System.out.println(Arrays.toString(originalArray));

        // Тест simpleEncode
        final var simpleEncoded = rle.simpleEncode(originalArray, originalLength);
        System.out.println("\nSimple Encoded:");
        System.out.println(Arrays.toString(simpleEncoded));

        // Тест simpleDecode
        final var simpleDecoded = rle.simpleDecode(simpleEncoded, simpleEncoded.length);
        System.out.println("\nSimple Decoded:");
        System.out.println(Arrays.toString(simpleDecoded));

        if (Arrays.equals(originalArray, simpleDecoded)) {
            System.out.println("\nSimple Encode/Decode match: true");
        } else {
            throw new RuntimeException("\nSimple Encode/Decode match: failed");
        }

        // Тест improvedEncode
        final var improvedEncoded = rle.improvedEncode(originalArray, (byte) (Byte.MAX_VALUE - 1));
        System.out.println("\nImproved Encoded:");
        System.out.println(Arrays.toString(improvedEncoded));

        // Тест improvedDecode
        final var improvedDecoded = rle.improvedDecode(improvedEncoded);
        System.out.println("\nImproved Decoded:");
        System.out.println(Arrays.toString(improvedDecoded));

        if (Arrays.equals(originalArray, improvedDecoded)) {
            System.out.println("\nImproved Encode/Decode match: true");
        } else {
            throw new RuntimeException("Improved Encode/Decode match: failed");
        }
    }
}
