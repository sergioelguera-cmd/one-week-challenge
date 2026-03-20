package com.codefactor.challenge.one_week.day12;

/**
 * Challenge 02 - Implement a Stack
 *
 * Implement a Stack using array and linked list.
 *
 * Operations: push, pop, peek, isEmpty, size
 * All operations O(1)
 */
public class Challenge02 {

    /** Array-based Stack */
    static class ArrayStack<T> {
        private Object[] data;
        private int top = -1;

        @SuppressWarnings("unchecked")
        ArrayStack(int capacity) { data = new Object[capacity]; }

        public void push(T item) {
            if (top == data.length - 1) throw new StackOverflowError("Stack full");
            data[++top] = item;
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (isEmpty()) throw new java.util.EmptyStackException();
            T item = (T) data[top];
            data[top--] = null;
            return item;
        }

        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) throw new java.util.EmptyStackException();
            return (T) data[top];
        }

        public boolean isEmpty() { return top == -1; }
        public int size() { return top + 1; }
    }

    /** Linked list-based Stack (no capacity limit) */
    static class LinkedStack<T> {
        private Node<T> top;
        private int size;

        static class Node<T> {
            T data;
            Node<T> next;
            Node(T data, Node<T> next) { this.data = data; this.next = next; }
        }

        public void push(T item) {
            top = new Node<>(item, top);
            size++;
        }

        public T pop() {
            if (isEmpty()) throw new java.util.EmptyStackException();
            T data = top.data;
            top = top.next;
            size--;
            return data;
        }

        public T peek() {
            if (isEmpty()) throw new java.util.EmptyStackException();
            return top.data;
        }

        public boolean isEmpty() { return top == null; }
        public int size() { return size; }
    }

    public static void main(String[] args) {
        System.out.println("=== Array Stack ===");
        ArrayStack<Integer> aStack = new ArrayStack<>(5);
        aStack.push(10); aStack.push(20); aStack.push(30);
        System.out.println("Peek: " + aStack.peek());
        System.out.println("Pop: " + aStack.pop());
        System.out.println("Size: " + aStack.size());

        System.out.println("\n=== Linked Stack ===");
        LinkedStack<String> lStack = new LinkedStack<>();
        lStack.push("A"); lStack.push("B"); lStack.push("C");
        System.out.println("Peek: " + lStack.peek());
        System.out.println("Pop: " + lStack.pop());
        System.out.println("Size: " + lStack.size());
    }
}
