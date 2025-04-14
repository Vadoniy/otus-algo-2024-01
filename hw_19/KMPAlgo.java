package hw_19;

import utils.FindTargetAlgo;

public class KMPAlgo extends FindTargetAlgo {
    public boolean findTarget(String text, String targetText) {
        final var lps = computeLPSArray(targetText);
        final var textLength = text.length();
        final var targetTextLength = targetText.length();
        var textIndex = 0;
        var targetTextIndex = 0;

        while (textIndex < textLength) {
            cmp++;
            if (text.charAt(textIndex) == targetText.charAt(targetTextIndex)) {
                textIndex++;
                targetTextIndex++;
                if (targetTextIndex == targetTextLength) {
                    return true;
                }
            } else {
                if (targetTextIndex != 0) {
                    targetTextIndex = lps[targetTextIndex - 1]; // откат назад по шаблону
                } else {
                    textIndex++;
                }
            }
        }
        return false;
    }

    // Префикс-функция на каждой позиции — длина наибольшего префикса, совпадающего с суффиксом
    private int[] computeLPSArray(String targetText) {
        final var targetTextLength = targetText.length();
        final var lps = new int[targetTextLength];
        var previousMatchPrefixLength = 0; // длина предыдущего совпавшего префикса
        var indexOfCharInPattern = 1; //начинаем с 1, т к на 0-м шаге символ не может сравниваться с предыдущим символом

        while (indexOfCharInPattern < targetTextLength) {
            if (targetText.charAt(indexOfCharInPattern) == targetText.charAt(previousMatchPrefixLength)) {
                previousMatchPrefixLength++;
                lps[indexOfCharInPattern] = previousMatchPrefixLength;
                indexOfCharInPattern++;
            } else {
                if (previousMatchPrefixLength != 0) {
                    previousMatchPrefixLength = lps[previousMatchPrefixLength - 1]; // сдвигаемся назад
                } else {
                    lps[indexOfCharInPattern] = 0;
                    indexOfCharInPattern++;
                }
            }
        }
        return lps;
    }
}