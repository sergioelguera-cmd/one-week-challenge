package com.codefactor.challenge.one_week.day13;

import java.util.*;

/**
 * Challenge 10 - Sliding Window Maximum
 *
 * Given an array and window size k, find the maximum in each window.
 *
 * Approaches:
 * 1. Brute force O(n*k)
 * 2. Deque-based O(n)
 *
 * Example:
 *   Input:  [1,3,-1,-3,5,3,6,7], k=3
 *   Output: [3,3,5,5,6,7]
 */
public class Challenge10 {

    /** Brute force O(n*k) */
    public static int[] maxSlidingWindowBrute(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i++) {
            int max = nums[i];
            for (int j = i + 1; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    /** Deque approach O(n) - stores indices */
    public static int[] maxSlidingWindowDeque(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < nums.length; i++) {
            // Remove elements outside window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // Remove smaller elements (they'll never be the max)
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("Array: " + Arrays.toString(nums) + ", k=" + k);
        System.out.println("Brute: " + Arrays.toString(maxSlidingWindowBrute(nums, k)));
        System.out.println("Deque: " + Arrays.toString(maxSlidingWindowDeque(nums, k)));

        int[] nums2 = {9, 11, 8, 5, 7, 10};
        System.out.println("\nArray: " + Arrays.toString(nums2) + ", k=2");
        System.out.println("Deque: " + Arrays.toString(maxSlidingWindowDeque(nums2, 2)));
    }
}
