package com.codefactor.challenge.one_week.day5;

/**
 * Challenge 10 - Check if One String is a Rotation of Another
 *
 * Given two strings, check if one is a rotation of the other.
 * A rotation means moving characters from the front to the back.
 *
 * Approaches:
 * 1. Concatenation trick: if s2 is a rotation of s1, then s1+s1 contains s2
 * 2. Brute force: try all rotation positions
 *
 * Complexity:
 *   Concatenation: O(n) time, O(n) space
 *   Brute force: O(n^2) time, O(n) space
 *
 * Example:
 *   Input:  "waterbottle", "erbottlewat"
 *   Output: true
 */
public class Challenge10 {

    /** Approach 1: Concatenation trick */
    public static boolean isRotationConcat(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if (s1.isEmpty()) return true;
        return (s1 + s1).contains(s2);
    }

    /** Approach 2: Try all rotation positions */
    public static boolean isRotationBruteForce(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if (s1.isEmpty()) return true;
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            String rotated = s1.substring(i) + s1.substring(0, i);
            if (rotated.equals(s2)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[][] tests = {
            {"waterbottle", "erbottlewat"},
            {"hello", "llohe"},
            {"hello", "world"},
            {"abc", "cab"},
            {"", ""}
        };
        for (String[] pair : tests) {
            System.out.println("\"" + pair[0] + "\" rotation of \"" + pair[1] + "\"?");
            System.out.println("  Concat:     " + isRotationConcat(pair[0], pair[1]));
            System.out.println("  BruteForce: " + isRotationBruteForce(pair[0], pair[1]));
            System.out.println();
        }
    }
}
