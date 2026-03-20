package com.codefactor.challenge.one_week.day12;

/**
 * Challenge 06 - Implement a HashMap from Scratch
 *
 * Build a simple HashMap using array of linked lists (chaining).
 *
 * Key concepts: hashing, collision handling, load factor, resizing
 */
public class Challenge06 {

    static class MyHashMap<K, V> {
        private static final int INITIAL_CAPACITY = 16;
        private static final double LOAD_FACTOR = 0.75;

        @SuppressWarnings("unchecked")
        private Node<K, V>[] buckets = new Node[INITIAL_CAPACITY];
        private int size;

        static class Node<K, V> {
            K key;
            V value;
            Node<K, V> next;
            Node(K key, V value) { this.key = key; this.value = value; }
        }

        private int getBucketIndex(K key) {
            return Math.abs(key.hashCode() % buckets.length);
        }

        public void put(K key, V value) {
            int index = getBucketIndex(key);
            Node<K, V> current = buckets[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // update
                    return;
                }
                current = current.next;
            }
            Node<K, V> newNode = new Node<>(key, value);
            newNode.next = buckets[index];
            buckets[index] = newNode;
            size++;

            if ((double) size / buckets.length > LOAD_FACTOR) resize();
        }

        public V get(K key) {
            int index = getBucketIndex(key);
            Node<K, V> current = buckets[index];
            while (current != null) {
                if (current.key.equals(key)) return current.value;
                current = current.next;
            }
            return null;
        }

        public boolean containsKey(K key) { return get(key) != null; }
        public int size() { return size; }

        @SuppressWarnings("unchecked")
        private void resize() {
            Node<K, V>[] oldBuckets = buckets;
            buckets = new Node[oldBuckets.length * 2];
            size = 0;
            for (Node<K, V> head : oldBuckets) {
                while (head != null) {
                    put(head.key, head.value);
                    head = head.next;
                }
            }
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Alice", 90);
        map.put("Bob", 85);
        map.put("Charlie", 92);
        map.put("Alice", 95); // update

        System.out.println("Alice: " + map.get("Alice"));
        System.out.println("Bob: " + map.get("Bob"));
        System.out.println("Dave: " + map.get("Dave"));
        System.out.println("Contains Bob: " + map.containsKey("Bob"));
        System.out.println("Size: " + map.size());
    }
}
