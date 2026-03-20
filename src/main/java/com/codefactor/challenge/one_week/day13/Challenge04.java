package com.codefactor.challenge.one_week.day13;

/**
 * Challenge 04 - Fibonacci Number
 *
 * Compute nth Fibonacci number using multiple approaches.
 *
 * Approaches:
 * 1. Recursive (naive) - O(2^n) exponential
 * 2. Memoized (top-down DP) - O(n)
 * 3. Iterative (bottom-up DP) - O(n) time, O(1) space
 */
public class Challenge04 {

    /** Approach 1: Naive recursion - O(2^n) */
    public static long fibRecursive(int n) {
        if (n <= 1) return n;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    /** Approach 2: Memoized - O(n) time, O(n) space */
    public static long fibMemoized(int n) {
        long[] memo = new long[n + 1];
        java.util.Arrays.fill(memo, -1);
        return fibHelper(n, memo);
    }

    private static long fibHelper(int n, long[] memo) {
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n];
        memo[n] = fibHelper(n - 1, memo) + fibHelper(n - 2, memo);
        return memo[n];
    }

    /** Approach 3: Iterative - O(n) time, O(1) space */
    public static long fibIterative(int n) {
        if (n <= 1) return n;
        long prev2 = 0, prev1 = 1;
        for (int i = 2; i <= n; i++) {
            long current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    public static void main(String[] args) {
        System.out.println("=== Fibonacci ===");
        for (int n : new int[]{0, 1, 5, 10, 20}) {
            System.out.printf("  fib(%d) = %d (iter), %d (memo)%n",
                n, fibIterative(n), fibMemoized(n));
        }

        // Performance comparison
        System.out.println("\n=== Performance (fib(40)) ===");
        long start = System.nanoTime();
        long result = fibIterative(40);
        long iterTime = System.nanoTime() - start;
        System.out.println("  Iterative: " + result + " (" + iterTime / 1000 + "us)");

        start = System.nanoTime();
        result = fibMemoized(40);
        long memoTime = System.nanoTime() - start;
        System.out.println("  Memoized:  " + result + " (" + memoTime / 1000 + "us)");

        start = System.nanoTime();
        result = fibRecursive(40);
        long recTime = System.nanoTime() - start;
        System.out.println("  Recursive: " + result + " (" + recTime / 1_000_000 + "ms)");
    }
}
