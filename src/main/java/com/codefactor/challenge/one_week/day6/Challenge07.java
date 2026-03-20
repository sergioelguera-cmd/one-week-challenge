package com.codefactor.challenge.one_week.day6;

/**
 * Challenge 07 - Find Maximum Subarray Sum (Kadane's Algorithm)
 *
 * Given an integer array, find the contiguous subarray with the largest sum.
 *
 * Requirements:
 * - Handle arrays with all negative numbers
 * - Return both the sum and the subarray boundaries
 *
 * Complexity: O(n) time, O(1) space
 *
 * Example:
 *   Input:  [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 *   Output: 6 (subarray [4, -1, 2, 1])
 */
public class Challenge07 {

    /** Kadane's Algorithm - returns maximum subarray sum */
    public static int maxSubarraySum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];

        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    /** Extended: also returns the subarray boundaries */
    public static int[] maxSubarrayWithIndices(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int maxSoFar = arr[0], maxEndingHere = arr[0];
        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxEndingHere + arr[i]) {
                maxEndingHere = arr[i];
                tempStart = i;
            } else {
                maxEndingHere += arr[i];
            }
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }
        return new int[]{maxSoFar, start, end};
    }

    public static void main(String[] args) {
        int[][] tests = {
            {-2, 1, -3, 4, -1, 2, 1, -5, 4},
            {1, 2, 3, 4, 5},
            {-1, -2, -3, -4},
            {5, -3, 5},
            {-2, -1}
        };
        for (int[] test : tests) {
            System.out.print("Array: [");
            for (int i = 0; i < test.length; i++) {
                System.out.print(test[i] + (i < test.length - 1 ? ", " : ""));
            }
            System.out.println("]");
            System.out.println("  Max sum: " + maxSubarraySum(test));

            int[] result = maxSubarrayWithIndices(test);
            System.out.println("  With indices: sum=" + result[0]
                + ", from index " + result[1] + " to " + result[2]);
            System.out.println();
        }
    }
}
