package hw_18;

import utils.FindTargetAlgo;

import java.util.Arrays;

public class BoyerMooreAlgo extends FindTargetAlgo {
    public boolean findTarget(String text, String targetText) {
        var badChar = preprocessBadCharacter(targetText);
        var m = targetText.length();
        var n = text.length();

        var shift = 0; // Смещение шаблона относительно текста
        while (shift <= (n - m)) {
            var j = m - 1;

            while (j >= 0 && targetText.charAt(j) == text.charAt(shift + j)) {
                j--;
            }

            cmp++;
            if (j < 0) {
                return true;
            } else {
                shift += Math.max(1, j - badChar[text.charAt(shift + j)]);
            }
        }
        return false;
    }

    private int[] preprocessBadCharacter(String pattern) {
        var badChar = new int[ALPHABET_SIZE];
        Arrays.fill(badChar, -1);

        for (int i = 0; i < pattern.length(); i++) {
            badChar[pattern.charAt(i)] = i;
        }
        return badChar;
    }
}