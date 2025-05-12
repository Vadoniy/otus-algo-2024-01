package hw_21;

import utils.FilesTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class BigIsland {

    public static void main(String[] args) throws IOException {
        final var testsFolder = new File("hw_21/tests/big_island");
        final var testCases = FilesTest.loadTestCasesFiles(testsFolder);

        testCases.forEach((lines, expectedIslandCount) -> {
                    final var size = Integer.parseInt(lines.get(0).trim());
                    final var matrix = new int[size][size];
                    final var visited = new boolean[size][size];

                    for (int i = 0; i < size; i++) {
                        matrix[i] = Arrays.stream(lines.get(i + 1).trim().split("\\s+"))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                    }

                    var islandCount = 0;

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if (matrix[i][j] == 1 && !visited[i][j]) {
                                dfs(i, j, size, matrix, visited);
                                islandCount++;
                            }
                        }
                    }

                    if (expectedIslandCount == islandCount) {
                        System.out.println("Fact = " + islandCount + ", expected = " + expectedIslandCount
                                + "; result is correct");
                    } else {
                        throw new RuntimeException("INCORRECT RESULT: expected " + expectedIslandCount
                                + ",  but was " + islandCount);
                    }
                }
        );
    }

    private static void dfs(int x, int y, int N, int[][] grid, boolean[][] visited) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return;
        }
        if (grid[x][y] == 0 || visited[x][y]) {
            return;
        }

        visited[x][y] = true;

        dfs(x - 1, y, N, grid, visited); // вверх
        dfs(x + 1, y, N, grid, visited); // вниз
        dfs(x, y - 1, N, grid, visited); // влево
        dfs(x, y + 1, N, grid, visited); // вправо
    }
}