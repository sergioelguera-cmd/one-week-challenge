package com.codefactor.challenge.one_week.day7;

import java.util.*;

/**
 * Challenge 03 - Group Anagrams Together
 *
 * Given a list of strings, group anagrams together.
 *
 * Approach: Sort each string to create a key, group by that key using HashMap.
 *
 * Complexity: O(n * k log k) where n = number of strings, k = max string length
 *
 * Example:
 *   Input:  ["eat", "tea", "tan", "ate", "nat", "bat"]
 *   Output: [["eat","tea","ate"], ["tan","nat"], ["bat"]]
 */
public class Challenge03 {

    /** Group anagrams using sorted key */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    /** Alternative: frequency-based key (avoids sorting) */
    public static List<List<String>> groupAnagramsFreq(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] freq = new int[26];
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }
            String key = Arrays.toString(freq);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Sorted key:    " + groupAnagrams(input));
        System.out.println("Frequency key: " + groupAnagramsFreq(input));
    }
}
