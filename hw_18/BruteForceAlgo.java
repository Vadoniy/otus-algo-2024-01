package hw_18;

import utils.FindTargetAlgo;

public class BruteForceAlgo extends FindTargetAlgo {
    public boolean findTarget(String text, String targetText) {
        var textArr = text.toCharArray();
        var targetTextArr = targetText.toCharArray();
        var startPosition = 0;
        var maxPosition = text.length() - targetText.length();
        var matchLength = 0;

        while (startPosition <= maxPosition) {
            for (int i = 0; i < targetText.length(); i++) {
                cmp++;
                if (textArr[startPosition + i] == targetTextArr[i]) {
                    matchLength++;
                } else {
                    matchLength = 0;
                    break;
                }
                if (matchLength == targetText.length()) {
                    return true;
                }
            }
            startPosition++;
        }
        return false;
    }
}
