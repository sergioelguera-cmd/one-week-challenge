package com.codefactor.challenge.one_week.day5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Challenge 08 - Reverse Words in a Sentence
 *
 * Given a sentence, reverse the order of words (not characters).
 *
 * Approaches:
 * 1. Split, reverse array, join
 * 2. Stream with reduce
 * 3. Two-pointer in-place (on char array)
 *
 * Complexity: O(n) time, O(n) space
 *
 * Example:
 *   Input:  "Hello World Java"
 *   Output: "Java World Hello"
 */
public class Challenge08 {

    /** Approach 1: Split and reverse */
    public static String reverseWordsSplit(String s) {
        if (s == null || s.trim().isEmpty()) return s;
        String[] words = s.trim().split("\\s+");
        List<String> list = Arrays.asList(words);
        Collections.reverse(list);
        return String.join(" ", list);
    }

    /** Approach 2: Stream reduce */
    public static String reverseWordsStream(String s) {
        if (s == null || s.trim().isEmpty()) return s;
        return Arrays.stream(s.trim().split("\\s+"))
                .reduce((a, b) -> b + " " + a)
                .orElse("");
    }

    /** Approach 3: StringBuilder manual approach */
    public static String reverseWordsManual(String s) {
        if (s == null || s.trim().isEmpty()) return s;
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] tests = {
            "Hello World Java",
            "  leading and trailing  ",
            "single",
            "the sky is blue"
        };
        for (String test : tests) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("  Split:  \"" + reverseWordsSplit(test) + "\"");
            System.out.println("  Stream: \"" + reverseWordsStream(test) + "\"");
            System.out.println("  Manual: \"" + reverseWordsManual(test) + "\"");
            System.out.println();
        }
    }
}
