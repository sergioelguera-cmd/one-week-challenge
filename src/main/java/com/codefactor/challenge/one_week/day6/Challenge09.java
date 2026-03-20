package com.codefactor.challenge.one_week.day6;

import java.util.Arrays;

/**
 * Challenge 09 - Find Intersection of Two Arrays
 *
 * Given two integer arrays, find their intersection (common elements).
 * Each element in the result should appear as many times as it shows in both arrays.
 *
 * Approaches:
 * 1. Sorting + Two Pointers
 * 2. HashMap frequency count
 *
 * Complexity:
 *   Sort: O(n log n + m log m) time
 *   HashMap: O(n + m) time, O(min(n,m)) space
 *
 * Example:
 *   Input:  [1, 2, 2, 1], [2, 2]
 *   Output: [2, 2]
 */
public class Challenge09 {

    /** Approach 1: Sort + Two Pointers */
    public static int[] intersectSorted(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int i = 0, j = 0, k = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                result[k++] = nums1[i];
                i++;
                j++;
            }
        }
        return Arrays.copyOf(result, k);
    }

    /** Approach 2: HashMap frequency */
    public static int[] intersectMap(int[] nums1, int[] nums2) {
        java.util.Map<Integer, Integer> freq = new java.util.HashMap<>();
        for (int n : nums1) {
            freq.merge(n, 1, Integer::sum);
        }

        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int k = 0;
        for (int n : nums2) {
            if (freq.getOrDefault(n, 0) > 0) {
                result[k++] = n;
                freq.merge(n, -1, Integer::sum);
            }
        }
        return Arrays.copyOf(result, k);
    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 2, 1}, b1 = {2, 2};
        System.out.println("Arrays: " + Arrays.toString(a1) + " & " + Arrays.toString(b1));
        System.out.println("  Sorted: " + Arrays.toString(intersectSorted(a1.clone(), b1.clone())));
        System.out.println("  Map:    " + Arrays.toString(intersectMap(a1, b1)));

        int[] a2 = {4, 9, 5}, b2 = {9, 4, 9, 8, 4};
        System.out.println("Arrays: " + Arrays.toString(a2) + " & " + Arrays.toString(b2));
        System.out.println("  Sorted: " + Arrays.toString(intersectSorted(a2.clone(), b2.clone())));
        System.out.println("  Map:    " + Arrays.toString(intersectMap(a2, b2)));
    }
}
