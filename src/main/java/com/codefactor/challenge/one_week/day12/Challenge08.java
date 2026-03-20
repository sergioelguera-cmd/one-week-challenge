package com.codefactor.challenge.one_week.day12;

/**
 * Challenge 08 - Trie (Prefix Tree)
 *
 * Implement a Trie for efficient string prefix matching.
 * Used in: autocomplete, spell check, IP routing.
 *
 * Operations: insert O(m), search O(m), startsWith O(m) where m = word length
 */
public class Challenge08 {

    static class Trie {
        private final TrieNode root = new TrieNode();

        static class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isEndOfWord;
        }

        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toLowerCase().toCharArray()) {
                int idx = c - 'a';
                if (current.children[idx] == null) {
                    current.children[idx] = new TrieNode();
                }
                current = current.children[idx];
            }
            current.isEndOfWord = true;
        }

        public boolean search(String word) {
            TrieNode node = findNode(word);
            return node != null && node.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            return findNode(prefix) != null;
        }

        private TrieNode findNode(String prefix) {
            TrieNode current = root;
            for (char c : prefix.toLowerCase().toCharArray()) {
                int idx = c - 'a';
                if (current.children[idx] == null) return null;
                current = current.children[idx];
            }
            return current;
        }

        /** Find all words with given prefix */
        public java.util.List<String> autocomplete(String prefix) {
            java.util.List<String> results = new java.util.ArrayList<>();
            TrieNode node = findNode(prefix);
            if (node != null) {
                collectWords(node, new StringBuilder(prefix.toLowerCase()), results);
            }
            return results;
        }

        private void collectWords(TrieNode node, StringBuilder current, java.util.List<String> results) {
            if (node.isEndOfWord) results.add(current.toString());
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    current.append((char) ('a' + i));
                    collectWords(node.children[i], current, results);
                    current.deleteCharAt(current.length() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] words = {"apple", "app", "application", "apt", "bat", "batch", "bath"};
        for (String w : words) trie.insert(w);

        System.out.println("search 'app': " + trie.search("app"));
        System.out.println("search 'appl': " + trie.search("appl"));
        System.out.println("startsWith 'app': " + trie.startsWith("app"));
        System.out.println("startsWith 'xyz': " + trie.startsWith("xyz"));
        System.out.println("autocomplete 'app': " + trie.autocomplete("app"));
        System.out.println("autocomplete 'bat': " + trie.autocomplete("bat"));
    }
}
