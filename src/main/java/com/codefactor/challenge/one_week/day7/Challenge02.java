package com.codefactor.challenge.one_week.day7;

import java.util.*;

/**
 * Challenge 02 - Find the First Duplicate in a List
 *
 * Given a list of integers, find the first element that appears more than once.
 *
 * Approaches:
 * 1. HashSet - track seen elements
 * 2. Brute force - nested loops
 *
 * Complexity: O(n) time with HashSet, O(n^2) brute force
 *
 * Example:
 *   Input:  [2, 1, 3, 5, 3, 2]
 *   Output: 3 (first duplicate encountered)
 */
public class Challenge02 {

    /** Approach 1: HashSet - O(n) */
    public static Integer firstDuplicateSet(List<Integer> list) {
        Set<Integer> seen = new HashSet<>();
        for (int num : list) {
            if (!seen.add(num)) {
                return num;
            }
        }
        return null;
    }

    /** Approach 2: Brute force - O(n^2) */
    public static Integer firstDuplicateBrute(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(i).equals(list.get(j))) {
                    return list.get(i);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
            Arrays.asList(2, 1, 3, 5, 3, 2),
            Arrays.asList(1, 2, 3, 4, 5),
            Arrays.asList(1, 1, 2, 3),
            Arrays.asList(5, 5)
        );
        for (List<Integer> test : tests) {
            System.out.println("Input: " + test);
            System.out.println("  HashSet: " + firstDuplicateSet(test));
            System.out.println("  Brute:   " + firstDuplicateBrute(test));
            System.out.println();
        }
    }
}
