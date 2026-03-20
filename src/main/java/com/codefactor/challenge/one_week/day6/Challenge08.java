package com.codefactor.challenge.one_week.day6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Challenge 08 - Two Sum Problem
 *
 * Given an array of integers and a target sum, find two numbers that add up to the target.
 * Return their indices.
 *
 * Approaches:
 * 1. Brute force O(n^2)
 * 2. HashMap O(n)
 * 3. Sorted array + two pointers O(n log n)
 *
 * Example:
 *   Input:  nums = [2, 7, 11, 15], target = 9
 *   Output: [0, 1] (because nums[0] + nums[1] = 9)
 */
public class Challenge08 {

    /** Approach 1: Brute force - check all pairs */
    public static int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    /** Approach 2: HashMap - O(n) single pass */
    public static int[] twoSumHashMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Array: " + Arrays.toString(nums1) + ", Target: " + target1);
        System.out.println("  Brute:   " + Arrays.toString(twoSumBrute(nums1, target1)));
        System.out.println("  HashMap: " + Arrays.toString(twoSumHashMap(nums1, target1)));

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("Array: " + Arrays.toString(nums2) + ", Target: " + target2);
        System.out.println("  Brute:   " + Arrays.toString(twoSumBrute(nums2, target2)));
        System.out.println("  HashMap: " + Arrays.toString(twoSumHashMap(nums2, target2)));

        int[] nums3 = {1, 5, 3, 7, 8, 2};
        int target3 = 10;
        System.out.println("Array: " + Arrays.toString(nums3) + ", Target: " + target3);
        System.out.println("  Brute:   " + Arrays.toString(twoSumBrute(nums3, target3)));
        System.out.println("  HashMap: " + Arrays.toString(twoSumHashMap(nums3, target3)));
    }
}
