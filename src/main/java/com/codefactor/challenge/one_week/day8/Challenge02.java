package com.codefactor.challenge.one_week.day8;

import java.util.*;
import java.util.stream.*;

/**
 * Challenge 02 - Stream map, flatMap, and reduce
 *
 * Demonstrate the difference between map and flatMap,
 * and practical uses of reduce.
 *
 * Key concepts:
 * - map: 1-to-1 transformation
 * - flatMap: 1-to-many, flattens nested structures
 * - reduce: combines elements into single result
 */
public class Challenge02 {

    public static void main(String[] args) {
        // map: transform each element
        List<String> names = Arrays.asList("alice", "bob", "charlie");
        List<String> capitalized = names.stream()
            .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
            .collect(Collectors.toList());
        System.out.println("map (capitalize): " + capitalized);

        // flatMap: flatten list of lists
        List<List<Integer>> nested = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5),
            Arrays.asList(6, 7, 8, 9)
        );
        List<Integer> flat = nested.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        System.out.println("flatMap (flatten): " + flat);

        // flatMap: split sentences into words
        List<String> sentences = Arrays.asList("Hello World", "Java Streams", "Are Great");
        List<String> words = sentences.stream()
            .flatMap(s -> Arrays.stream(s.split(" ")))
            .collect(Collectors.toList());
        System.out.println("flatMap (words):   " + words);

        // reduce: sum
        int sum = IntStream.rangeClosed(1, 10).reduce(0, Integer::sum);
        System.out.println("reduce (sum 1-10): " + sum);

        // reduce: find longest string
        Optional<String> longest = names.stream()
            .reduce((a, b) -> a.length() >= b.length() ? a : b);
        System.out.println("reduce (longest):  " + longest.orElse(""));

        // reduce: concatenate with delimiter
        String joined = names.stream()
            .reduce((a, b) -> a + ", " + b)
            .orElse("");
        System.out.println("reduce (join):     " + joined);

        // Collectors.joining (preferred for string concatenation)
        String joinedCollector = names.stream()
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Collectors.joining: " + joinedCollector);
    }
}
