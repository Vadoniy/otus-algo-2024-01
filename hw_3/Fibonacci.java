package hw_3;

import utils.FilesTest;

import java.math.BigInteger;

import static utils.FilesTest.NOT_SUCCESS;
import static utils.FilesTest.SUCCESS;

public class Fibonacci {

    public static void main(String[] args) {
        final var filesTest = new FilesTest();
        final var testFileByPath = filesTest.getTestFileByPathBigInteger("hw_3/fibo");
        final var sbResult = new StringBuffer();
        final var fibo = new Fibonacci();

        testFileByPath.forEach((aLong, bigInteger) -> {
            sbResult.append("----------------------------------------------\n")
                    .append("Index of the element: ")
                    .append(aLong)
                    .append("\n");
            BigInteger l = fibo.fiboByMatrixes(aLong.intValue());
            sbResult.append("Fibo result: ")
                    .append(l)
                    .append("\n")
                    .append("Expected result: ")
                    .append(bigInteger)
                    .append("\n")
                    .append(l.equals(bigInteger) ? SUCCESS : NOT_SUCCESS)
                    .append("\n");
        });
        System.out.println(sbResult.append("----------------------------------------------"));
        System.out.println(sbResult.toString().contains(NOT_SUCCESS) ? "WARNING! Errors in the result"
                : "All the tests passed successfully");
    }

    private long simpleFiboRecursive(int inputIndex) {
        if (inputIndex == 0) {
            return 1;
        } else if (inputIndex == 1) {
            return 1;
        } else {
            return simpleFibo(inputIndex - 1) + simpleFibo(inputIndex - 2);
        }
    }

    private long simpleFibo(int inputIndex) {
        var fib0 = 0;
        var fib1 = 1;
        var result = 0;

        for (int i = 1; i < inputIndex; i++) {
            result = fib0 + fib1;
            fib0 = fib1;
            fib1 = result;
        }
        return result;
    }

    private BigInteger fiboByMatrixes(long inputIndex) {
        var f0 = BigInteger.ZERO;
        var f1 = BigInteger.ONE;
        var f2 = BigInteger.ONE;

        if (inputIndex == 0) {
            return f0;
        }

        if (inputIndex == 1) {
            return f1;
        }


        var multiplierMatrix = new BigInteger[2][2];
        multiplierMatrix[0][0] = BigInteger.ONE;
        multiplierMatrix[0][1] = BigInteger.ONE;
        multiplierMatrix[1][0] = BigInteger.ONE;
        multiplierMatrix[1][1] = BigInteger.ZERO;
        var result = new BigInteger[2][2];
        var matrix1 = new BigInteger[2][2];

        for (int i = 3; i <= inputIndex; i++) {
            matrix1[0][0] = f2;
            matrix1[0][1] = f1;
            matrix1[1][0] = f1;
            matrix1[1][1] = f0;

            result = this.multiplyMatrixes(matrix1, multiplierMatrix);
            f0 = result[1][1];
            f1 = result[0][1];
            f2 = result[0][0];
        }

        return f2;
    }

    private BigInteger[][] multiplyMatrixes(BigInteger[][] matrix1, BigInteger[][] matrix2) {
        if (matrix1[1].length != matrix2[0].length) {
            throw new RuntimeException("Impossible to multiply matrix's");
        }
        var resultMatrix = new BigInteger[2][2];
        resultMatrix[0][0] = (matrix1[0][0].multiply(matrix2[0][0])).add((matrix1[0][1].multiply(matrix2[1][0])));
        resultMatrix[0][1] = (matrix1[0][0].multiply(matrix2[0][1])).add((matrix1[0][1].multiply(matrix2[1][1])));
        resultMatrix[1][0] = (matrix1[1][0].multiply(matrix2[0][0])).add((matrix1[1][1].multiply(matrix2[1][0])));
        resultMatrix[1][1] = (matrix1[1][0].multiply(matrix2[0][1])).add((matrix1[1][1].multiply(matrix2[1][1])));

        return resultMatrix;
    }

    private void printMatrix(long[][] matrix) {
        var sb = new StringBuffer();
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
