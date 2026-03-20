package com.codefactor.challenge.one_week.day5;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Challenge 03 - Count Character Occurrences
 *
 * Given a string, count the frequency of each character.
 *
 * Approaches:
 * 1. Using LinkedHashMap (preserves insertion order)
 * 2. Using Streams with Collectors.groupingBy
 *
 * Complexity: O(n) time, O(k) space where k = unique characters
 *
 * Example:
 *   Input:  "programming"
 *   Output: {p=1, r=2, o=1, g=2, a=1, m=2, i=1, n=1}
 */
public class Challenge03 {

    /** Approach 1: LinkedHashMap for ordered frequency count */
    public static Map<Character, Integer> countCharsMap(String s) {
        Map<Character, Integer> freq = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            freq.merge(c, 1, Integer::sum);
        }
        return freq;
    }

    /** Approach 2: Streams with groupingBy */
    public static Map<Character, Long> countCharsStream(String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()));
    }

    public static void main(String[] args) {
        String[] tests = {"programming", "hello world", "aabbcc", "Java"};
        for (String test : tests) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("  Map approach:    " + countCharsMap(test));
            System.out.println("  Stream approach: " + countCharsStream(test));
            System.out.println();
        }
    }
}
