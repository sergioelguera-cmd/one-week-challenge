package com.codefactor.challenge.one_week.day8;

import java.util.*;
import java.util.stream.*;

/**
 * Challenge 07 - Stream of Primitives and Boxing
 *
 * Demonstrate IntStream, LongStream, DoubleStream and the
 * difference from Stream<Integer> (boxing/unboxing).
 *
 * Key methods: range, rangeClosed, sum, average, asDoubleStream, boxed
 */
public class Challenge07 {

    public static void main(String[] args) {
        // IntStream.range vs rangeClosed
        System.out.println("range(1,5):       " +
            IntStream.range(1, 5).boxed().collect(Collectors.toList()));
        System.out.println("rangeClosed(1,5): " +
            IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList()));

        // Sum, average, min, max
        IntStream.Builder builder = IntStream.builder();
        builder.add(10).add(20).add(30).add(40).add(50);
        IntSummaryStatistics stats = builder.build().summaryStatistics();
        System.out.println("\nStats: " + stats);

        // Generate fibonacci using iterate
        System.out.println("\nFirst 10 Fibonacci numbers:");
        Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
            .limit(10)
            .map(f -> f[0])
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Convert array to stream and back
        int[] array = {5, 3, 1, 4, 2};
        int[] sorted = Arrays.stream(array).sorted().toArray();
        System.out.println("\nSorted array: " + Arrays.toString(sorted));

        // mapToInt for efficient numeric operations
        List<String> numStrings = Arrays.asList("10", "20", "30", "40");
        int sum = numStrings.stream()
            .mapToInt(Integer::parseInt)
            .sum();
        System.out.println("Sum of parsed ints: " + sum);

        // DoubleStream for calculations
        double average = DoubleStream.of(1.5, 2.5, 3.5, 4.5)
            .average()
            .orElse(0.0);
        System.out.printf("Average: %.2f%n", average);
    }
}
