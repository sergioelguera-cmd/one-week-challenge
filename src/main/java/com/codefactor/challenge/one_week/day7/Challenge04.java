package com.codefactor.challenge.one_week.day7;

import java.util.*;

/**
 * Challenge 04 - Implement LRU Cache using LinkedHashMap
 *
 * Design a data structure that follows Least Recently Used (LRU) cache constraints.
 * - get(key): Return the value if key exists, otherwise -1
 * - put(key, value): Insert or update. Evict least recently used if at capacity.
 *
 * Complexity: O(1) for both get and put
 *
 * Example:
 *   LRUCache cache = new LRUCache(2);
 *   cache.put(1, 1); cache.put(2, 2);
 *   cache.get(1);    // returns 1
 *   cache.put(3, 3); // evicts key 2
 */
public class Challenge04 {

    static class LRUCache extends LinkedHashMap<Integer, Integer> {
        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true); // accessOrder = true
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    /** Manual implementation without extending LinkedHashMap */
    static class LRUCacheManual {
        private final int capacity;
        private final Map<Integer, Node> map;
        private final Node head, tail;

        static class Node {
            int key, value;
            Node prev, next;
            Node(int k, int v) { key = k; value = v; }
        }

        public LRUCacheManual(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            remove(node);
            insertAtEnd(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                remove(map.get(key));
            }
            Node node = new Node(key, value);
            insertAtEnd(node);
            map.put(key, node);
            if (map.size() > capacity) {
                Node lru = head.next;
                remove(lru);
                map.remove(lru.key);
            }
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void insertAtEnd(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== LinkedHashMap LRU ===");
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("get(1): " + cache.get(1));   // 1
        cache.put(3, 3);                                   // evicts 2
        System.out.println("get(2): " + cache.get(2));   // -1
        System.out.println("get(3): " + cache.get(3));   // 3

        System.out.println("\n=== Manual Doubly-Linked List LRU ===");
        LRUCacheManual manual = new LRUCacheManual(2);
        manual.put(1, 1);
        manual.put(2, 2);
        System.out.println("get(1): " + manual.get(1));  // 1
        manual.put(3, 3);                                  // evicts 2
        System.out.println("get(2): " + manual.get(2));  // -1
        System.out.println("get(3): " + manual.get(3));  // 3
    }
}
