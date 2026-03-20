package com.codefactor.challenge.one_week.day10;

import java.util.*;
import java.util.concurrent.*;

/**
 * Challenge 08 - ConcurrentHashMap and Thread-Safe Collections
 *
 * Compare thread-safe collection options:
 * - Collections.synchronizedMap (wrapper, coarse-grained)
 * - ConcurrentHashMap (segment locking, fine-grained)
 * - CopyOnWriteArrayList (for read-heavy scenarios)
 */
public class Challenge08 {

    public static void main(String[] args) throws InterruptedException {
        // ConcurrentHashMap - thread-safe without external sync
        System.out.println("=== ConcurrentHashMap ===");
        ConcurrentHashMap<String, Integer> concMap = new ConcurrentHashMap<>();

        // Atomic operations
        concMap.put("visits", 0);
        concMap.compute("visits", (k, v) -> v == null ? 1 : v + 1);
        concMap.merge("visits", 1, Integer::sum);
        System.out.println("  Visits: " + concMap.get("visits"));

        // putIfAbsent - atomic check-and-put
        concMap.putIfAbsent("newKey", 42);
        System.out.println("  newKey: " + concMap.get("newKey"));

        // Concurrent word counter
        ConcurrentHashMap<String, Integer> wordCount = new ConcurrentHashMap<>();
        String[] words = {"hello", "world", "hello", "java", "world", "hello"};

        Thread[] threads = new Thread[words.length];
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            threads[i] = new Thread(() -> wordCount.merge(word, 1, Integer::sum));
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("  Word counts: " + wordCount);

        // CopyOnWriteArrayList - safe iteration while modifying
        System.out.println("\n=== CopyOnWriteArrayList ===");
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>(
            Arrays.asList("A", "B", "C"));
        // Can iterate and modify concurrently without ConcurrentModificationException
        for (String item : cowList) {
            if (item.equals("B")) cowList.add("D");
            System.out.println("  Item: " + item);
        }
        System.out.println("  Final list: " + cowList);

        // Collections.synchronizedMap
        System.out.println("\n=== synchronizedMap ===");
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        syncMap.put("key", 1);
        // Must synchronize on map for iteration
        synchronized (syncMap) {
            syncMap.forEach((k, v) -> System.out.println("  " + k + "=" + v));
        }
    }
}
