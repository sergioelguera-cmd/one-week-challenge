package com.codefactor.challenge.one_week.day12;

/**
 * Challenge 01 - Implement a Singly Linked List
 *
 * Build a linked list from scratch with operations:
 * - addFirst, addLast, removeFirst, removeLast
 * - get, size, contains, reverse
 *
 * Complexity: addFirst O(1), addLast O(n), get O(n)
 */
public class Challenge01 {

    static class LinkedList<T> {
        private Node<T> head;
        private int size;

        static class Node<T> {
            T data;
            Node<T> next;
            Node(T data) { this.data = data; }
        }

        public void addFirst(T data) {
            Node<T> newNode = new Node<>(data);
            newNode.next = head;
            head = newNode;
            size++;
        }

        public void addLast(T data) {
            Node<T> newNode = new Node<>(data);
            if (head == null) { head = newNode; }
            else {
                Node<T> current = head;
                while (current.next != null) current = current.next;
                current.next = newNode;
            }
            size++;
        }

        public T removeFirst() {
            if (head == null) throw new java.util.NoSuchElementException();
            T data = head.data;
            head = head.next;
            size--;
            return data;
        }

        public T get(int index) {
            if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
            Node<T> current = head;
            for (int i = 0; i < index; i++) current = current.next;
            return current.data;
        }

        public boolean contains(T data) {
            Node<T> current = head;
            while (current != null) {
                if (current.data.equals(data)) return true;
                current = current.next;
            }
            return false;
        }

        public void reverse() {
            Node<T> prev = null, current = head;
            while (current != null) {
                Node<T> next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            head = prev;
        }

        public int size() { return size; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            Node<T> current = head;
            while (current != null) {
                sb.append(current.data);
                if (current.next != null) sb.append(" -> ");
                current = current.next;
            }
            return sb.append("]").toString();
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addFirst(0);
        System.out.println("List: " + list);
        System.out.println("Size: " + list.size());
        System.out.println("Get(2): " + list.get(2));
        System.out.println("Contains 2: " + list.contains(2));

        list.reverse();
        System.out.println("Reversed: " + list);

        System.out.println("RemoveFirst: " + list.removeFirst());
        System.out.println("After remove: " + list);
    }
}
