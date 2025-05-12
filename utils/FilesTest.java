package utils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    public static Map<String, String> loadTestCases(File folder) throws IOException {
        final var inFiles = new HashMap<String, File>();
        final var outFiles = new HashMap<String, File>();
        final var result = new HashMap<String, String>();

        for (final var file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isFile()) {
                final var name = file.getName();
                if (name.endsWith(".in")) {
                    final var baseName = name.substring(0, name.length() - 3);
                    inFiles.put(baseName, file);
                } else if (name.endsWith(".out")) {
                    final var baseName = name.substring(0, name.length() - 4);
                    outFiles.put(baseName, file);
                }
            }
        }

        for (final var base : inFiles.keySet()) {
            if (outFiles.containsKey(base)) {
                final var inContent = Files.readString(inFiles.get(base).toPath()).trim();
                final var outContent = Files.readString(outFiles.get(base).toPath()).trim();
                result.put(inContent, outContent);
            }
        }

        return result;
    }

    public static Map<List<String>, Integer> loadTestCasesFiles(File folder) throws IOException {
        final var inFiles = new HashMap<String, List<String>>();
        final var outFiles = new HashMap<String, Integer>();
        final var result = new HashMap<List<String>, Integer>();

        for (final var file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isFile()) {
                final var name = file.getName();
                if (name.endsWith(".in")) {
                    final var baseName = name.substring(0, name.length() - 3);
                    final var lines = Files.readAllLines(file.toPath());
                    inFiles.put(baseName, lines);
                } else if (name.endsWith(".out")) {
                    final var baseName = name.substring(0, name.length() - 4);
                    final var resultOut = Integer.parseInt(Files.readString(file.toPath()).trim());
                    outFiles.put(baseName, resultOut);
                }
            }
        }

        for (final var base : inFiles.keySet()) {
            if (outFiles.containsKey(base)) {
                final var inContent = inFiles.get(base);
                final var outContent = outFiles.get(base);
                result.put(inContent, outContent);
            }
        }

        return result;
    }
}
