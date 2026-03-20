package com.codefactor.challenge.one_week.day13;

/**
 * Challenge 09 - Longest Common Subsequence (LCS)
 *
 * Find the length of the longest subsequence common to two strings.
 * Classic dynamic programming problem.
 *
 * Complexity: O(m*n) time, O(m*n) space
 */
public class Challenge09 {

    /** DP approach with 2D table */
    public static int lcs(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /** Also reconstruct the LCS string */
    public static String lcsString(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // Backtrack to find the actual subsequence
        StringBuilder result = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                result.append(s1.charAt(i - 1));
                i--; j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) i--;
            else j--;
        }
        return result.reverse().toString();
    }

    /** Longest Common Substring (contiguous) */
    public static String longestCommonSubstring(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0, endIdx = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) { maxLen = dp[i][j]; endIdx = i; }
                }
            }
        }
        return s1.substring(endIdx - maxLen, endIdx);
    }

    public static void main(String[] args) {
        System.out.println("LCS length('ABCBDAB', 'BDCAB'): " + lcs("ABCBDAB", "BDCAB"));
        System.out.println("LCS string: " + lcsString("ABCBDAB", "BDCAB"));

        System.out.println("\nLongest Common Substring('OldSite:GeeksforGeeks.org', 'NewSite:GeeksQuiz.com'):");
        System.out.println("  " + longestCommonSubstring("OldSite:GeeksforGeeks.org", "NewSite:GeeksQuiz.com"));
    }
}
