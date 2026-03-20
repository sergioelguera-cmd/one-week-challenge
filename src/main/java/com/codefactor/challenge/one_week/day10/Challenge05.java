package com.codefactor.challenge.one_week.day10;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Challenge 05 - CompletableFuture
 *
 * Demonstrate asynchronous programming with CompletableFuture.
 *
 * Key methods:
 * - supplyAsync / runAsync
 * - thenApply / thenAccept / thenRun
 * - thenCompose / thenCombine
 * - exceptionally / handle
 * - allOf / anyOf
 */
public class Challenge05 {

    static String fetchUserName(int userId) {
        sleep(200);
        return "User_" + userId;
    }

    static double fetchUserRating(String userName) {
        sleep(150);
        return 4.5;
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Basic async operation
        System.out.println("=== supplyAsync + thenApply ===");
        CompletableFuture<String> future = CompletableFuture
            .supplyAsync(() -> fetchUserName(1))
            .thenApply(name -> "Hello, " + name + "!");

        System.out.println("  " + future.get());

        // Chaining: thenCompose (flatMap for futures)
        System.out.println("\n=== thenCompose (chained async) ===");
        CompletableFuture<Double> rating = CompletableFuture
            .supplyAsync(() -> fetchUserName(2))
            .thenCompose(name -> CompletableFuture.supplyAsync(() -> fetchUserRating(name)));
        System.out.println("  Rating: " + rating.get());

        // Combining two independent futures
        System.out.println("\n=== thenCombine (parallel) ===");
        CompletableFuture<String> nameF = CompletableFuture.supplyAsync(() -> fetchUserName(3));
        CompletableFuture<Double> ratingF = CompletableFuture.supplyAsync(() -> fetchUserRating("User_3"));
        CompletableFuture<String> combined = nameF.thenCombine(ratingF,
            (name, r) -> name + " has rating " + r);
        System.out.println("  " + combined.get());

        // Error handling
        System.out.println("\n=== exceptionally ===");
        CompletableFuture<String> withError = CompletableFuture
            .supplyAsync(() -> {
                if (true) throw new RuntimeException("Something went wrong");
                return "OK";
            })
            .exceptionally(ex -> "Recovered: " + ex.getMessage());
        System.out.println("  " + withError.get());

        // allOf - wait for all
        System.out.println("\n=== allOf ===");
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> { sleep(100); return "A"; });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> { sleep(200); return "B"; });
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> { sleep(50); return "C"; });
        CompletableFuture.allOf(f1, f2, f3).join();
        System.out.println("  All done: " + f1.get() + ", " + f2.get() + ", " + f3.get());
    }
}
