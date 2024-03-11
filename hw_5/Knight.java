package hw_5;

import java.math.BigInteger;

import static hw_5.ChessFigure.LEFT_HEX_LIMIT;
import static hw_5.ChessFigure.LEFT_HEX_LIMIT2;
import static hw_5.ChessFigure.RIGHT_HEX_LIMIT;
import static hw_5.ChessFigure.RIGHT_HEX_LIMIT2;

public class Knight {
    public BigInteger possibleMove(int position) {
        final var currentPosBit = BigInteger.ONE.shiftLeft(position);
        final var b5 = currentPosBit.shiftLeft(6);
        final var b3 = currentPosBit.shiftRight(10);
        final var c6 = currentPosBit.shiftLeft(15);
        final var c2 = currentPosBit.shiftRight(17);
        final var e6 = currentPosBit.shiftLeft(17);
        final var e2 = currentPosBit.shiftRight(15);
        final var f5 = currentPosBit.shiftLeft(10);
        final var f3 = currentPosBit.shiftRight(6);
        return RIGHT_HEX_LIMIT2.and(b5.or(b3))
                .or(RIGHT_HEX_LIMIT.and(c6.or(c2)))
                .or(LEFT_HEX_LIMIT.and(e6.or(e2)))
                .or(LEFT_HEX_LIMIT2.and(f5.or(f3)));
    }
}
