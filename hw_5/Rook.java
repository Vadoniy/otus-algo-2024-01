package hw_5;

import java.math.BigInteger;

public class Rook {

// Positions on the board:
// 56 57 58	59 60 61 62 63
// 48 49 50	51 52 53 54 55
// 40 41 42	43 44 45 46 47
// 32 33 34	35 36 37 38 39
// 24 25 26	27 28 29 30 31
// 16 17 18	19 20 21 22 23
// 08 09 10	11 12 13 14 15
// 00 01 02	03 04 05 06 07

    private static final BigInteger HORIZONTAL_HEX_LINE = new BigInteger("ff", 16);
    private static final BigInteger VERTICAL_HEX_LINE = new BigInteger("101010101010101", 16);

    public BigInteger possibleMove(int position) {
        final var xLine = BigInteger.valueOf(position).shiftRight(3);
        final var yColumn = BigInteger.valueOf(position).and(BigInteger.valueOf(7));
        final var verticalMove = VERTICAL_HEX_LINE.shiftLeft(yColumn.intValue());
        final var horizontalMove = HORIZONTAL_HEX_LINE.shiftLeft(xLine.multiply(BigInteger.valueOf(8)).intValue());
        return verticalMove.xor(horizontalMove);
    }
}
