package com.codefactor.challenge.one_week.day8;

import java.util.*;
import java.util.stream.*;

/**
 * Challenge 04 - Parallel Streams and Performance
 *
 * Demonstrate parallel stream usage, when it helps, and common pitfalls.
 *
 * Key points:
 * - parallel() for CPU-bound tasks on large datasets
 * - Avoid for I/O operations or small datasets
 * - Thread-safety concerns with shared mutable state
 */
public class Challenge04 {

    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1, 1_000_000)
            .boxed()
            .collect(Collectors.toList());

        // Sequential sum
        long start = System.nanoTime();
        long seqSum = numbers.stream()
            .mapToLong(Integer::longValue)
            .sum();
        long seqTime = System.nanoTime() - start;
        System.out.println("Sequential sum: " + seqSum + " (" + seqTime / 1_000_000 + "ms)");

        // Parallel sum
        start = System.nanoTime();
        long parSum = numbers.parallelStream()
            .mapToLong(Integer::longValue)
            .sum();
        long parTime = System.nanoTime() - start;
        System.out.println("Parallel sum:   " + parSum + " (" + parTime / 1_000_000 + "ms)");

        // PITFALL: Shared mutable state (WRONG)
        System.out.println("\n=== Thread Safety Pitfall ===");
        List<Integer> unsafeList = new ArrayList<>();
        IntStream.rangeClosed(1, 1000).parallel().forEach(unsafeList::add);
        System.out.println("Unsafe parallel add size: " + unsafeList.size() + " (expected 1000)");

        // CORRECT: Use collect instead
        List<Integer> safeList = IntStream.rangeClosed(1, 1000)
            .parallel()
            .boxed()
            .collect(Collectors.toList());
        System.out.println("Safe parallel collect size: " + safeList.size());

        // forEachOrdered: maintains encounter order in parallel
        System.out.println("\nParallel with forEachOrdered (first 10):");
        IntStream.rangeClosed(1, 10)
            .parallel()
            .forEachOrdered(i -> System.out.print(i + " "));
        System.out.println();
    }
}
