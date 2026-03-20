package com.codefactor.challenge.one_week.day5;

/**
 * Challenge 07 - Check if String Contains Only Digits
 *
 * Given a string, check if it contains only numeric digits.
 *
 * Approaches:
 * 1. Regex matching
 * 2. Character.isDigit loop
 * 3. Stream allMatch
 *
 * Complexity: O(n) time, O(1) space
 *
 * Example:
 *   Input:  "12345"  -> true
 *   Input:  "123a5"  -> false
 */
public class Challenge07 {

    /** Approach 1: Regex */
    public static boolean isNumericRegex(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.matches("\\d+");
    }

    /** Approach 2: Character.isDigit loop */
    public static boolean isNumericLoop(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    /** Approach 3: Stream allMatch */
    public static boolean isNumericStream(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.chars().allMatch(Character::isDigit);
    }

    public static void main(String[] args) {
        String[] tests = {"12345", "123a5", "", "0", "3.14", "-42", "999999"};
        for (String test : tests) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("  Regex:  " + isNumericRegex(test));
            System.out.println("  Loop:   " + isNumericLoop(test));
            System.out.println("  Stream: " + isNumericStream(test));
            System.out.println();
        }
    }
}
