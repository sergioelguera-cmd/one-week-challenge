package com.codefactor.challenge.one_week.day6;

import java.util.Arrays;

/**
 * Challenge 02 - Rotate an Array by K Positions to the Right
 *
 * Given an integer array and a number k, rotate the array to the right
 * by k positions in-place.
 *
 * Approach (Reversal Algorithm):
 *   1. Reverse the entire array.
 *   2. Reverse the first k elements.
 *   3. Reverse the remaining n - k elements.
 *
 * This avoids extra space and runs in O(n) time.
 *
 * Example:
 *   Input:  [1, 2, 3, 4, 5, 6, 7], k = 3
 *   Output: [5, 6, 7, 1, 2, 3, 4]
 *
 * Edge cases:
 *   - k greater than array length (use k % n)
 *   - k equals 0 or array length (no change)
 *   - Empty or single-element array
 *
 * Complexity: O(n) time, O(1) space
 */
public class Challenge02 {

    /**
     * Rotates the array to the right by k positions using the
     * three-reversal technique.
     *
     * @param arr the array to rotate
     * @param k   number of positions to rotate right
     */
    public static void rotateArray(int[] arr, int k) {
        if (arr == null || arr.length <= 1 || k <= 0) {
            return;
        }

        int n = arr.length;
        k = k % n; // Handle k > n
        if (k == 0) {
            return;
        }

        // Step 1: Reverse the whole array
        reverse(arr, 0, n - 1);
        // Step 2: Reverse the first k elements
        reverse(arr, 0, k - 1);
        // Step 3: Reverse the remaining n - k elements
        reverse(arr, k, n - 1);
    }

    /**
     * Reverses elements in arr between indices left and right (inclusive).
     */
    private static void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Before: " + Arrays.toString(arr1));
        rotateArray(arr1, 3);
        System.out.println("After rotating by 3: " + Arrays.toString(arr1));
        System.out.println();

        int[] arr2 = {10, 20, 30, 40, 50};
        System.out.println("Before: " + Arrays.toString(arr2));
        rotateArray(arr2, 7); // k > length, effective rotation = 7 % 5 = 2
        System.out.println("After rotating by 7 (effective 2): " + Arrays.toString(arr2));
        System.out.println();

        int[] arr3 = {1};
        System.out.println("Before: " + Arrays.toString(arr3));
        rotateArray(arr3, 5);
        System.out.println("After rotating single element by 5: " + Arrays.toString(arr3));
    }
}
