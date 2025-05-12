package hw_20;

import utils.ArrayUtils;

import java.util.ArrayList;

public class Rle {

    public byte[] simpleEncode(byte[] array, int length) {
        if (array.length > Byte.MAX_VALUE - 1) {
            throw new RuntimeException("length must be less 127");
        }

        final var rle = new ArrayList<Byte>();
        var i = 0;

        while (i < length) {
            var j = (byte) (i + 1);

            while (j < length) {
                if (array[i] == array[j]) {
                    j++;
                } else {
                    break;
                }
            }

            rle.add((byte) (j - i));
            rle.add(array[i]);
            i = j;
        }

        return ArrayUtils.toByteArray(rle);
    }

    public byte[] simpleDecode(byte[] rleArray, int length) {
        if (rleArray.length % 2 != 0) {
            throw new RuntimeException("length must be even");
        }

        final var back = new ArrayList<Byte>();
        var i = 0;
        while (i < length) {
            var replayCount = rleArray[i];

            while (replayCount-- != 0) {
                back.add(rleArray[i + 1]);
            }

            i = i + 2;
        }
        return ArrayUtils.toByteArray(back);
    }

    public byte[] improvedEncode(byte[] simpleRleArray, byte repeatCountMax) {
        final var compressedRle = new ArrayList<Byte>();
        byte inRaw = 0;         //Количество символов без повторений подряд
        var inRawPosition = 0;  //Позиция в массиве счётчика количества символов без повторений

        int i = 0;
        while (i < simpleRleArray.length) {
            byte current = simpleRleArray[i];
            byte count = 1;

//          Количество повторений для текущего символа
            while (i + count < simpleRleArray.length
                    && simpleRleArray[i + count] == current
                    && count < repeatCountMax) {
                count++;
            }
//          Если количество повторений больше 1, то сжимаем
            if (count > 1) {
                compressedRle.add(count);
                compressedRle.add(current);
                inRaw = 0;
                inRawPosition = compressedRle.size() - 1;
            } else {
                inRaw++;
                if (inRaw == 1) {
                    compressedRle.add((byte) (inRaw * -1));
                    inRawPosition = compressedRle.size() > 0 ? compressedRle.size() - 1 : 0;
                } else {
                    compressedRle.set(inRawPosition, (byte) (inRaw * -1));
                }
                compressedRle.add(current);
            }

            i += count;  // Переходим к следующему символу
        }

        return ArrayUtils.toByteArray(compressedRle);
    }

    public byte[] improvedDecode(byte[] compressedRleArray) {
        final var back = new ArrayList<Byte>();
        final var length = compressedRleArray.length;

        var i = 0;
        while (i < length) {
            var repeatCount = compressedRleArray[i];

            if (repeatCount > 0) {
                if (i == length - 1) {
                    throw new RuntimeException("Error decoding array");
                }

                while (repeatCount != 0) {
                    back.add(compressedRleArray[i + 1]);
                    repeatCount--;
                }

                i = i + 2;
            } else {
                repeatCount = (byte) (-repeatCount);

                if (Math.abs(repeatCount) > length - i - 1) {
                    throw new RuntimeException("Error decoding array: repeat count is too large");
                }

                for (int j = 0; j < repeatCount; j++) {
                    back.add(compressedRleArray[i + 1 + j]);
                }

                i = i + 1 + repeatCount;
            }
        }
        return ArrayUtils.toByteArray(back);
    }
}