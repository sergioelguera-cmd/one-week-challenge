package com.codefactor.challenge.one_week.day6;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Challenge 05 - Find All Duplicate Elements in an Array
 *
 * Given an array of integers, find every element that appears more than once.
 *
 * Two approaches:
 *
 * 1. HashSet Approach:
 *    Iterate through the array, attempt to add each element to a Set.
 *    If add() returns false the element is a duplicate.
 *
 * 2. Stream Approach:
 *    Group elements by identity, filter groups with count > 1,
 *    and collect the keys.
 *
 * Example:
 *   Input:  [1, 3, 4, 2, 6, 3, 4, 8, 1]
 *   Output: [1, 3, 4]
 *
 * Complexity: O(n) time, O(n) space for both approaches
 */
public class Challenge05 {

    /**
     * Finds duplicates using a HashSet.
     * The first occurrence is added to 'seen'; subsequent occurrences
     * are added to 'duplicates'.
     *
     * @param arr the input array
     * @return a set of duplicate values
     */
    public static Set<Integer> findDuplicatesHashSet(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new LinkedHashSet<>();

        for (int num : arr) {
            if (!seen.add(num)) {
                // add() returns false if the element was already present
                duplicates.add(num);
            }
        }

        return duplicates;
    }

    /**
     * Finds duplicates using Java Streams.
     * Groups elements by value, keeps only groups with count > 1.
     *
     * @param arr the input array
     * @return a list of duplicate values
     */
    public static List<Integer> findDuplicatesStream(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(n -> n, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 4, 2, 6, 3, 4, 8, 1};
        System.out.println("Array:             " + Arrays.toString(arr1));
        System.out.println("HashSet approach:  " + findDuplicatesHashSet(arr1));
        System.out.println("Stream approach:   " + findDuplicatesStream(arr1));
        System.out.println();

        int[] arr2 = {5, 5, 5, 5};
        System.out.println("Array:             " + Arrays.toString(arr2));
        System.out.println("HashSet approach:  " + findDuplicatesHashSet(arr2));
        System.out.println("Stream approach:   " + findDuplicatesStream(arr2));
        System.out.println();

        int[] arr3 = {1, 2, 3, 4};
        System.out.println("Array:             " + Arrays.toString(arr3));
        System.out.println("HashSet approach:  " + findDuplicatesHashSet(arr3));
        System.out.println("Stream approach:   " + findDuplicatesStream(arr3));
    }
}
