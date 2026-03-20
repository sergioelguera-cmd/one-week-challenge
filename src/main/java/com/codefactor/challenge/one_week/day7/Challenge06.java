package com.codefactor.challenge.one_week.day7;

import java.util.*;

/**
 * Challenge 06 - Frequency Count and Top K Elements
 *
 * Given a list of integers, find the K most frequent elements.
 *
 * Approaches:
 * 1. HashMap + PriorityQueue (min-heap)
 * 2. HashMap + Bucket Sort
 *
 * Complexity:
 *   Heap: O(n log k)
 *   Bucket: O(n)
 *
 * Example:
 *   Input:  [1,1,1,2,2,3], k=2
 *   Output: [1, 2]
 */
public class Challenge06 {

    /** Approach 1: HashMap + Min-Heap */
    public static List<Integer> topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.merge(n, 1, Integer::sum);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(freq::get)
        );
        for (int key : freq.keySet()) {
            minHeap.offer(key);
            if (minHeap.size() > k) minHeap.poll();
        }
        List<Integer> result = new ArrayList<>(minHeap);
        result.sort((a, b) -> freq.get(b) - freq.get(a));
        return result;
    }

    /** Approach 2: Bucket Sort */
    @SuppressWarnings("unchecked")
    public static List<Integer> topKFrequentBucket(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.merge(n, 1, Integer::sum);

        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int f = entry.getValue();
            if (buckets[f] == null) buckets[f] = new ArrayList<>();
            buckets[f].add(entry.getKey());
        }

        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            if (buckets[i] != null) result.addAll(buckets[i]);
        }
        return result.subList(0, k);
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println("Input: " + Arrays.toString(nums) + ", k=" + k);
        System.out.println("  Heap:   " + topKFrequentHeap(nums, k));
        System.out.println("  Bucket: " + topKFrequentBucket(nums, k));

        int[] nums2 = {4, 1, -1, 2, -1, 2, 3};
        System.out.println("Input: " + Arrays.toString(nums2) + ", k=2");
        System.out.println("  Heap:   " + topKFrequentHeap(nums2, 2));
        System.out.println("  Bucket: " + topKFrequentBucket(nums2, 2));
    }
}
