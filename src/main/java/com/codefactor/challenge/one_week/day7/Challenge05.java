package com.codefactor.challenge.one_week.day7;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Challenge 05 - Sort a Map by Values
 *
 * Given a Map, sort it by its values in ascending and descending order.
 *
 * Approaches:
 * 1. Using Stream API
 * 2. Using List of Map.Entry with Comparator
 *
 * Example:
 *   Input:  {Java=3, Python=1, C++=2}
 *   Output: {Python=1, C++=2, Java=3} (ascending)
 */
public class Challenge05 {

    /** Approach 1: Stream API - ascending */
    public static <K, V extends Comparable<V>> Map<K, V> sortByValueStream(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                ));
    }

    /** Approach 2: List sort - descending */
    public static <K, V extends Comparable<V>> Map<K, V> sortByValueDescending(Map<K, V> map) {
        List<Map.Entry<K, V>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Map.Entry.<K, V>comparingByValue().reversed());
        Map<K, V> sorted = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : entries) {
            sorted.put(entry.getKey(), entry.getValue());
        }
        return sorted;
    }

    public static void main(String[] args) {
        Map<String, Integer> languages = new HashMap<>();
        languages.put("Java", 3);
        languages.put("Python", 1);
        languages.put("C++", 5);
        languages.put("JavaScript", 2);
        languages.put("Go", 4);

        System.out.println("Original:   " + languages);
        System.out.println("Ascending:  " + sortByValueStream(languages));
        System.out.println("Descending: " + sortByValueDescending(languages));
    }
}
