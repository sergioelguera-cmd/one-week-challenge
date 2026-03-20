package com.codefactor.challenge.one_week.day13;

/**
 * Challenge 06 - Reverse a Linked List
 *
 * Reverse a singly linked list iteratively and recursively.
 *
 * Iterative: O(n) time, O(1) space
 * Recursive: O(n) time, O(n) space (call stack)
 */
public class Challenge06 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    /** Iterative reversal */
    public static ListNode reverseIterative(ListNode head) {
        ListNode prev = null, current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    /** Recursive reversal */
    public static ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /** Find middle of linked list (slow/fast pointer) */
    public static ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /** Check if linked list is palindrome */
    public static boolean isPalindrome(ListNode head) {
        ListNode mid = findMiddle(head);
        ListNode secondHalf = reverseIterative(mid);
        ListNode p1 = head, p2 = secondHalf;
        while (p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    static ListNode createList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int v : values) { current.next = new ListNode(v); current = current.next; }
        return dummy.next;
    }

    static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        ListNode list = createList(1, 2, 3, 4, 5);
        System.out.println("Original:  " + listToString(list));
        list = reverseIterative(list);
        System.out.println("Reversed:  " + listToString(list));
        list = reverseRecursive(list);
        System.out.println("Re-reverse:" + listToString(list));

        ListNode mid = findMiddle(createList(1, 2, 3, 4, 5));
        System.out.println("Middle: " + mid.val);

        System.out.println("Palindrome [1,2,1]: " + isPalindrome(createList(1, 2, 1)));
        System.out.println("Palindrome [1,2,3]: " + isPalindrome(createList(1, 2, 3)));
    }
}
