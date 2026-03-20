package com.codefactor.challenge.one_week.day13;

/**
 * Challenge 07 - Check if Number is Prime / Sieve of Eratosthenes
 *
 * 1. Check primality O(sqrt(n))
 * 2. Generate all primes up to N using Sieve O(n log log n)
 * 3. Find GCD using Euclidean algorithm
 */
public class Challenge07 {

    /** Check if n is prime - O(sqrt(n)) */
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n < 4) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    /** Sieve of Eratosthenes - all primes up to n */
    public static java.util.List<Integer> sieve(int n) {
        boolean[] isComposite = new boolean[n + 1];
        java.util.List<Integer> primes = new java.util.ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!isComposite[i]) {
                primes.add(i);
                for (long j = (long) i * i; j <= n; j += i) {
                    isComposite[(int) j] = true;
                }
            }
        }
        return primes;
    }

    /** GCD using Euclidean algorithm */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /** LCM using GCD */
    public static int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }

    public static void main(String[] args) {
        System.out.println("=== Primality Check ===");
        int[] tests = {1, 2, 7, 10, 13, 17, 20, 97};
        for (int n : tests) {
            System.out.println("  " + n + " is prime: " + isPrime(n));
        }

        System.out.println("\n=== Sieve (primes up to 50) ===");
        System.out.println("  " + sieve(50));

        System.out.println("\n=== GCD and LCM ===");
        System.out.println("  GCD(12, 8) = " + gcd(12, 8));
        System.out.println("  LCM(12, 8) = " + lcm(12, 8));
    }
}
