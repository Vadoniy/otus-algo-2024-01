package utils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FilesTest {

    public static final String SUCCESS = "Test passed";
    public static final String NOT_SUCCESS = "Test NOT passed";

    public Map<Long, Long> getTestFileByPath(String path) {
        try {
            final var dir = Optional.ofNullable(new File(path).listFiles())
                    .map(Arrays::asList)
                    .orElseThrow(() -> new IOException("Failed to read files from " + path))
                    .stream()
                    .filter(file -> file.getName().endsWith(".in") || file.getName().endsWith(".out"))
                    .sorted()
                    .toList();
            final var map = new HashMap<Long, Long>(dir.size() / 2);
            for (int i = 0; i < dir.size(); i++, i++) {
                map.put(stringToLong(dir.get(i).toPath()), stringToLong(dir.get(i+1).toPath()));
            }

            return map;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<BigInteger, BigInteger> getTestFileByPathBigInteger(String path) {
        try {
            final var dir = Optional.ofNullable(new File(path).listFiles())
                    .map(Arrays::asList)
                    .orElseThrow(() -> new IOException("Failed to read files from " + path))
                    .stream()
                    .filter(file -> file.getName().endsWith(".in") || file.getName().endsWith(".out"))
                    .sorted()
                    .toList();
            final var map = new HashMap<BigInteger, BigInteger>(dir.size() / 2);
            for (int i = 0; i < dir.size(); i++, i++) {
                map.put(stringToBigInteger(dir.get(i).toPath()), stringToBigInteger(dir.get(i+1).toPath()));
            }

            return map;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Long stringToLong(Path path) throws IOException {
        return Long.parseLong(Files.readString(path).replaceAll("[^\\d]", ""));
    }

    private BigInteger stringToBigInteger(Path path) throws IOException {
        return new BigInteger(Files.readString(path).replaceAll("[^\\d]", ""));
    }
}
