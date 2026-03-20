package com.codefactor.challenge.one_week.day7;

import java.util.*;

/**
 * Challenge 07 - Implement a Stack Using Two Queues
 *
 * Implement a Last-In-First-Out (LIFO) stack using only two queues.
 * Support push, pop, top, and isEmpty operations.
 *
 * Complexity: Push O(n), Pop O(1) (or vice versa depending on approach)
 */
public class Challenge07 {

    /** Stack using two queues - costly push */
    static class StackUsingQueues<T> {
        private Queue<T> q1 = new LinkedList<>();
        private Queue<T> q2 = new LinkedList<>();

        public void push(T item) {
            q2.offer(item);
            while (!q1.isEmpty()) {
                q2.offer(q1.poll());
            }
            Queue<T> temp = q1;
            q1 = q2;
            q2 = temp;
        }

        public T pop() {
            if (q1.isEmpty()) throw new EmptyStackException();
            return q1.poll();
        }

        public T top() {
            if (q1.isEmpty()) throw new EmptyStackException();
            return q1.peek();
        }

        public boolean isEmpty() {
            return q1.isEmpty();
        }
    }

    /** Queue using two stacks - for completeness */
    static class QueueUsingStacks<T> {
        private final Deque<T> inbox = new ArrayDeque<>();
        private final Deque<T> outbox = new ArrayDeque<>();

        public void enqueue(T item) {
            inbox.push(item);
        }

        public T dequeue() {
            if (outbox.isEmpty()) {
                while (!inbox.isEmpty()) {
                    outbox.push(inbox.pop());
                }
            }
            if (outbox.isEmpty()) throw new NoSuchElementException();
            return outbox.pop();
        }

        public boolean isEmpty() {
            return inbox.isEmpty() && outbox.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Stack using Queues ===");
        StackUsingQueues<Integer> stack = new StackUsingQueues<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Top: " + stack.top());     // 3
        System.out.println("Pop: " + stack.pop());      // 3
        System.out.println("Pop: " + stack.pop());      // 2
        System.out.println("Empty: " + stack.isEmpty()); // false

        System.out.println("\n=== Queue using Stacks ===");
        QueueUsingStacks<Integer> queue = new QueueUsingStacks<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("Dequeue: " + queue.dequeue()); // 1
        System.out.println("Dequeue: " + queue.dequeue()); // 2
        System.out.println("Empty: " + queue.isEmpty());    // false
    }
}
