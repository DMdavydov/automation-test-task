package com.epam.tasks.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("files/numbers.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            String joinedLines = String.join("", lines).replaceAll("\\s+", "");

            String[] data = joinedLines.split(",");
            List<Integer> naturalOrder = Arrays.stream(data)
                    .map(Integer::parseInt)
                    .sorted()
                    .collect(Collectors.toList());
            List<Integer> reverseOrder = Arrays.stream(data)
                    .map(Integer::parseInt)
                    .sorted(Collections.reverseOrder())
                    .collect(Collectors.toList());
            System.out.println("Natural order: " + naturalOrder + "\n"
                    + "Reversed order: " + reverseOrder);
        } catch (IOException | NumberFormatException e) {
            e.getStackTrace();
        }
    }
}
