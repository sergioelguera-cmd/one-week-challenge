package com.codefactor.challenge.one_week.day10;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * Challenge 06 - ReentrantLock vs Synchronized
 *
 * Compare ReentrantLock with synchronized keyword.
 *
 * ReentrantLock advantages:
 * - tryLock with timeout
 * - lockInterruptibly
 * - Fairness policy
 * - Multiple condition variables
 * - Must explicitly unlock (try-finally)
 */
public class Challenge06 {

    /** Using synchronized */
    static class SyncBankAccount {
        private double balance;

        SyncBankAccount(double balance) { this.balance = balance; }

        public synchronized void deposit(double amount) {
            balance += amount;
        }

        public synchronized boolean withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        }

        public synchronized double getBalance() { return balance; }
    }

    /** Using ReentrantLock */
    static class LockBankAccount {
        private double balance;
        private final ReentrantLock lock = new ReentrantLock();

        LockBankAccount(double balance) { this.balance = balance; }

        public void deposit(double amount) {
            lock.lock();
            try {
                balance += amount;
            } finally {
                lock.unlock();
            }
        }

        public boolean withdraw(double amount) {
            lock.lock();
            try {
                if (balance >= amount) {
                    balance -= amount;
                    return true;
                }
                return false;
            } finally {
                lock.unlock();
            }
        }

        /** tryLock - non-blocking attempt */
        public boolean tryWithdraw(double amount, long timeoutMs) {
            try {
                if (lock.tryLock(timeoutMs, TimeUnit.MILLISECONDS)) {
                    try {
                        if (balance >= amount) {
                            balance -= amount;
                            return true;
                        }
                        return false;
                    } finally {
                        lock.unlock();
                    }
                }
                return false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        public double getBalance() {
            lock.lock();
            try { return balance; } finally { lock.unlock(); }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockBankAccount account = new LockBankAccount(1000);
        int numThreads = 5;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    account.deposit(10);
                    account.withdraw(10);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();

        System.out.println("Final balance: $" + account.getBalance() + " (expected $1000.0)");
        System.out.println("tryWithdraw $500: " + account.tryWithdraw(500, 1000));
        System.out.println("Balance after: $" + account.getBalance());
    }
}
