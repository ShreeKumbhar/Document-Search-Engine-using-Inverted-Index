package com.minigoogle;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

// Converts raw text into a list of normalized tokens.
public class Tokenizer {

    // Regex to remove non-alphanumeric characters.
    private static final Pattern SPECIAL_CHARS = Pattern.compile("[^a-zA-Z0-9\\s]");

    // Cleans the content and splits it into words.
    public List<String> tokenize(String content) {
        List<String> tokens = new ArrayList<>();
        if (content == null || content.isEmpty()) {
            return tokens;
        }

        // Convert to lowercase.
        String lowerCaseContent = content.toLowerCase();

        // Remove punctuation and replace with space.
        String cleanContent = SPECIAL_CHARS.matcher(lowerCaseContent).replaceAll(" ");

        // Split by whitespace.
        String[] splitWords = cleanContent.split("\\s+");

        for (String word : splitWords) {
            if (!word.isEmpty()) {
                tokens.add(word);
            }
        }

        return tokens;
    }
}
