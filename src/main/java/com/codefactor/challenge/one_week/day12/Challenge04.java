package com.codefactor.challenge.one_week.day12;

/**
 * Challenge 04 - Binary Search Tree (BST)
 *
 * Implement a BST with insert, search, delete, and traversals.
 *
 * Traversals: inorder, preorder, postorder
 * Average: O(log n), Worst: O(n) for skewed tree
 */
public class Challenge04 {

    static class BST {
        Node root;

        static class Node {
            int value;
            Node left, right;
            Node(int value) { this.value = value; }
        }

        public void insert(int value) { root = insertRec(root, value); }

        private Node insertRec(Node node, int value) {
            if (node == null) return new Node(value);
            if (value < node.value) node.left = insertRec(node.left, value);
            else if (value > node.value) node.right = insertRec(node.right, value);
            return node;
        }

        public boolean search(int value) { return searchRec(root, value); }

        private boolean searchRec(Node node, int value) {
            if (node == null) return false;
            if (value == node.value) return true;
            return value < node.value ? searchRec(node.left, value) : searchRec(node.right, value);
        }

        public void inorder() { inorderRec(root); System.out.println(); }
        private void inorderRec(Node node) {
            if (node == null) return;
            inorderRec(node.left);
            System.out.print(node.value + " ");
            inorderRec(node.right);
        }

        public void preorder() { preorderRec(root); System.out.println(); }
        private void preorderRec(Node node) {
            if (node == null) return;
            System.out.print(node.value + " ");
            preorderRec(node.left);
            preorderRec(node.right);
        }

        public int findMin() {
            if (root == null) throw new java.util.NoSuchElementException();
            Node current = root;
            while (current.left != null) current = current.left;
            return current.value;
        }

        public int findMax() {
            if (root == null) throw new java.util.NoSuchElementException();
            Node current = root;
            while (current.right != null) current = current.right;
            return current.value;
        }

        public int height() { return heightRec(root); }
        private int heightRec(Node node) {
            if (node == null) return -1;
            return 1 + Math.max(heightRec(node.left), heightRec(node.right));
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) tree.insert(v);

        System.out.print("Inorder:  "); tree.inorder();
        System.out.print("Preorder: "); tree.preorder();
        System.out.println("Search 40: " + tree.search(40));
        System.out.println("Search 25: " + tree.search(25));
        System.out.println("Min: " + tree.findMin());
        System.out.println("Max: " + tree.findMax());
        System.out.println("Height: " + tree.height());
    }
}
