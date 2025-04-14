package hw_19;

import java.util.List;

public class FindTargetAppTest {
    private final static String RESULT_PATTERN = "Result of %s algorithm - %s; amount o attempts: %s";

    public static void main(String[] args) {
        final var text0 = "KOLOKOL";
        final var text1 = "KOLKOKOLOKOLL";
        final var text2 = "DHJRGTNCISERUHGILESURHGINKEURHGIERUHGOIRUHAOIHROIFBQWIROUQIWOEUHRIHRQWEIHKOLKOKOLOKOLLAWOIAER" +
                "TOJEICTUILERUTKCLEISRUTOPKISEURT";
        final var text3 = "DHJRGTNCISERUHGILESURHGINKEURHGIERDHJDHJRGTNCISERUHGILESURHGINKEURHGIERUHGOIRUHAOIHROIFBQWIRO" +
                "UQIWOEUHRIHRQWEIHKOLKOKORGTNCISERUHGILESURHGINKEURHGIERUHGOIRUHAOIHROIFBQWIROUQI" +
                "WOEUHRIHRQWEIHKOLKOKOUHGDHJRGTNCISERUHGILESURHGINKEURHGIERUHGOIRUHAOIHROIFBQWIRO" +
                "UQIWOEUHRIHRQWEIHKOLKOKOOIRUHAOIHROIFBQWIROUQIWOEUHRIHRQWEIHKOLKOKOLOKOLLAWOIAERTOJEICTUILERUTKCLEISRUT" +
                "OPKISEURT";
        final var targetText = "KOLOKOL";
        final var texts = List.of(text0, text1, text2, text3);
        final var findersList = List.of(new FiniteStateMatcher(), new KMPAlgo());

        for (String text : texts) {
            System.out.println("--------------------------------------------");
            System.out.println("Text(length = " + text.length() + "): " + text);
            System.out.println("Text to find (length = " + targetText.length() + "): " + targetText);


            for (var finder : findersList) {
                System.out.printf((RESULT_PATTERN) + "%n",
                        finder.getClass().getSimpleName(),
                        finder.check(text, targetText),
                        finder.getCmp());
            }
        }
    }
}
