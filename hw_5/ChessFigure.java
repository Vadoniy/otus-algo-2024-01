package hw_5;

import java.math.BigInteger;

public class ChessFigure {

    public static final BigInteger LEFT_HEX_LIMIT = new BigInteger("fefefefefefefefe", 16);
    public static final BigInteger RIGHT_HEX_LIMIT = new BigInteger("7f7f7f7f7f7f7f7f", 16);
    public static final BigInteger LEFT_HEX_LIMIT2 = new BigInteger("fcfcfcfcfcfcfcfc", 16);
    public static final BigInteger RIGHT_HEX_LIMIT2 = new BigInteger("3f3f3f3f3f3f3f3f", 16);

// Positions on the board:
// 56 57 58	59 60 61 62 63
// 48 49 50	51 52 53 54 55
// 40 41 42	43 44 45 46 47
// 32 33 34	35 36 37 38 39
// 24 25 26	27 28 29 30 31
// 16 17 18	19 20 21 22 23
// 08 09 10	11 12 13 14 15
// 00 01 02	03 04 05 06 07

    public static void main(String[] args) {
        final var king = new King();
        final var kingsMoves = king.possibleMove(27);
        System.out.println(kingsMoves);
        final var knight = new Knight();
        final var knightsMoves = knight.possibleMove(25);
        System.out.println(knightsMoves);
        final var rook = new Rook();
        System.out.println(rook.possibleMove(24));
        final var popCounter = new PopCounter();
        System.out.println(popCounter.counter1(knightsMoves));
        System.out.println(popCounter.counter2(knightsMoves));
    }
}