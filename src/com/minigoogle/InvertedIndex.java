package com.minigoogle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Core structure mapping unique words to files and frequencies.
// Structure: Map<Word, Map<FileName, Frequency>>
public class InvertedIndex {

    private final Map<String, Map<String, Integer>> index;

    public InvertedIndex() {
        this.index = new HashMap<>();
    }

    // Adds a file's tokens to the index.
    public void addDocument(String fileName, List<String> tokens) {
        for (String token : tokens) {
            // If word exists, update count; otherwise create new entry.
            index.computeIfAbsent(token, k -> new HashMap<>())
                    .merge(fileName, 1, Integer::sum);
        }
    }

    // Looks up a word in the index. O(1) complexity.
    public Map<String, Integer> search(String word) {
        return index.get(word);
    }

    // Returns total number of unique words.
    public int getVocabularySize() {
        return index.size();
    }
}
