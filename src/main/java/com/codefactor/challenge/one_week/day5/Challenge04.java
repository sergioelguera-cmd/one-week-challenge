package com.codefactor.challenge.one_week.day5;

import java.util.Arrays;

/**
 * Challenge 04 - Check if Two Strings are Anagrams
 *
 * Given two strings, determine if they are anagrams of each other.
 * Two strings are anagrams if they contain the same characters with the same frequencies.
 *
 * Approaches:
 * 1. Sort and compare
 * 2. Frequency array (for lowercase ASCII)
 *
 * Complexity:
 *   Sorting: O(n log n) time, O(n) space
 *   Frequency: O(n) time, O(1) space (fixed 26-char array)
 *
 * Example:
 *   Input:  "listen", "silent"
 *   Output: true
 */
public class Challenge04 {

    /** Approach 1: Sort both strings and compare */
    public static boolean isAnagramSort(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        char[] a = s1.toLowerCase().toCharArray();
        char[] b = s2.toLowerCase().toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    /** Approach 2: Frequency count using int array */
    public static boolean isAnagramFrequency(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        int[] freq = new int[26];
        String a = s1.toLowerCase(), b = s2.toLowerCase();
        for (int i = 0; i < a.length(); i++) {
            freq[a.charAt(i) - 'a']++;
            freq[b.charAt(i) - 'a']--;
        }
        for (int count : freq) {
            if (count != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] tests = {
            {"listen", "silent"},
            {"hello", "world"},
            {"Astronomer", "Moon starer"},
            {"anagram", "nagaram"},
            {"rat", "car"}
        };
        for (String[] pair : tests) {
            String s1 = pair[0], s2 = pair[1];
            System.out.println("\"" + s1 + "\" vs \"" + s2 + "\"");
            System.out.println("  Sort:      " + isAnagramSort(s1, s2));
            System.out.println("  Frequency: " + isAnagramFrequency(s1, s2));
            System.out.println();
        }
    }
}
