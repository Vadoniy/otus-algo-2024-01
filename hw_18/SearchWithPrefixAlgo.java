package hw_18;

public class SearchWithPrefixAlgo extends FindTargetAlgo {

    public boolean findTarget(String text, String targetText) {
        var prefix = buildPrefixFunction(targetText);
        var textIndex = 0;
        var targetIndex = 0;

        while (textIndex < text.length()) {
            if (text.charAt(textIndex) == targetText.charAt(targetIndex)) {
                textIndex++;
                targetIndex++;
                // Совпадение найдено, если достигнут конец шаблона
                if (targetIndex == targetText.length()) {
                    return true;
                }
            } else {
                if (targetIndex == 0) {
                    textIndex++;
                } else {
                    targetIndex = prefix[targetIndex - 1]; // Сдвиг по префикс-функции
                }
            }
            cmp++;
        }

        return false;
    }

    private int[] buildPrefixFunction(String pattern) {
        int[] prefix = new int[pattern.length()];
        for (int i = 1, j = 0; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            prefix[i] = j;
        }
        return prefix;
    }
}
