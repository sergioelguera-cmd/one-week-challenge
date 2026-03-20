package com.codefactor.challenge.one_week.day13;

import java.util.Arrays;

/**
 * Challenge 02 - Sorting Algorithms: Bubble, Selection, Insertion
 *
 * Implement the three basic O(n^2) sorting algorithms.
 * Know their differences for interviews.
 *
 * Bubble: swap adjacent, stable, O(n^2)
 * Selection: find min, put in place, unstable, O(n^2)
 * Insertion: insert into sorted portion, stable, O(n^2) avg, O(n) best
 */
public class Challenge02 {

    /** Bubble Sort */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // optimization: already sorted
        }
    }

    /** Selection Sort */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[i]; arr[i] = arr[minIdx]; arr[minIdx] = temp;
        }
    }

    /** Insertion Sort */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] original = {64, 34, 25, 12, 22, 11, 90};

        int[] a = original.clone();
        bubbleSort(a);
        System.out.println("Bubble:    " + Arrays.toString(a));

        a = original.clone();
        selectionSort(a);
        System.out.println("Selection: " + Arrays.toString(a));

        a = original.clone();
        insertionSort(a);
        System.out.println("Insertion: " + Arrays.toString(a));
    }
}
