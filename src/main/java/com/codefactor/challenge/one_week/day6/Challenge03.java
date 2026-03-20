package com.codefactor.challenge.one_week.day6;

import java.util.Arrays;

/**
 * Challenge 03 - Find the Missing Number
 *
 * Given an array containing n distinct numbers from the range 1 to n+1,
 * exactly one number is missing. Find it.
 *
 * Two approaches are demonstrated:
 *
 * 1. Sum Formula:
 *    Expected sum = n*(n+1)/2 where n = arr.length + 1.
 *    Missing = expected - actual sum.
 *    Risk: integer overflow for very large n (use long).
 *
 * 2. XOR Approach:
 *    XOR all array elements with all numbers 1..n+1.
 *    Pairs cancel out, leaving only the missing number.
 *    No overflow risk.
 *
 * Example:
 *   Input:  [1, 2, 4, 5, 6]  (range 1..6)
 *   Output: 3
 *
 * Complexity: O(n) time, O(1) space for both approaches
 */
public class Challenge03 {

    /**
     * Finds the missing number using the arithmetic sum formula.
     *
     * @param arr array of n elements containing values from 1 to n+1 with one missing
     * @return the missing number
     */
    public static int findMissingSum(int[] arr) {
        int n = arr.length + 1; // Total count should be n
        long expectedSum = (long) n * (n + 1) / 2;
        long actualSum = 0;
        for (int num : arr) {
            actualSum += num;
        }
        return (int) (expectedSum - actualSum);
    }

    /**
     * Finds the missing number using XOR.
     * a ^ a = 0 and a ^ 0 = a, so all paired values cancel out.
     *
     * @param arr array of n elements containing values from 1 to n+1 with one missing
     * @return the missing number
     */
    public static int findMissingXOR(int[] arr) {
        int n = arr.length + 1;
        int xor = 0;

        // XOR all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }

        // XOR all elements in the array
        for (int num : arr) {
            xor ^= num;
        }

        // Only the missing number remains
        return xor;
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {1, 2, 4, 5, 6},       // missing 3
                {2, 3, 4, 5},           // missing 1
                {1, 2, 3, 4},           // missing 5
                {1}                     // missing 2
        };

        for (int[] test : testCases) {
            System.out.println("Array:        " + Arrays.toString(test));
            System.out.println("Sum approach: " + findMissingSum(test));
            System.out.println("XOR approach: " + findMissingXOR(test));
            System.out.println();
        }
    }
}
