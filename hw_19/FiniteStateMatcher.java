package hw_19;

import utils.FindTargetAlgo;

public class FiniteStateMatcher extends FindTargetAlgo {

    public boolean findTarget(String text, String targetText) {
        final var delta = buildTransitionTable(targetText);
        final var m = targetText.length();
        var state = 0;

        for (int i = 0; i < text.length(); i++) {
            cmp++;
            state = delta[state][text.charAt(i)];

            if (state == m) {
                return true;
            }
        }

        return false;
    }

    //Строим таблицу переходов размером "длина шаблона"*"количество символов"
    public int[][] buildTransitionTable(String targetText) {
        final var m = targetText.length();
        final var delta = new int[m + 1][ALPHABET_SIZE];

        //Для каждого состояния от q до m ищем максимальный суффикс, который доступен в шаблоне
        for (int q = 0; q <= m; q++) {
            for (int a = 0; a < ALPHABET_SIZE; a++) {
                var k = Math.min(m, q + 1);
                while (k > 0 && !isSuffix(targetText.substring(0, k), targetText.substring(0, q) + (char) a)) {
                    k--;
                }
                delta[q][a] = k;
            }
        }

        return delta;
    }

    private boolean isSuffix(String suffix, String fullText) {
        return fullText.endsWith(suffix);
    }
}