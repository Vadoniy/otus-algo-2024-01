package hw_5;

import java.math.BigInteger;

import static hw_5.ChessFigure.LEFT_HEX_LIMIT;
import static hw_5.ChessFigure.RIGHT_HEX_LIMIT;

public class King {

    public BigInteger possibleMove(int position) {
//        ход вправо = смещение влево. Соответственно, для одстижения позиции номер 27 нужно сделать смещение влево
//        относительно начальной позиции на номер позиции - на 27.
        final var currentPosBit = BigInteger.ONE.shiftLeft(position);
        final var curPosWithLeftLimit = currentPosBit.and(LEFT_HEX_LIMIT);
        final var curPosWithRightLimit = currentPosBit.and(RIGHT_HEX_LIMIT);
//        король может пойти на 1 поле влево или вправо - соответственно, смещение вправо на 1 даёт нам лимит хода влево
//        , а смещение вправо - лимит хода вправо
        final var leftLimit = curPosWithLeftLimit.shiftRight(1);
        final var rightLimit = curPosWithRightLimit.shiftLeft(1);
//        для ограничения хода вперёд, нам нужно "добавить" один ряд битов - сместить влево на 8
        final var forwardLimit = currentPosBit.shiftLeft(8);
        final var backwardLimit = currentPosBit.shiftRight(8);
//        ограничение хода по диагонали:
        final var forwardDiagonals = curPosWithLeftLimit.shiftLeft(7)
                .or(curPosWithLeftLimit.shiftRight(9));
        final var backwardDiagonals = curPosWithRightLimit.shiftRight(7)
                .or(curPosWithRightLimit.shiftLeft(9));
        return leftLimit
                .or(rightLimit)
                .or(forwardLimit)
                .or(backwardLimit)
                .or(forwardDiagonals)
                .or(backwardDiagonals);
    }
}
