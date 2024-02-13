package hw_2;

import utils.FilesTest;

public class LuckyTicket {
    public static final int MIN = 0;
    public static final int MAX = 9;

    public static void main(String[] args) {
        final var filesTest = new FilesTest();
        final var testFileByPath = filesTest.getTestFileByPath("hw_2/tickets");

        testFileByPath.forEach((aLong, aLong2) -> {
            System.out.println("----------------------------------------------");
            System.out.println("Amount of elements: " + aLong);
            final var luckyCounter = new LuckyCounter();
            luckyCounter.countLuckyTicketsByAmountOfElements(aLong, 0, 0);
            System.out.println("Count result: " + luckyCounter.getCounter());
            System.out.println("Expected result: " + aLong2);
            System.out.println(luckyCounter.getCounter() == aLong2 ? "Test passed" : "Test NOT passed");
            System.out.println("----------------------------------------------");
        });
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

        public void countLuckyTicketsByAmountOfElements(long amountOfElementsInAHalf, long sumA, long sumB) {

            if (amountOfElementsInAHalf == 1) {
                final var dif = Math.abs(sumA - sumB);
                if (dif < 10) {
                    counter = counter + (10 - dif);
                }
                return;
            }

            for (int a = 0; a < 10; a++) {
                for (int b = 0; b < 10; b++) {
                    countLuckyTicketsByAmountOfElements(amountOfElementsInAHalf - 1, sumA + a, sumB + b);
                }
            }
        }

        public long getCounter() {
            return this.counter;
        }
    }
}
