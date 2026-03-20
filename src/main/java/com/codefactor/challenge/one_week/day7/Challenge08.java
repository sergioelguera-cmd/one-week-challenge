package com.codefactor.challenge.one_week.day7;

import java.util.*;

/**
 * Challenge 08 - Merge Two Sorted Lists
 *
 * Merge K sorted lists into one sorted list using a PriorityQueue.
 * Also includes merging two sorted lists iteratively.
 *
 * Complexity: O(N log K) where N = total elements, K = number of lists
 */
public class Challenge08 {

    /** Merge two sorted lists */
    public static List<Integer> mergeTwoLists(List<Integer> l1, List<Integer> l2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < l1.size() && j < l2.size()) {
            if (l1.get(i) <= l2.get(j)) {
                result.add(l1.get(i++));
            } else {
                result.add(l2.get(j++));
            }
        }
        while (i < l1.size()) result.add(l1.get(i++));
        while (j < l2.size()) result.add(l2.get(j++));
        return result;
    }

    /** Merge K sorted lists using PriorityQueue */
    public static List<Integer> mergeKLists(List<List<Integer>> lists) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            Comparator.comparingInt(a -> lists.get(a[0]).get(a[1]))
        );
        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                pq.offer(new int[]{i, 0});
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int listIdx = curr[0], elemIdx = curr[1];
            result.add(lists.get(listIdx).get(elemIdx));
            if (elemIdx + 1 < lists.get(listIdx).size()) {
                pq.offer(new int[]{listIdx, elemIdx + 1});
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 4, 7);
        List<Integer> l2 = Arrays.asList(2, 5, 8);
        System.out.println("Merge two: " + mergeTwoLists(l1, l2));

        List<List<Integer>> kLists = Arrays.asList(
            Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8),
            Arrays.asList(3, 6, 9)
        );
        System.out.println("Merge K:   " + mergeKLists(kLists));
    }
}
