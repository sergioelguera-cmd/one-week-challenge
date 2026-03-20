package com.codefactor.challenge.one_week.day12;

/**
 * Challenge 09 - Balanced Parentheses using Stack
 *
 * Check if a string of brackets is balanced.
 * Classic stack interview problem.
 *
 * Supports: (), {}, []
 * Complexity: O(n) time, O(n) space
 */
public class Challenge09 {

    /** Check if brackets are balanced */
    public static boolean isBalanced(String s) {
        java.util.Deque<Character> stack = new java.util.ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if (!matches(open, c)) return false;
            }
        }
        return stack.isEmpty();
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }

    /** Find the index of the first unmatched bracket */
    public static int findUnmatched(String s) {
        java.util.Deque<int[]> stack = new java.util.ArrayDeque<>(); // [char, index]
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(new int[]{c, i});
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty() || !matches((char) stack.peek()[0], c)) {
                    return i;
                }
                stack.pop();
            }
        }
        return stack.isEmpty() ? -1 : stack.peek()[1];
    }

    /** Generate all valid parentheses combinations */
    public static java.util.List<String> generateParentheses(int n) {
        java.util.List<String> result = new java.util.ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    private static void generate(java.util.List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }
        if (open < max) generate(result, current + "(", open + 1, close, max);
        if (close < open) generate(result, current + ")", open, close + 1, max);
    }

    public static void main(String[] args) {
        String[] tests = {"()", "()[]{}", "(]", "([)]", "{[]}", "(()", ""};
        for (String test : tests) {
            System.out.printf("  %-10s balanced: %s%n", "\"" + test + "\"", isBalanced(test));
        }

        System.out.println("\nUnmatched index in '(()': " + findUnmatched("(()"));
        System.out.println("Unmatched index in '())': " + findUnmatched("())"));

        System.out.println("\nGenerate valid parentheses (n=3):");
        generateParentheses(3).forEach(s -> System.out.println("  " + s));
    }
}
