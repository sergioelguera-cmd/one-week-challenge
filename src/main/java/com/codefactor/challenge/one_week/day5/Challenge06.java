package com.codefactor.challenge.one_week.day5;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * Challenge 06 - Remove Duplicate Characters from a String
 *
 * Given a string, remove duplicate characters while preserving the original order.
 *
 * Approaches:
 * 1. LinkedHashSet (preserves insertion order, ignores duplicates)
 * 2. Stream with distinct()
 *
 * Complexity: O(n) time, O(k) space
 *
 * Example:
 *   Input:  "programming"
 *   Output: "progamin"
 */
public class Challenge06 {

    /** Approach 1: LinkedHashSet */
    public static String removeDuplicatesSet(String s) {
        if (s == null) return null;
        LinkedHashSet<Character> seen = new LinkedHashSet<>();
        for (char c : s.toCharArray()) {
            seen.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : seen) {
            sb.append(c);
        }
        return sb.toString();
    }

    /** Approach 2: Stream distinct */
    public static String removeDuplicatesStream(String s) {
        if (s == null) return null;
        return s.chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String[] tests = {"programming", "hello world", "aabbccdd", "Java"};
        for (String test : tests) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("  Set:    \"" + removeDuplicatesSet(test) + "\"");
            System.out.println("  Stream: \"" + removeDuplicatesStream(test) + "\"");
            System.out.println();
        }
    }
}
