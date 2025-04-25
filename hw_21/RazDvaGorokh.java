package hw_21;

import utils.FilesTest;

import java.io.File;
import java.io.IOException;

public class RazDvaGorokh {

    public static void main(String[] args) throws IOException {
        final var testsFolder = new File("hw_21/tests/raz_dva_gorokh");
        final var testCases = FilesTest.loadTestCases(testsFolder);

        testCases.forEach((s, s2) -> {
                    final var result = sumFractions(s);
                    System.out.println("Summing " + s + "...\n" + "   Actual result: " + result + "; expected result: " + s2);
                    if (!result.equalsIgnoreCase(s2)) {
                        throw new RuntimeException("Wrong result when summing " + s + ": " + result + ", when expected " + s2);
                    }
                }
        );

    }

    public static String sumFractions(String input) {
        final var parts = input.split("\\+");
        final var fraction1 = parts[0].split("/");
        final var fraction2 = parts[1].split("/");
        final var fraction1_numerator = Integer.parseInt(fraction1[0]);
        final var fraction1_denominator = Integer.parseInt(fraction1[1]);
        final var fraction2_numerator = Integer.parseInt(fraction2[0]);
        final var fraction2_denominator = Integer.parseInt(fraction2[1]);

        var commonNumerator = fraction1_numerator * fraction2_denominator + fraction2_numerator * fraction1_denominator;
        var commonDenominator = fraction1_denominator * fraction2_denominator;

        final var gcd = greatestCommonDenominator(commonNumerator, commonDenominator);
        commonNumerator = commonNumerator / gcd;
        commonDenominator = commonDenominator / gcd;

        return commonNumerator + "/" + commonDenominator;
    }

    private static int greatestCommonDenominator(int numerator, int denominator) {
        while (denominator != 0) {
            final var tmp = denominator;
            denominator = numerator % denominator;
            numerator = tmp;
        }
        return numerator;
    }
}
