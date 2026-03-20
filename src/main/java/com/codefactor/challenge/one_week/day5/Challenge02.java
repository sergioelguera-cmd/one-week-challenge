package com.codefactor.challenge.one_week.day5;

/**
 * Challenge 02 - Check if a String is a Palindrome
 *
 * Given a string, determine if it reads the same forward and backward.
 * Ignore non-alphanumeric characters and case.
 *
 * Approaches:
 * 1. Two-pointer technique
 * 2. Using StringBuilder reverse
 *
 * Complexity: O(n) time, O(1) space (two-pointer)
 *
 * Example:
 *   Input:  "A man, a plan, a canal: Panama"
 *   Output: true
 */
public class Challenge02 {

    /** Approach 1: Two-pointer skipping non-alphanumeric */
    public static boolean isPalindromeTwoPointer(String s) {
        if (s == null) return false;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /** Approach 2: Clean string then compare with reverse */
    public static boolean isPalindromeReverse(String s) {
        if (s == null) return false;
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }

    public static void main(String[] args) {
        String[] tests = {
            "A man, a plan, a canal: Panama",
            "racecar",
            "hello",
            "Was it a car or a cat I saw?",
            "",
            "a"
        };
        for (String test : tests) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("  Two-pointer: " + isPalindromeTwoPointer(test));
            System.out.println("  Reverse:     " + isPalindromeReverse(test));
            System.out.println();
        }
    }
}
