package com.codefactor.challenge.one_week.day13;

/**
 * Challenge 08 - Recursion: Power, Permutations, Subsets
 *
 * Classic recursion problems commonly asked in interviews.
 */
public class Challenge08 {

    /** Power function O(log n) */
    public static double power(double base, int exp) {
        if (exp == 0) return 1;
        if (exp < 0) return 1.0 / power(base, -exp);
        double half = power(base, exp / 2);
        return exp % 2 == 0 ? half * half : half * half * base;
    }

    /** Generate all permutations of a string */
    public static java.util.List<String> permutations(String s) {
        java.util.List<String> result = new java.util.ArrayList<>();
        permute(s.toCharArray(), 0, result);
        return result;
    }

    private static void permute(char[] arr, int start, java.util.List<String> result) {
        if (start == arr.length) {
            result.add(new String(arr));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            permute(arr, start + 1, result);
            swap(arr, start, i);
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    /** Generate all subsets (power set) */
    public static java.util.List<java.util.List<Integer>> subsets(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        subsetsHelper(nums, 0, new java.util.ArrayList<>(), result);
        return result;
    }

    private static void subsetsHelper(int[] nums, int index,
            java.util.List<Integer> current,
            java.util.List<java.util.List<Integer>> result) {
        result.add(new java.util.ArrayList<>(current));
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            subsetsHelper(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("power(2, 10) = " + power(2, 10));
        System.out.println("power(2, -3) = " + power(2, -3));

        System.out.println("\nPermutations of 'abc': " + permutations("abc"));

        int[] nums = {1, 2, 3};
        System.out.println("\nSubsets of [1,2,3]: " + subsets(nums));
    }
}
