package com.codefactor.challenge.one_week.day12;

import java.util.*;

/**
 * Challenge 05 - Min Heap / Priority Queue Implementation
 *
 * Implement a Min Heap from scratch and compare with Java's PriorityQueue.
 *
 * Operations: insert O(log n), extractMin O(log n), peek O(1)
 */
public class Challenge05 {

    static class MinHeap {
        private final List<Integer> heap = new ArrayList<>();

        public void insert(int value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        public int extractMin() {
            if (heap.isEmpty()) throw new NoSuchElementException();
            int min = heap.get(0);
            int last = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, last);
                siftDown(0);
            }
            return min;
        }

        public int peek() {
            if (heap.isEmpty()) throw new NoSuchElementException();
            return heap.get(0);
        }

        public int size() { return heap.size(); }
        public boolean isEmpty() { return heap.isEmpty(); }

        private void siftUp(int i) {
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (heap.get(i) >= heap.get(parent)) break;
                swap(i, parent);
                i = parent;
            }
        }

        private void siftDown(int i) {
            int n = heap.size();
            while (2 * i + 1 < n) {
                int child = 2 * i + 1;
                if (child + 1 < n && heap.get(child + 1) < heap.get(child)) child++;
                if (heap.get(i) <= heap.get(child)) break;
                swap(i, child);
                i = child;
            }
        }

        private void swap(int i, int j) {
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Custom MinHeap ===");
        MinHeap heap = new MinHeap();
        int[] values = {5, 3, 8, 1, 4, 2, 7};
        for (int v : values) heap.insert(v);

        System.out.print("Extract in order: ");
        while (!heap.isEmpty()) {
            System.out.print(heap.extractMin() + " ");
        }
        System.out.println();

        System.out.println("\n=== Java PriorityQueue ===");
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int v : values) pq.offer(v);
        System.out.print("Poll in order: ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();

        // Max heap using PriorityQueue
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        for (int v : values) maxPQ.offer(v);
        System.out.print("Max heap poll: ");
        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.poll() + " ");
        }
        System.out.println();
    }
}
