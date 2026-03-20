package com.codefactor.challenge.one_week.day14;

import java.util.*;

/**
 * Challenge 04 - Method References and Constructor References
 *
 * Four types of method references:
 * 1. Static method:     Class::staticMethod
 * 2. Instance method:   object::instanceMethod
 * 3. Arbitrary object:  Class::instanceMethod
 * 4. Constructor:       Class::new
 */
public class Challenge04 {

    record Person(String name, int age) {}

    static int compareByAge(Person a, Person b) {
        return Integer.compare(a.age(), b.age());
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(List.of(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 35)
        ));

        // 1. Static method reference
        people.sort(Challenge04::compareByAge);
        System.out.println("Sorted by age (static ref): " + people);

        // 2. Instance method of a particular object
        var printer = new Object() {
            void print(Person p) { System.out.println("  -> " + p); }
        };
        System.out.println("\nInstance method ref:");
        people.forEach(printer::print);

        // 3. Instance method of arbitrary object of a type
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        names.sort(String::compareToIgnoreCase);
        System.out.println("\nSorted names (arbitrary instance ref): " + names);

        // 4. Constructor reference
        List<String> nameList = List.of("Alice", "Bob", "Charlie");

        // String[]::new as constructor reference for toArray
        String[] array = nameList.toArray(String[]::new);
        System.out.println("Constructor ref (toArray): " + Arrays.toString(array));

        // Using Function as constructor reference
        java.util.function.Function<String, StringBuilder> sbCreator = StringBuilder::new;
        StringBuilder sb = sbCreator.apply("Hello");
        System.out.println("Constructor ref: " + sb);

        // Method reference with streams
        System.out.println("\nMethod refs with streams:");
        List<String> words = List.of("hello", "WORLD", "Java");
        words.stream()
            .map(String::toLowerCase)
            .filter(((java.util.function.Predicate<String>) String::isEmpty).negate())
            .forEach(System.out::println);
    }
}
