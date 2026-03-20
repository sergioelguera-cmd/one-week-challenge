package com.codefactor.challenge.one_week.daythree;

public class MainChallenge_Sol {


    public static int palindromeIndex(String s) {
        // Write your code here

            int l = 0, r = s.length() - 1;

            while (l < r && s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            }

            // already palindrome
            if (l >= r) return -1;

            // try skipping left or right
            if (isPalindrome(s, l + 1, r)) return l;
            if (isPalindrome(s, l, r - 1)) return r;

            return -1;
        }

        public static boolean isPalindrome(String s, int l, int r) {
            while (l < r) {
                if (s.charAt(l) != s.charAt(r)) return false;
                l++;
                r--;
            }
            return true;
        }


}
