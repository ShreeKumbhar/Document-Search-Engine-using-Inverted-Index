package com.minigoogle;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Handles loading files from the file system.
public class FileLoader {

    // Recursively finds all .txt files in the directory.
    public List<Path> loadTextFiles(String directoryPath) throws IOException {
        Path root = Paths.get(directoryPath);

        if (!Files.exists(root) || !Files.isDirectory(root)) {
            throw new IllegalArgumentException("Invalid directory path: " + directoryPath);
        }

        // Walk the file tree and filter for text files.
        try (Stream<Path> stream = Files.walk(root, FileVisitOption.FOLLOW_LINKS)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".txt"))
                    .collect(Collectors.toList());
        }
    }

    // Reads the entire content of a file into a String.
    public String readFileContent(Path filePath) throws IOException {
        return Files.readString(filePath);
    }
}
