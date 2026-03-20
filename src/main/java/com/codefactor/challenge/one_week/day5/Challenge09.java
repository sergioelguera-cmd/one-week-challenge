package com.codefactor.challenge.one_week.day5;

/**
 * Challenge 09 - String Compression
 *
 * Implement basic string compression using the counts of repeated characters.
 * If the compressed string is not smaller than the original, return the original.
 *
 * Complexity: O(n) time, O(n) space
 *
 * Example:
 *   Input:  "aabcccccaaa"
 *   Output: "a2b1c5a3"
 *
 *   Input:  "abc"
 *   Output: "abc" (compressed "a1b1c1" is longer)
 */
public class Challenge09 {

    /** Compress string using run-length encoding */
    public static String compress(String s) {
        if (s == null || s.length() <= 1) return s;

        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(s.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        sb.append(s.charAt(s.length() - 1)).append(count);

        return sb.length() < s.length() ? sb.toString() : s;
    }

    /** Optimized: check if compression would help before building */
    public static String compressOptimized(String s) {
        if (s == null || s.length() <= 1) return s;

        int compressedLength = 0;
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                compressedLength += 1 + String.valueOf(count).length();
                count = 1;
            }
        }
        compressedLength += 1 + String.valueOf(count).length();

        if (compressedLength >= s.length()) return s;

        // Build compressed string
        StringBuilder sb = new StringBuilder(compressedLength);
        count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(s.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        sb.append(s.charAt(s.length() - 1)).append(count);
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] tests = {"aabcccccaaa", "abc", "aaa", "aabbcc", "aaAAaa"};
        for (String test : tests) {
            System.out.println("Input:     \"" + test + "\"");
            System.out.println("  Basic:     \"" + compress(test) + "\"");
            System.out.println("  Optimized: \"" + compressOptimized(test) + "\"");
            System.out.println();
        }
    }
}
