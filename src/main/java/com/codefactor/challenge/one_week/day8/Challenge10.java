package com.codefactor.challenge.one_week.day8;

import java.util.*;
import java.util.stream.*;

/**
 * Challenge 10 - Optional and Streams
 *
 * Demonstrate proper use of Optional with streams.
 * Common interview question: How to avoid NullPointerException?
 *
 * Key methods: of, ofNullable, map, flatMap, orElse, orElseGet, ifPresent
 */
public class Challenge10 {

    record User(String name, String email, Optional<String> phone) {}

    public static void main(String[] args) {
        // Creating Optional
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(null);

        System.out.println("present: " + present);
        System.out.println("empty:   " + empty);
        System.out.println("nullable: " + nullable);

        // Safe value extraction
        System.out.println("\norElse:    " + empty.orElse("default"));
        System.out.println("orElseGet: " + empty.orElseGet(() -> "computed default"));

        // map and flatMap
        Optional<String> upper = present.map(String::toUpperCase);
        System.out.println("map(toUpperCase): " + upper);

        // Chaining Optional operations
        Optional<Integer> length = present
            .filter(s -> s.length() > 3)
            .map(String::length);
        System.out.println("filter + map: " + length);

        // Using Optional with streams
        List<User> users = List.of(
            new User("Alice", "alice@mail.com", Optional.of("555-0101")),
            new User("Bob", "bob@mail.com", Optional.empty()),
            new User("Charlie", "charlie@mail.com", Optional.of("555-0303"))
        );

        // Get all phone numbers (filter out empty)
        List<String> phones = users.stream()
            .map(User::phone)
            .flatMap(Optional::stream)
            .collect(Collectors.toList());
        System.out.println("\nAll phones: " + phones);

        // Find first user with phone
        users.stream()
            .filter(u -> u.phone().isPresent())
            .findFirst()
            .ifPresent(u -> System.out.println("First with phone: " + u.name()));

        // Optional.or (Java 9+) - provide alternative Optional
        Optional<String> result = empty.or(() -> Optional.of("fallback"));
        System.out.println("or() fallback: " + result);

        // ifPresentOrElse (Java 9+)
        present.ifPresentOrElse(
            val -> System.out.println("Found: " + val),
            () -> System.out.println("Not found")
        );
    }
}
