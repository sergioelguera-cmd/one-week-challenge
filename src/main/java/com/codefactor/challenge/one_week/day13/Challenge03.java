package com.codefactor.challenge.one_week.day13;

import java.util.Arrays;

/**
 * Challenge 03 - Merge Sort and Quick Sort
 *
 * O(n log n) sorting algorithms - must know for interviews.
 *
 * Merge Sort: stable, O(n log n) always, O(n) extra space
 * Quick Sort: unstable, O(n log n) avg, O(n^2) worst, in-place
 */
public class Challenge03 {

    /** Merge Sort */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    /** Quick Sort */
    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int pivotIndex = partition(arr, low, high);
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] original = {38, 27, 43, 3, 9, 82, 10};

        int[] a = original.clone();
        mergeSort(a, 0, a.length - 1);
        System.out.println("Merge Sort: " + Arrays.toString(a));

        a = original.clone();
        quickSort(a, 0, a.length - 1);
        System.out.println("Quick Sort: " + Arrays.toString(a));
    }
}
