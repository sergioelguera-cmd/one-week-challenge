package com.codefactor.challenge.one_week.day8;

import java.util.*;
import java.util.stream.*;

/**
 * Challenge 09 - Convert Between Collections using Streams
 *
 * Common interview conversions:
 * - List to Map
 * - Map to List
 * - Array to List and back
 * - List to Set and back
 * - String to List of characters
 */
public class Challenge09 {

    record Person(int id, String name) {}

    public static void main(String[] args) {
        List<Person> people = List.of(
            new Person(1, "Alice"),
            new Person(2, "Bob"),
            new Person(3, "Charlie")
        );

        // List to Map (id -> name)
        Map<Integer, String> idToName = people.stream()
            .collect(Collectors.toMap(Person::id, Person::name));
        System.out.println("List -> Map: " + idToName);

        // Map to List of values
        List<String> names = new ArrayList<>(idToName.values());
        System.out.println("Map values -> List: " + names);

        // Map to List of entries
        List<String> entries = idToName.entrySet().stream()
            .map(e -> e.getKey() + "=" + e.getValue())
            .collect(Collectors.toList());
        System.out.println("Map entries -> List: " + entries);

        // Array to List
        String[] array = {"X", "Y", "Z"};
        List<String> fromArray = Arrays.stream(array).collect(Collectors.toList());
        System.out.println("Array -> List: " + fromArray);

        // List to Array
        String[] backToArray = fromArray.toArray(String[]::new);
        System.out.println("List -> Array: " + Arrays.toString(backToArray));

        // List to Set (removes duplicates)
        List<Integer> withDups = Arrays.asList(1, 2, 2, 3, 3, 3);
        Set<Integer> uniqueSet = new LinkedHashSet<>(withDups);
        System.out.println("List -> Set: " + uniqueSet);

        // Set to sorted List
        List<Integer> sortedList = uniqueSet.stream()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("Set -> sorted List: " + sortedList);

        // String to List<Character>
        String str = "Hello";
        List<Character> chars = str.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());
        System.out.println("String -> chars: " + chars);

        // int[] to List<Integer>
        int[] intArray = {10, 20, 30};
        List<Integer> intList = Arrays.stream(intArray)
            .boxed()
            .collect(Collectors.toList());
        System.out.println("int[] -> List<Integer>: " + intList);
    }
}
