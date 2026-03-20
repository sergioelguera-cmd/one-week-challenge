package com.codefactor.challenge.one_week.day10;

import java.util.concurrent.*;

/**
 * Challenge 03 - ExecutorService and Thread Pools
 *
 * Demonstrate different thread pool types and ExecutorService usage.
 *
 * Types:
 * - FixedThreadPool: fixed number of threads
 * - CachedThreadPool: creates threads as needed, reuses idle ones
 * - SingleThreadExecutor: single worker thread
 * - ScheduledThreadPool: for delayed/periodic tasks
 */
public class Challenge03 {

    public static void main(String[] args) throws Exception {
        // Fixed Thread Pool
        System.out.println("=== Fixed Thread Pool (3 threads, 5 tasks) ===");
        ExecutorService fixed = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            fixed.submit(() -> {
                System.out.println("  Task " + taskId + " on " + Thread.currentThread().getName());
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
        }
        fixed.shutdown();
        fixed.awaitTermination(5, TimeUnit.SECONDS);

        // Callable with Future - get return values
        System.out.println("\n=== Callable with Future ===");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> f1 = executor.submit(() -> {
            Thread.sleep(100);
            return "Result from task 1";
        });
        Future<String> f2 = executor.submit(() -> {
            Thread.sleep(200);
            return "Result from task 2";
        });
        System.out.println("  " + f1.get());
        System.out.println("  " + f2.get());
        executor.shutdown();

        // invokeAll - wait for all tasks
        System.out.println("\n=== invokeAll ===");
        ExecutorService pool = Executors.newFixedThreadPool(3);
        var tasks = java.util.List.of(
            (Callable<Integer>) () -> { Thread.sleep(100); return 1; },
            (Callable<Integer>) () -> { Thread.sleep(200); return 2; },
            (Callable<Integer>) () -> { Thread.sleep(50); return 3; }
        );
        var results = pool.invokeAll(tasks);
        for (Future<Integer> r : results) {
            System.out.println("  Result: " + r.get());
        }
        pool.shutdown();

        // Scheduled executor
        System.out.println("\n=== Scheduled Executor ===");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> System.out.println("  Delayed task executed!"),
            500, TimeUnit.MILLISECONDS);
        scheduler.awaitTermination(1, TimeUnit.SECONDS);
        scheduler.shutdown();
    }
}
