package com.codefactor.challenge.one_week.day13;

/**
 * Challenge 05 - Dynamic Programming: Climbing Stairs
 *
 * You can climb 1 or 2 steps at a time.
 * How many distinct ways to reach the top of n stairs?
 *
 * Classic DP problem. Same pattern as Fibonacci.
 * dp[i] = dp[i-1] + dp[i-2]
 *
 * Complexity: O(n) time, O(1) space
 */
public class Challenge05 {

    /** Bottom-up DP */
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    /** With k steps allowed (generalized) */
    public static int climbStairsK(int n, int k) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }

    /** Minimum cost climbing stairs */
    public static int minCostClimbing(int[] cost) {
        int n = cost.length;
        int prev2 = cost[0], prev1 = cost[1];
        for (int i = 2; i < n; i++) {
            int current = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = current;
        }
        return Math.min(prev1, prev2);
    }

    public static void main(String[] args) {
        for (int n = 1; n <= 10; n++) {
            System.out.println("  stairs(" + n + ") = " + climbStairs(n) + " ways");
        }

        System.out.println("\nWith up to 3 steps:");
        for (int n = 1; n <= 5; n++) {
            System.out.println("  stairs(" + n + ", k=3) = " + climbStairsK(n, 3) + " ways");
        }

        int[] cost = {10, 15, 20};
        System.out.println("\nMin cost for " + java.util.Arrays.toString(cost) +
            ": " + minCostClimbing(cost));
    }
}
