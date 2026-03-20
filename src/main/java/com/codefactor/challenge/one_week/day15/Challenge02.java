package com.codefactor.challenge.one_week.day15;

import java.util.*;

/**
 * Challenge 02 - Generics: Classes, Methods, and Bounded Types
 *
 * Demonstrate generic programming in Java.
 *
 * Key concepts:
 * - Generic class, method, interface
 * - Bounded type parameters (extends, super)
 * - Wildcards (?, ? extends T, ? super T)
 * - Type erasure
 */
public class Challenge02 {

    /** Generic class: Pair<K,V> */
    static class Pair<K, V> {
        private final K key;
        private final V value;

        Pair(K key, V value) { this.key = key; this.value = value; }

        public K getKey() { return key; }
        public V getValue() { return value; }

        @Override
        public String toString() { return "(" + key + ", " + value + ")"; }
    }

    /** Bounded type: only accepts Comparable types */
    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not be empty");
        }
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) max = item;
        }
        return max;
    }

    /** Multiple bounds */
    public static <T extends Comparable<T> & java.io.Serializable> void process(T item) {
        System.out.println("  Processing: " + item);
    }

    /** Wildcards demo */
    // ? extends Number - read-only (producer)
    public static double sumOfNumbers(List<? extends Number> numbers) {
        double sum = 0;
        for (Number n : numbers) sum += n.doubleValue();
        return sum;
    }

    // ? super Integer - write-only (consumer)
    public static void addIntegers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /** Generic method */
    public static <T> List<T> arrayToList(T[] array) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, array);
        return list;
    }

    public static void main(String[] args) {
        // Generic class
        Pair<String, Integer> pair = new Pair<>("Age", 30);
        System.out.println("Pair: " + pair);

        // Bounded generic method
        List<Integer> ints = List.of(3, 1, 4, 1, 5, 9);
        System.out.println("Max int: " + findMax(ints));

        List<String> strings = List.of("banana", "apple", "cherry");
        System.out.println("Max string: " + findMax(strings));

        // Wildcards
        System.out.println("\n=== Wildcards ===");
        List<Integer> intList = List.of(1, 2, 3);
        List<Double> doubleList = List.of(1.5, 2.5, 3.5);
        System.out.println("Sum ints: " + sumOfNumbers(intList));
        System.out.println("Sum doubles: " + sumOfNumbers(doubleList));

        // ? super Integer
        List<Number> numberList = new ArrayList<>();
        addIntegers(numberList);
        System.out.println("After addIntegers: " + numberList);

        // Generic method
        String[] arr = {"X", "Y", "Z"};
        List<String> fromArray = arrayToList(arr);
        System.out.println("Array to list: " + fromArray);
    }
}
