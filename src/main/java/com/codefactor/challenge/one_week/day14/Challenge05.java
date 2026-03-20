package com.codefactor.challenge.one_week.day14;

import java.util.*;

/**
 * Challenge 05 - Immutable Collections and Convenience Factory Methods
 *
 * Java 9+ collection factory methods and unmodifiable wrappers.
 *
 * List.of(), Set.of(), Map.of(), Map.ofEntries()
 * Collections.unmodifiableList() vs List.copyOf()
 */
public class Challenge05 {

    public static void main(String[] args) {
        // List.of - immutable list (Java 9+)
        List<String> immutableList = List.of("A", "B", "C");
        System.out.println("List.of: " + immutableList);
        tryModify(() -> immutableList.add("D"), "List.of().add()");

        // Set.of - immutable set
        Set<Integer> immutableSet = Set.of(1, 2, 3);
        System.out.println("Set.of: " + immutableSet);

        // Map.of - immutable map (up to 10 pairs)
        Map<String, Integer> immutableMap = Map.of("one", 1, "two", 2, "three", 3);
        System.out.println("Map.of: " + immutableMap);

        // Map.ofEntries - immutable map (any number of pairs)
        Map<String, Integer> map = Map.ofEntries(
            Map.entry("a", 1),
            Map.entry("b", 2),
            Map.entry("c", 3)
        );
        System.out.println("Map.ofEntries: " + map);

        // List.copyOf - create immutable copy (Java 10+)
        List<String> mutable = new ArrayList<>(List.of("X", "Y"));
        List<String> copy = List.copyOf(mutable);
        mutable.add("Z"); // doesn't affect copy
        System.out.println("\nOriginal after add: " + mutable);
        System.out.println("copyOf (unchanged): " + copy);

        // Collections.unmodifiableList (view, not copy)
        List<String> source = new ArrayList<>(List.of("1", "2"));
        List<String> unmodifiable = Collections.unmodifiableList(source);
        source.add("3"); // DOES affect the unmodifiable view!
        System.out.println("\nSource after add: " + source);
        System.out.println("unmodifiableList: " + unmodifiable + " (reflects source changes!)");

        // var keyword (Java 10+)
        var numbers = List.of(1, 2, 3); // type inferred as List<Integer>
        System.out.println("\nvar inference: " + numbers.getClass().getSimpleName());
    }

    private static void tryModify(Runnable action, String description) {
        try {
            action.run();
        } catch (UnsupportedOperationException e) {
            System.out.println("  " + description + " -> UnsupportedOperationException");
        }
    }
}
