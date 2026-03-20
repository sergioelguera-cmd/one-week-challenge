package com.codefactor.challenge.one_week.day12;

import java.util.*;

/**
 * Challenge 10 - Detect Cycle in Linked List (Floyd's Algorithm)
 *
 * Use Floyd's Tortoise and Hare algorithm to detect a cycle.
 * Also find the start of the cycle.
 *
 * Complexity: O(n) time, O(1) space
 */
public class Challenge10 {

    static class Node {
        int value;
        Node next;
        Node(int value) { this.value = value; }
    }

    /** Detect if cycle exists - Floyd's algorithm */
    public static boolean hasCycle(Node head) {
        if (head == null) return false;
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /** Find the start node of the cycle */
    public static Node findCycleStart(Node head) {
        if (head == null) return null;
        Node slow = head, fast = head;

        // Phase 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null; // no cycle

        // Phase 2: Find start
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /** Find cycle length */
    public static int cycleLength(Node head) {
        Node start = findCycleStart(head);
        if (start == null) return 0;
        int length = 1;
        Node current = start.next;
        while (current != start) {
            length++;
            current = current.next;
        }
        return length;
    }

    /** Alternative: HashSet-based detection O(n) space */
    public static boolean hasCycleHashSet(Node head) {
        Set<Node> visited = new HashSet<>();
        Node current = head;
        while (current != null) {
            if (!visited.add(current)) return true;
            current = current.next;
        }
        return false;
    }

    public static void main(String[] args) {
        // Create list without cycle
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        System.out.println("List 1 has cycle: " + hasCycle(head1));

        // Create list with cycle: 1->2->3->4->2
        Node head2 = new Node(1);
        Node n2 = new Node(2);
        head2.next = n2;
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = n2; // creates cycle

        System.out.println("List 2 has cycle: " + hasCycle(head2));
        System.out.println("List 2 has cycle (HashSet): " + hasCycleHashSet(head2));

        Node cycleStart = findCycleStart(head2);
        System.out.println("Cycle starts at value: " + (cycleStart != null ? cycleStart.value : "none"));
        System.out.println("Cycle length: " + cycleLength(head2));
    }
}
