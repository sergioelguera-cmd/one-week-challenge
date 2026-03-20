package com.codefactor.challenge.one_week.day5;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Challenge 05 - First Non-Repeated Character
 *
 * Given a string, find the first character that does not repeat.
 *
 * Approaches:
 * 1. LinkedHashMap to maintain insertion order
 * 2. Stream with groupingBy and filter
 *
 * Complexity: O(n) time, O(k) space
 *
 * Example:
 *   Input:  "swiss"
 *   Output: 'w'
 */
public class Challenge05 {

    /** Approach 1: LinkedHashMap preserving insertion order */
    public static Character firstNonRepeatedMap(String s) {
        if (s == null || s.isEmpty()) return null;
        Map<Character, Integer> freq = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            freq.merge(c, 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return null;
    }

    /** Approach 2: Stream-based approach */
    public static Character firstNonRepeatedStream(String s) {
        if (s == null || s.isEmpty()) return null;
        return s.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> s.indexOf(c) == s.lastIndexOf(c))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        String[] tests = {"swiss", "aabbccd", "aabb", "javascript", "a"};
        for (String test : tests) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("  Map:    " + firstNonRepeatedMap(test));
            System.out.println("  Stream: " + firstNonRepeatedStream(test));
            System.out.println();
        }
    }
}
