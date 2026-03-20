package com.codefactor.challenge.one_week.day6;

/**
 * Challenge 06 - Move All Zeros to End of Array
 *
 * Given an array of integers, move all zeros to the end while maintaining
 * the relative order of non-zero elements.
 *
 * Requirements:
 * - Must be done in-place without making a copy of the array
 * - Minimize the total number of operations
 *
 * Complexity: O(n) time, O(1) space
 *
 * Example:
 *   Input:  [0, 1, 0, 3, 12]
 *   Output: [1, 3, 12, 0, 0]
 */
public class Challenge06 {

    /** Approach 1: Two-pointer - write non-zeros then fill zeros */
    public static void moveZeros(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int writeIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[writeIndex++] = arr[i];
            }
        }
        while (writeIndex < arr.length) {
            arr[writeIndex++] = 0;
        }
    }

    /** Approach 2: Swap-based - swap non-zero with first zero position */
    public static void moveZerosSwap(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int slow = 0;
        for (int fast = 0; fast < arr.length; fast++) {
            if (arr[fast] != 0) {
                int temp = arr[slow];
                arr[slow] = arr[fast];
                arr[fast] = temp;
                slow++;
            }
        }
    }

    public static void main(String[] args) {
        int[][] tests = {
            {0, 1, 0, 3, 12},
            {0, 0, 0, 1},
            {1, 2, 3},
            {0},
            {1, 0, 0, 0, 2}
        };
        for (int[] test : tests) {
            int[] copy1 = test.clone();
            int[] copy2 = test.clone();
            System.out.print("Input:  ");
            printArray(test);

            moveZeros(copy1);
            System.out.print("Write:  ");
            printArray(copy1);

            moveZerosSwap(copy2);
            System.out.print("Swap:   ");
            printArray(copy2);
            System.out.println();
        }
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
