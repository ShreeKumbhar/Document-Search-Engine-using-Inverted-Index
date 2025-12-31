package com.minigoogle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// Handles search operations.
public class SearchEngine {

    private final InvertedIndex invertedIndex;

    public SearchEngine(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    // Helper class to store and sort search results.
    public static class SearchResult implements Comparable<SearchResult> {
        private final String fileName;
        private final int frequency;

        public SearchResult(String fileName, int frequency) {
            this.fileName = fileName;
            this.frequency = frequency;
        }

        public String getFileName() {
            return fileName;
        }

        public int getFrequency() {
            return frequency;
        }

        @Override
        public String toString() {
            return String.format("File: %s | Frequency: %d", fileName, frequency);
        }

        @Override
        public int compareTo(SearchResult other) {
            // Sort by frequency descending (higher is better).
            return Integer.compare(other.frequency, this.frequency);
        }
    }

    // Searches for the keyword and returns sorted results.
    public List<SearchResult> search(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Collections.emptyList();
        }

        String normalizedKeyword = keyword.toLowerCase().trim();
        Map<String, Integer> fileFrequencies = invertedIndex.search(normalizedKeyword);

        List<SearchResult> results = new ArrayList<>();
        if (fileFrequencies != null) {
            for (Map.Entry<String, Integer> entry : fileFrequencies.entrySet()) {
                results.add(new SearchResult(entry.getKey(), entry.getValue()));
            }
        }

        // Sort the results.
        Collections.sort(results);
        return results;
    }
}
