package com.codefactor.challenge.one_week.day6;

import java.util.Arrays;

/**
 * Challenge 04 - Merge Two Sorted Arrays
 *
 * Given two sorted integer arrays, merge them into a single sorted array.
 *
 * Approach (Two-Pointer Merge):
 *   - Maintain a pointer for each input array and one for the result.
 *   - Compare current elements; place the smaller one into the result.
 *   - Copy any remaining elements once one array is exhausted.
 *
 * This is the merge step of merge sort.
 *
 * Example:
 *   Input:  arr1 = [1, 3, 5, 7], arr2 = [2, 4, 6, 8]
 *   Output: [1, 2, 3, 4, 5, 6, 7, 8]
 *
 * Complexity: O(n + m) time, O(n + m) space
 */
public class Challenge04 {

    /**
     * Merges two sorted arrays into one sorted array using the two-pointer technique.
     *
     * @param arr1 first sorted array
     * @param arr2 second sorted array
     * @return merged sorted array
     */
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;
        int[] result = new int[n + m];

        int i = 0; // pointer for arr1
        int j = 0; // pointer for arr2
        int k = 0; // pointer for result

        // Compare and place the smaller element
        while (i < n && j < m) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        // Copy remaining elements from arr1 (if any)
        while (i < n) {
            result[k++] = arr1[i++];
        }

        // Copy remaining elements from arr2 (if any)
        while (j < m) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 3, 5, 7};
        int[] a2 = {2, 4, 6, 8};
        System.out.println("Array 1: " + Arrays.toString(a1));
        System.out.println("Array 2: " + Arrays.toString(a2));
        System.out.println("Merged:  " + Arrays.toString(mergeSortedArrays(a1, a2)));
        System.out.println();

        int[] b1 = {1, 1, 3};
        int[] b2 = {2, 2, 4, 5, 6};
        System.out.println("Array 1: " + Arrays.toString(b1));
        System.out.println("Array 2: " + Arrays.toString(b2));
        System.out.println("Merged:  " + Arrays.toString(mergeSortedArrays(b1, b2)));
        System.out.println();

        int[] c1 = {};
        int[] c2 = {10, 20, 30};
        System.out.println("Array 1: " + Arrays.toString(c1));
        System.out.println("Array 2: " + Arrays.toString(c2));
        System.out.println("Merged:  " + Arrays.toString(mergeSortedArrays(c1, c2)));
    }
}
