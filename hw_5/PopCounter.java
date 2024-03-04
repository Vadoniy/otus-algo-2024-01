package hw_5;

import java.math.BigInteger;

public class PopCounter {

    public int counter1(BigInteger mask) {
        int oneCounter = 0;
        BigInteger temp;

        while (mask.compareTo(BigInteger.ZERO) > 0) {
            temp = mask.subtract(BigInteger.ONE);
            mask = mask.and(temp);
            oneCounter++;
        }

        return oneCounter;
    }

    public int counter2(BigInteger mask) {
        int oneCounter = 0;

        while (mask.compareTo(BigInteger.ZERO) > 0) {
            oneCounter += mask.and(BigInteger.ONE).intValue();
            mask = mask.shiftRight(1);
        }

        return oneCounter;
    }
}
