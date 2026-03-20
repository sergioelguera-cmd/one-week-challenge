package com.codefactor.challenge.one_week.day7;

import java.util.*;

/**
 * Challenge 10 - ConcurrentModificationException and Safe Removal
 *
 * Demonstrate the common pitfall of modifying a collection during iteration
 * and how to avoid ConcurrentModificationException.
 *
 * Approaches:
 * 1. Iterator.remove() - safe removal during iteration
 * 2. removeIf() - Java 8+ predicate-based removal
 * 3. CopyOnWriteArrayList for concurrent scenarios
 */
public class Challenge10 {

    /** WRONG: This throws ConcurrentModificationException */
    public static void unsafeRemoval(List<String> list) {
        try {
            for (String s : list) {
                if (s.startsWith("B")) {
                    list.remove(s); // throws ConcurrentModificationException
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("  ConcurrentModificationException caught!");
        }
    }

    /** Approach 1: Iterator.remove() */
    public static List<String> safeRemovalIterator(List<String> original) {
        List<String> list = new ArrayList<>(original);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().startsWith("B")) {
                it.remove();
            }
        }
        return list;
    }

    /** Approach 2: removeIf (Java 8+) */
    public static List<String> safeRemovalRemoveIf(List<String> original) {
        List<String> list = new ArrayList<>(original);
        list.removeIf(s -> s.startsWith("B"));
        return list;
    }

    /** Approach 3: Collect non-matching into new list */
    public static List<String> safeRemovalNewList(List<String> original) {
        List<String> result = new ArrayList<>();
        for (String s : original) {
            if (!s.startsWith("B")) {
                result.add(s);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Barbara", "Dave");
        System.out.println("Original: " + names);

        System.out.println("\nUnsafe removal (for-each + list.remove):");
        unsafeRemoval(new ArrayList<>(names));

        System.out.println("\nIterator.remove(): " + safeRemovalIterator(names));
        System.out.println("removeIf():        " + safeRemovalRemoveIf(names));
        System.out.println("New list:          " + safeRemovalNewList(names));
    }
}
