package hw_21;

import utils.FilesTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Tree {

    public static void main(String[] args) throws IOException {
        final var testsFolder = new File("hw_21/tests/tree");
        final var testCases = FilesTest.loadTestCasesFiles(testsFolder);

        testCases.forEach((lines, sum) -> {
                    final var height = Integer.parseInt(lines.get(0));
                    final var tree = readTree(height, lines);
                    var dp = Arrays.copyOf(tree[height - 1], tree[height - 1].length);

                    for (int level = height - 2; level >= 0; level--) {
                        final var next = new int[tree[level].length];
                        for (int i = 0; i < tree[level].length; i++) {
                            next[i] = tree[level][i] + Math.max(dp[i], dp[i + 1]);
                        }
                        dp = next;
                    }

                    if (dp[0] == sum) {
                        System.out.println("Fact = " + dp[0] + ", expected = " + sum + "; result is correct");
                    } else {
                        throw new RuntimeException("INCORRECT RESULT: expected " + sum + ",  but was " + dp[0]);
                    }
                }
        );
    }

    private static int[][] readTree(int height, List<String> lines) {
        int[][] tree = new int[height][];
        for (int i = 0; i < height; i++) {
            tree[i] = Arrays.stream(lines.get(i + 1).trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return tree;
    }
}
