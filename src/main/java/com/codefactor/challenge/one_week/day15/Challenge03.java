package com.codefactor.challenge.one_week.day15;

import java.util.*;

/**
 * Challenge 03 - Generic Data Structures
 *
 * Implement generic Stack and a type-safe container.
 *
 * Demonstrates: type parameters in classes, methods, and constructors.
 */
public class Challenge03 {

    /** Generic Stack */
    static class GenericStack<T> {
        private final List<T> elements = new ArrayList<>();

        public void push(T item) { elements.add(item); }

        public T pop() {
            if (isEmpty()) throw new EmptyStackException();
            return elements.remove(elements.size() - 1);
        }

        public T peek() {
            if (isEmpty()) throw new EmptyStackException();
            return elements.get(elements.size() - 1);
        }

        public boolean isEmpty() { return elements.isEmpty(); }
        public int size() { return elements.size(); }

        @Override
        public String toString() { return elements.toString(); }
    }

    /** Type-safe heterogeneous container (Effective Java pattern) */
    static class TypeSafeMap {
        private final Map<Class<?>, Object> map = new HashMap<>();

        public <T> void put(Class<T> type, T value) {
            map.put(type, value);
        }

        public <T> T get(Class<T> type) {
            return type.cast(map.get(type));
        }
    }

    /** Generic method: swap elements */
    public static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /** Generic method: count occurrences */
    public static <T> long count(List<T> list, T target) {
        return list.stream().filter(item -> Objects.equals(item, target)).count();
    }

    public static void main(String[] args) {
        // Generic Stack
        System.out.println("=== Generic Stack<String> ===");
        GenericStack<String> strStack = new GenericStack<>();
        strStack.push("Hello");
        strStack.push("World");
        System.out.println("Stack: " + strStack);
        System.out.println("Pop: " + strStack.pop());

        GenericStack<Integer> intStack = new GenericStack<>();
        intStack.push(10);
        intStack.push(20);
        System.out.println("\n=== Generic Stack<Integer> ===");
        System.out.println("Stack: " + intStack);

        // Type-safe container
        System.out.println("\n=== Type-Safe Map ===");
        TypeSafeMap tsMap = new TypeSafeMap();
        tsMap.put(String.class, "Hello");
        tsMap.put(Integer.class, 42);
        tsMap.put(Double.class, 3.14);
        System.out.println("String: " + tsMap.get(String.class));
        System.out.println("Integer: " + tsMap.get(Integer.class));
        System.out.println("Double: " + tsMap.get(Double.class));

        // Generic utility methods
        System.out.println("\n=== Generic Methods ===");
        List<String> words = new ArrayList<>(List.of("A", "B", "C"));
        swap(words, 0, 2);
        System.out.println("After swap: " + words);

        List<Integer> nums = List.of(1, 2, 3, 2, 4, 2);
        System.out.println("Count of 2: " + count(nums, 2));
    }
}
