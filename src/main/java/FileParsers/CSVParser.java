package FileParsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVParser {

    private List<String> padList(List<String> original, int size) {
        while (original.size() < size) {
            original.add("");
        }
        return original;
    }

    public List<List<String>> parseCSV(String fileName) {
        List<List<String>> records = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            List<String> allLines = lines.collect(Collectors.toList());
            if (!allLines.isEmpty()) {
                String delimiter = detectDelimiter(allLines.get(0));
                int numberOfHeaders = allLines.get(0).split(delimiter, -1).length;

                records = allLines.stream()
                        // Regex for line split
                        .map(line -> Arrays.asList(line.split("\\s*,\\s*", -1)))
                        .map(list -> padList(new ArrayList<>(list), numberOfHeaders))
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }

    private String detectDelimiter(String firstLine) {
        Map<Character, Integer> delimiterCounts = new HashMap<>();
        // Common delimiters
        char[] delimiters = new char[]{',', ';', '\t', '|', ' '};

        for (char delimiter : delimiters) {
            int count = countOccurrences(firstLine, delimiter);
            delimiterCounts.put(delimiter, count);
        }

        return Collections.max(delimiterCounts.entrySet(), Map.Entry.comparingByValue()).getKey().toString();
    }

    private int countOccurrences(String str, char ch) {
        return (int) str.chars().filter(c -> c == ch).count();
    }
}
