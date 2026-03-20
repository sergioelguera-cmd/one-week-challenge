package com.codefactor.challenge.one_week.day7;

import java.util.*;

/**
 * Challenge 01 - HashMap vs TreeMap vs LinkedHashMap
 *
 * Demonstrate the differences between the three Map implementations:
 * - HashMap: no ordering guarantee, O(1) operations
 * - TreeMap: sorted by key, O(log n) operations
 * - LinkedHashMap: insertion-order, O(1) operations
 *
 * Common interview question: When would you choose each?
 */
public class Challenge01 {

    public static void main(String[] args) {
        // HashMap - no order guaranteed
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Charlie", 3);
        hashMap.put("Alice", 1);
        hashMap.put("Bob", 2);
        System.out.println("HashMap (no order):       " + hashMap);

        // TreeMap - sorted by natural key order
        Map<String, Integer> treeMap = new TreeMap<>(hashMap);
        System.out.println("TreeMap (sorted by key):  " + treeMap);

        // LinkedHashMap - insertion order preserved
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("Charlie", 3);
        linkedMap.put("Alice", 1);
        linkedMap.put("Bob", 2);
        System.out.println("LinkedHashMap (insert order): " + linkedMap);

        // LinkedHashMap with access order (LRU cache behavior)
        Map<String, Integer> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put("A", 1);
        accessOrderMap.put("B", 2);
        accessOrderMap.put("C", 3);
        accessOrderMap.get("A"); // access A, moves it to end
        System.out.println("LinkedHashMap (access order): " + accessOrderMap);

        // TreeMap with custom comparator (reverse order)
        Map<String, Integer> reverseTree = new TreeMap<>(Comparator.reverseOrder());
        reverseTree.putAll(hashMap);
        System.out.println("TreeMap (reverse order):  " + reverseTree);
    }
}
