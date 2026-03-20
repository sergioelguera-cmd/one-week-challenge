package com.codefactor.challenge.one_week.day12;

/**
 * Challenge 03 - Implement a Queue
 *
 * Implement a Queue using array (circular buffer) and linked list.
 *
 * Operations: enqueue, dequeue, peek, isEmpty, size
 * All operations O(1)
 */
public class Challenge03 {

    /** Circular array-based Queue */
    static class ArrayQueue<T> {
        private final Object[] data;
        private int front, rear, size;

        ArrayQueue(int capacity) { data = new Object[capacity]; }

        public void enqueue(T item) {
            if (size == data.length) throw new IllegalStateException("Queue full");
            data[rear] = item;
            rear = (rear + 1) % data.length;
            size++;
        }

        @SuppressWarnings("unchecked")
        public T dequeue() {
            if (isEmpty()) throw new java.util.NoSuchElementException();
            T item = (T) data[front];
            data[front] = null;
            front = (front + 1) % data.length;
            size--;
            return item;
        }

        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) throw new java.util.NoSuchElementException();
            return (T) data[front];
        }

        public boolean isEmpty() { return size == 0; }
        public int size() { return size; }
    }

    /** Linked list-based Queue */
    static class LinkedQueue<T> {
        private Node<T> head, tail;
        private int size;

        static class Node<T> {
            T data;
            Node<T> next;
            Node(T data) { this.data = data; }
        }

        public void enqueue(T item) {
            Node<T> newNode = new Node<>(item);
            if (tail != null) tail.next = newNode;
            tail = newNode;
            if (head == null) head = newNode;
            size++;
        }

        public T dequeue() {
            if (isEmpty()) throw new java.util.NoSuchElementException();
            T data = head.data;
            head = head.next;
            if (head == null) tail = null;
            size--;
            return data;
        }

        public T peek() {
            if (isEmpty()) throw new java.util.NoSuchElementException();
            return head.data;
        }

        public boolean isEmpty() { return head == null; }
        public int size() { return size; }
    }

    public static void main(String[] args) {
        System.out.println("=== Array Queue (Circular) ===");
        ArrayQueue<Integer> aq = new ArrayQueue<>(5);
        aq.enqueue(1); aq.enqueue(2); aq.enqueue(3);
        System.out.println("Peek: " + aq.peek());
        System.out.println("Dequeue: " + aq.dequeue());
        aq.enqueue(4);
        System.out.println("Size: " + aq.size());

        System.out.println("\n=== Linked Queue ===");
        LinkedQueue<String> lq = new LinkedQueue<>();
        lq.enqueue("X"); lq.enqueue("Y"); lq.enqueue("Z");
        System.out.println("Peek: " + lq.peek());
        System.out.println("Dequeue: " + lq.dequeue());
        System.out.println("Size: " + lq.size());
    }
}
