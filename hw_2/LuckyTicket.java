package hw_2;

import utils.FilesTest;

import java.util.ArrayList;
import java.util.List;

public class LuckyTicket {
    public static final int MIN = 0;
    public static final int MAX = 9;
    public static final String SUCCESS = "Test passed";
    public static final String NOT_SUCCESS = "Test NOT passed";

    public static void main(String[] args) {
        final var filesTest = new FilesTest();
        final var testFileByPath = filesTest.getTestFileByPath("hw_2/tickets");
        final var sbResult = new StringBuffer();
        final var luckyCounter = new LuckyCounter();

        testFileByPath.forEach((aLong, aLong2) -> {
            sbResult.append("----------------------------------------------\n")
                    .append("Amount of elements: ")
                    .append(aLong)
                    .append("\n");
            luckyCounter.countLuckyTicketsByPreviousTenElements(aLong.intValue());
            sbResult.append("Count result: ")
                    .append(luckyCounter.getCounter())
                    .append("\n")
                    .append("Expected result: ")
                    .append(aLong2)
                    .append("\n")
                    .append(luckyCounter.getCounter() == aLong2 ? SUCCESS : NOT_SUCCESS)
                    .append("\n");
        });
        System.out.println(sbResult.append("----------------------------------------------"));
        System.out.println(sbResult.toString().contains(NOT_SUCCESS) ? "WARNING! Errors in the result"
                : "All the tests passed successfully");
    }

    public int countAmountOfLuckyTicketsForSix0() {
        final var start = System.nanoTime();
        int leftSum;
        int rightSum;
        int counter = 0;
        for (int a1 = MIN; a1 <= MAX; a1++) {
            for (int a2 = MIN; a2 <= MAX; a2++) {
                for (int a3 = MIN; a3 <= MAX; a3++) {
                    leftSum = a1 + a2 + a3;
                    for (int b1 = MIN; b1 <= MAX; b1++) {
                        for (int b2 = MIN; b2 <= MAX; b2++) {
                            for (int b3 = MIN; b3 <= MAX; b3++) {
                                rightSum = b1 + b2 + b3;
                                if (leftSum == rightSum) {
                                    counter++;
                                }
                            }
                        }
                    }
                }
            }
        }
        final var stop = System.nanoTime();
        System.out.println("BrootForce0 algorithm time: " + (stop - start));
        return counter;
    }

    public int countAmountOfLuckyTicketsForSix1() {
        final var start = System.nanoTime();
        int leftSum;
        int rightSum;
        int counter = 0;

        for (int a1 = MIN; a1 <= MAX; a1++) {
            for (int a2 = MIN; a2 <= MAX; a2++) {
                for (int a3 = MIN; a3 <= MAX; a3++) {
                    leftSum = a1 + a2 + a3;
                    for (int b1 = MIN; b1 <= MAX; b1++) {
                        for (int b2 = MIN; b2 <= MAX; b2++) {
                            rightSum = b1 + b2;
                            final var dif = leftSum - rightSum;
                            if (dif >= 0 && (dif) < 10) {
                                counter++;
                            }
                        }
                    }
                }
            }
        }
        final var stop = System.nanoTime();
        System.out.println("BrootForce1 algorithm time: " + (stop - start));
        return counter;
    }

    public int countAmountOfLuckyTicketsForSix2() {
        final var start = System.nanoTime();
        int leftSum;
        int rightSum;
        int counter = 0;

        for (int a1 = MIN; a1 <= MAX; a1++) {
            for (int a2 = MIN; a2 <= MAX; a2++) {
                leftSum = a1 + a2;
                for (int b1 = MIN; b1 <= MAX; b1++) {
                    for (int b2 = MIN; b2 <= MAX; b2++) {
                        rightSum = b1 + b2;
                        final var dif = Math.abs(leftSum - rightSum);
                        if (dif < 10) {
                            counter = counter + (10 - dif);
                        }
                    }
                }
            }
        }
        final var stop = System.nanoTime();
        System.out.println("BrootForce2 algorithm time: " + (stop - start));
        return counter;
    }

    static class LuckyCounter {
        private long counter = 0;
        private List<Long> currentNumbers = new ArrayList<>();
        private List<Long> previousNumbers = new ArrayList<>();

        public void countLuckyTicketsByAmountOfElementsBrootForce(long amountOfElementsInAHalf, long sumA, long sumB) {

            if (amountOfElementsInAHalf == 1) {
                final var dif = Math.abs(sumA - sumB);
                if (dif < 10) {
                    counter = counter + (10 - dif);
                }
                return;
            }

            for (int a = 0; a < 10; a++) {
                for (int b = 0; b < 10; b++) {
                    countLuckyTicketsByAmountOfElementsBrootForce(amountOfElementsInAHalf - 1, sumA + a, sumB + b);
                }
            }
        }

        public void countLuckyTicketsByPreviousTenElements(int n) {
            if (n == 0) {
                counter = 0;
                return;
            }
            if (n == 1) {
                for (int i = MIN; i <= MAX; i++) {
                    previousNumbers.add(i, 1L);
                }
                counter = previousNumbers.size();
                return;
            }

            for (int j = MIN; j <= MAX * n; j++) {
                var c = countPreviousTenSum(previousNumbers, j);
                currentNumbers.add(j, c);
            }

            final int lastIndex = (currentNumbers.size() / 2);

            counter = currentNumbers.subList(0, lastIndex).stream()
                    .map(aLong -> aLong * aLong)
                    .mapToLong(value -> value)
                    .sum() * 2;
            if (currentNumbers.size() % 2 != 0) {
                counter = counter + (currentNumbers.get(lastIndex) * currentNumbers.get(lastIndex));
            }
            previousNumbers = currentNumbers;
            currentNumbers = new ArrayList<>();
        }

        private long countPreviousTenSum(List<Long> previousNumbers, int curIndex) {
            long counter = 0;
            //current index value in current column equals sum of last ten elements in previous column
            for (int i = MAX; i >= MIN; i--) {
                if (curIndex >= 0 && curIndex < previousNumbers.size()) {
                    counter = counter + previousNumbers.get(curIndex);
                }
                curIndex--;
                if (curIndex < 0) {
                    break;
                }
            }
            return counter;
        }

        public long getCounter() {
            return this.counter;
        }
    }
}
