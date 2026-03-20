package com.codefactor.challenge.one_week.day8;

import java.util.*;
import java.util.stream.*;

/**
 * Challenge 08 - Stream.iterate, generate, and Custom Collectors
 *
 * Demonstrate stream creation methods and building custom collectors.
 *
 * Covers: Stream.iterate, Stream.generate, Collector.of
 */
public class Challenge08 {

    public static void main(String[] args) {
        // Stream.iterate with predicate (Java 9+)
        System.out.println("Powers of 2 up to 1024:");
        Stream.iterate(1, n -> n <= 1024, n -> n * 2)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Stream.generate with supplier
        System.out.println("\n5 random UUIDs:");
        Stream.generate(UUID::randomUUID)
            .limit(5)
            .forEach(System.out::println);

        // Custom collector: joining with custom separator
        List<String> words = Arrays.asList("Hello", "World", "Java", "Streams");
        String result = words.stream()
            .collect(Collector.of(
                StringBuilder::new,
                (sb, s) -> {
                    if (sb.length() > 0) sb.append(" | ");
                    sb.append(s);
                },
                (sb1, sb2) -> {
                    if (sb1.length() > 0 && sb2.length() > 0) sb1.append(" | ");
                    return sb1.append(sb2);
                },
                StringBuilder::toString
            ));
        System.out.println("\nCustom collector: " + result);

        // Stream.ofNullable (Java 9+)
        String nullValue = null;
        String nonNull = "hello";
        long countNull = Stream.ofNullable(nullValue).count();
        long countNonNull = Stream.ofNullable(nonNull).count();
        System.out.println("\nofNullable(null): " + countNull);
        System.out.println("ofNullable(\"hello\"): " + countNonNull);

        // takeWhile and dropWhile (Java 9+)
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 1, 2);
        System.out.println("\ntakeWhile(< 4): " +
            nums.stream().takeWhile(n -> n < 4).collect(Collectors.toList()));
        System.out.println("dropWhile(< 4): " +
            nums.stream().dropWhile(n -> n < 4).collect(Collectors.toList()));
    }
}
