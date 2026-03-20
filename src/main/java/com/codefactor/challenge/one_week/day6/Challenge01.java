package com.codefactor.challenge.one_week.day6;

import java.util.Arrays;

/**
 * Challenge 01 - Find the Second Largest Element in an Array
 *
 * Given an unsorted array of integers, find the second largest element
 * without sorting the array.
 *
 * Requirements:
 * - Do NOT sort the array. Solve in a single pass O(n).
 * - Handle edge cases: arrays with fewer than 2 distinct elements,
 *   arrays with all identical values, arrays containing negative numbers.
 * - Return Integer.MIN_VALUE if no valid second largest exists.
 *
 * Example:
 *   Input:  [12, 35, 1, 10, 34, 1]
 *   Output: 34
 *
 *   Input:  [5, 5, 5]
 *   Output: No second largest (all elements identical)
 *
 * Complexity: O(n) time, O(1) space
 */
public class Challenge01 {

    /**
     * Finds the second largest element by tracking the two largest
     * values in a single traversal.
     *
     * @param arr the input array
     * @return the second largest element, or Integer.MIN_VALUE if none exists
     */
    public static int findSecondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            return Integer.MIN_VALUE;
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > first) {
                // Current largest becomes second; num becomes the new largest
                second = first;
                first = num;
            } else if (num > second && num != first) {
                // num sits between first and second and is distinct from first
                second = num;
            }
        }

        return second;
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {12, 35, 1, 10, 34, 1},
                {5, 5, 5},
                {1, 2},
                {-3, -1, -7, -2},
                {100}
        };

        for (int[] test : testCases) {
            int result = findSecondLargest(test);
            System.out.println("Array:  " + Arrays.toString(test));
            if (result == Integer.MIN_VALUE) {
                System.out.println("Result: No valid second largest element");
            } else {
                System.out.println("Result: " + result);
            }
            System.out.println();
        }
    }
}
