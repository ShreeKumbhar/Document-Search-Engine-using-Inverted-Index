package com.minigoogle;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

// Entry point of the application. Handles the CLI.
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("   Mini Google - File Based Search Engine");
        System.out.println("=========================================");

        System.out.print("\nEnter directory path to index: ");
        String directoryPath = scanner.nextLine().trim();

        // Initialize components.
        FileLoader fileLoader = new FileLoader();
        Tokenizer tokenizer = new Tokenizer();
        InvertedIndex invertedIndex = new InvertedIndex();

        System.out.println("\n[INFO] Starting Indexing Process...");
        long startTime = System.currentTimeMillis();

        int fileCount = 0;
        try {
            List<Path> files = fileLoader.loadTextFiles(directoryPath);
            fileCount = files.size();

            if (fileCount == 0) {
                System.out.println("[WARN] No .txt files found.");
            } else {
                for (Path file : files) {
                    try {
                        String content = fileLoader.readFileContent(file);
                        List<String> tokens = tokenizer.tokenize(content);
                        invertedIndex.addDocument(file.getFileName().toString(), tokens);
                    } catch (IOException e) {
                        System.err.println("[ERROR] Failed to read file: " + file);
                        e.printStackTrace();
                    }
                }
            }

        } catch (IllegalArgumentException | IOException e) {
            System.err.println("[ERROR] " + e.getMessage());
            return;
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("[INFO] Indexing Complete. Indexed %d files in %d ms.\n", fileCount, (endTime - startTime));
        System.out.println("[INFO] Vocabulary Size: " + invertedIndex.getVocabularySize() + " words.");

        SearchEngine searchEngine = new SearchEngine(invertedIndex);

        // Search loop until user exits.
        while (true) {
            System.out.print("\nSearch (type ':q' to exit): ");
            String keyword = scanner.nextLine().trim();

            if (":q".equalsIgnoreCase(keyword)) {
                System.out.println("Exiting... Goodbye!");
                break;
            }

            if (keyword.isEmpty()) {
                continue;
            }

            List<SearchEngine.SearchResult> results = searchEngine.search(keyword);

            if (results.isEmpty()) {
                System.out.println("No results found for '" + keyword + "'");
            } else {
                System.out.println("Found " + results.size() + " result(s):");
                for (SearchEngine.SearchResult result : results) {
                    System.out.println(" - " + result);
                }
            }
        }

        scanner.close();
    }
}
