package hw_3;

import utils.FilesTest;

import java.util.ArrayList;

import static utils.FilesTest.NOT_SUCCESS;
import static utils.FilesTest.SUCCESS;

public class PrimeNumbers {

    public static void main(String[] args) {
        final var filesTest = new FilesTest();
        final var testFileByPath = filesTest.getTestFileByPath("hw_3/primes");
        final var sbResult = new StringBuffer();
        final var primes = new PrimeNumbers();

        testFileByPath.forEach((aLong, aLong2) -> {
            sbResult.append("----------------------------------------------\n")
                    .append("Max number by task is : ")
                    .append(aLong)
                    .append("\n");
            long l = primes.countPrimesTillTheN(aLong.intValue());
            sbResult.append("Amount of primes: ")
                    .append(l)
                    .append("\n")
                    .append("Expected result: ")
                    .append(aLong2)
                    .append("\n")
                    .append(l == aLong2 ? SUCCESS : NOT_SUCCESS)
                    .append("\n");
        });
        System.out.println(sbResult.append("----------------------------------------------"));
        System.out.println(sbResult.toString().contains(NOT_SUCCESS) ? "WARNING! Errors in the result"
                : "All the tests passed successfully");
    }

    public long countPrimesTillTheN(int limit) {
        var isComposite = new boolean[limit + 1];

        for (int i = 4; i <= limit; i += 2) {
            isComposite[i] = true;
        }

        var nextPrime = 3;
        var stopAt = (int) Math.sqrt(limit);

        while (nextPrime <= stopAt) {
            // Исключаем числа, кратные данному простому числу.
            for (int i = nextPrime * 2; i <= limit; i = i + nextPrime) {
                isComposite[i] = true;
            }
            nextPrime = nextPrime + 2;

            while (nextPrime <= limit && isComposite[nextPrime]) {
                nextPrime = nextPrime + 2;
            }
        }

        var primes = new ArrayList<Integer>();

        for (int i = 2; i <= limit; i++) {
            if (!isComposite[i]) {
                primes.add(i);
            }
        }
        return primes.size();
    }
}
