package com.codefactor.challenge.one_week.day6;

/**
 * Challenge 10 - Product of Array Except Self
 *
 * Given an integer array nums, return an array where each element is
 * the product of all elements except itself. Do NOT use division.
 *
 * Approaches:
 * 1. Left and Right product arrays
 * 2. Optimized O(1) extra space (using output array)
 *
 * Complexity: O(n) time, O(1) extra space (approach 2)
 *
 * Example:
 *   Input:  [1, 2, 3, 4]
 *   Output: [24, 12, 8, 6]
 */
public class Challenge10 {

    /** Approach 1: Prefix and suffix product arrays */
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] result = new int[n];

        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

    /** Approach 2: Optimized - single output array */
    public static int[] productExceptSelfOptimized(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {1, 2, 3, 4},
            {-1, 1, 0, -3, 3},
            {2, 3, 4, 5}
        };
        for (int[] test : tests) {
            System.out.print("Input:     ");
            printArray(test);
            System.out.print("Basic:     ");
            printArray(productExceptSelf(test));
            System.out.print("Optimized: ");
            printArray(productExceptSelfOptimized(test));
            System.out.println();
        }
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}
