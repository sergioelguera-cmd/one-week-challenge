package com.codefactor.challenge.one_week.day5;

/**
 * Challenge 01 - Reverse a String
 *
 * Given a string, reverse it using multiple approaches:
 * 1. Iterative with char array (two-pointer swap)
 * 2. Using StringBuilder
 * 3. Using Java Streams (IntStream)
 *
 * Complexity: O(n) time, O(n) space
 *
 * Example:
 *   Input:  "hello"
 *   Output: "olleh"
 */
public class Challenge01 {

    /** Approach 1: Two-pointer swap on char array */
    public static String reverseIterative(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    /** Approach 2: StringBuilder reverse */
    public static String reverseStringBuilder(String s) {
        if (s == null) return null;
        return new StringBuilder(s).reverse().toString();
    }

    /** Approach 3: Using IntStream to reverse characters */
    public static String reverseStream(String s) {
        if (s == null) return null;
        return s.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .reduce("", (a, b) -> b + a);
    }

    public static void main(String[] args) {
        String[] tests = {"hello", "Java", "racecar", "", "A", null};
        for (String test : tests) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("  Iterative:     \"" + reverseIterative(test) + "\"");
            System.out.println("  StringBuilder: \"" + reverseStringBuilder(test) + "\"");
            System.out.println("  Stream:        \"" + reverseStream(test) + "\"");
            System.out.println();
        }
    }
}
