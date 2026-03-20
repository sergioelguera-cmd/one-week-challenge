package com.codefactor.challenge.one_week.day13;

import java.util.Arrays;

/**
 * Challenge 01 - Binary Search
 *
 * Search for a target in a sorted array.
 *
 * Approaches:
 * 1. Iterative
 * 2. Recursive
 *
 * Complexity: O(log n) time, O(1) space (iterative)
 */
public class Challenge01 {

    /** Iterative binary search */
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    /** Recursive binary search */
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) return binarySearchRecursive(arr, target, mid + 1, right);
        return binarySearchRecursive(arr, target, left, mid - 1);
    }

    /** Find first occurrence of target */
    public static int firstOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) { result = mid; right = mid - 1; }
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Search 7:  index " + binarySearch(arr, 7));
        System.out.println("Search 10: index " + binarySearch(arr, 10));
        System.out.println("Recursive 13: index " +
            binarySearchRecursive(arr, 13, 0, arr.length - 1));

        int[] dups = {1, 2, 2, 2, 3, 4};
        System.out.println("\nFirst occurrence of 2 in " + Arrays.toString(dups) +
            ": index " + firstOccurrence(dups, 2));
    }
}
