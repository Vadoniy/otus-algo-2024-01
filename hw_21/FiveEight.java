package hw_21;

import utils.FilesTest;

import java.io.File;
import java.io.IOException;

public class FiveEight {

    public static void main(String[] args) throws IOException {
        final var testsFolder = new File("hw_21/tests/five_eight");
        final var testCases = FilesTest.loadTestCases(testsFolder);

        testCases.forEach((s, s2) -> {
//            длина числа
            final var numberLength = Integer.parseInt(s);
//            кол-во способов составить число длины numberLength,
//            второй разряд - последняя использованная цифра (0 - 5, 1 - 8),
//            третий разряд - сколько раз подряд последняя использованная цифра уже шла - 1 или 2
            final var dp = new long[numberLength + 1][2][3];
//            Начинаем с длины 1, можно начать с 5 или 8
            dp[1][0][1] = 1; // начинаем с 5
            dp[1][1][1] = 1; // начинаем с 8

            for (int n = 2; n <= numberLength; n++) {
                for (int last = 0; last <= 1; last++) {
                    for (int count = 1; count <= 2; count++) {
                        if (dp[n - 1][last][count] == 0) {
                            continue;
                        }
//                        Продолжаем предыдущую цифру
                        if (count < 2) {//проверяем, что не 3 цифры подряд
                            dp[n][last][count + 1] += dp[n - 1][last][count];
                        }
//                        Меняем цифру
                        final var next = 1 - last;
                        dp[n][next][1] += dp[n - 1][last][count];
                    }
                }
            }

            final var result = dp[numberLength][0][1] + dp[numberLength][0][2] +
                    dp[numberLength][1][1] + dp[numberLength][1][2];

            if (Long.parseLong(s2) == result) {
                System.out.println("Fact = " + result + ", expected = " + s2 + "; result is correct");
            } else {
                throw new RuntimeException("INCORRECT RESULT: expected " + s2 + ",  but was " + result);
            }
        });
    }
}
