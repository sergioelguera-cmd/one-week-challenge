package com.codefactor.challenge.one_week.day8;

import java.util.*;
import java.util.stream.*;

/**
 * Challenge 05 - Find Duplicates and Unique Elements using Streams
 *
 * Given a list, find:
 * 1. All duplicate elements
 * 2. All unique (non-duplicate) elements
 * 3. Frequency of each element
 *
 * Demonstrates: groupingBy, counting, filter, distinct
 */
public class Challenge05 {

    /** Find elements that appear more than once */
    public static <T> List<T> findDuplicates(List<T> list) {
        return list.stream()
            .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
            .entrySet().stream()
            .filter(e -> e.getValue() > 1)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

    /** Find elements that appear exactly once */
    public static <T> List<T> findUniques(List<T> list) {
        return list.stream()
            .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
            .entrySet().stream()
            .filter(e -> e.getValue() == 1)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

    /** Get frequency map */
    public static <T> Map<T, Long> frequencyMap(List<T> list) {
        return list.stream()
            .collect(Collectors.groupingBy(e -> e, LinkedHashMap::new, Collectors.counting()));
    }

    /** Alternative: find duplicates using Set.add() */
    public static <T> Set<T> findDuplicatesSet(List<T> list) {
        Set<T> seen = new HashSet<>();
        return list.stream()
            .filter(e -> !seen.add(e))
            .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 3, 5, 1, 6);
        System.out.println("Input:      " + numbers);
        System.out.println("Duplicates: " + findDuplicates(numbers));
        System.out.println("Uniques:    " + findUniques(numbers));
        System.out.println("Frequency:  " + frequencyMap(numbers));
        System.out.println("Dup (Set):  " + findDuplicatesSet(numbers));

        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "date");
        System.out.println("\nInput:      " + words);
        System.out.println("Duplicates: " + findDuplicates(words));
        System.out.println("Frequency:  " + frequencyMap(words));
    }
}
