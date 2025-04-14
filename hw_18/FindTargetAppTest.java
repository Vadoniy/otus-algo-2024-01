package hw_18;

import java.util.List;

public class FindTargetAppTest {
    private final static String RESULT_PATTERN = "Result of %s algorithm - %s; amount o attempts: %s";

    public static void main(String[] args) {
        final var text = "KOLKOKOLOKOLL";
        final var targetText = "KOLOKOL";

        System.out.println("Text: " + text);
        System.out.println("Text to find: " + targetText);

        final var findersList = List.of(new BruteForceAlgo(), new SearchWithPrefixAlgo(), new BoyerMooreAlgo());

        for (var finder : findersList) {
            System.out.printf((RESULT_PATTERN) + "%n",
                    finder.getClass().getSimpleName(),
                    finder.findTarget(text, targetText),
                    finder.getCmp());
        }
    }
}
