package com.codefactor.challenge.one_week.day10;

/**
 * Challenge 09 - Deadlock Detection and Prevention
 *
 * Demonstrate deadlock scenario and how to prevent it.
 *
 * Deadlock conditions (all must hold):
 * 1. Mutual exclusion
 * 2. Hold and wait
 * 3. No preemption
 * 4. Circular wait
 *
 * Prevention: Lock ordering, tryLock with timeout
 */
public class Challenge09 {

    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    /** DEADLOCK EXAMPLE (commented out - would hang) */
    static void deadlockExample() {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK_A) {
                System.out.println("  Thread 1: holding Lock A, waiting for Lock B...");
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                synchronized (LOCK_B) {
                    System.out.println("  Thread 1: got both locks");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LOCK_B) {
                System.out.println("  Thread 2: holding Lock B, waiting for Lock A...");
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                synchronized (LOCK_A) {
                    System.out.println("  Thread 2: got both locks");
                }
            }
        });

        t1.start();
        t2.start();
    }

    /** FIXED: Consistent lock ordering prevents deadlock */
    static void fixedLockOrdering() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK_A) {
                System.out.println("  Thread 1: holding Lock A");
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                synchronized (LOCK_B) {
                    System.out.println("  Thread 1: got Lock B too");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LOCK_A) { // Same order as t1!
                System.out.println("  Thread 2: holding Lock A");
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                synchronized (LOCK_B) {
                    System.out.println("  Thread 2: got Lock B too");
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    /** FIXED: Using tryLock with timeout */
    static void fixedTryLock() throws InterruptedException {
        java.util.concurrent.locks.ReentrantLock lockA = new java.util.concurrent.locks.ReentrantLock();
        java.util.concurrent.locks.ReentrantLock lockB = new java.util.concurrent.locks.ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                if (lockA.tryLock(1, java.util.concurrent.TimeUnit.SECONDS)) {
                    try {
                        Thread.sleep(50);
                        if (lockB.tryLock(1, java.util.concurrent.TimeUnit.SECONDS)) {
                            try {
                                System.out.println("  Thread 1: got both locks with tryLock");
                            } finally { lockB.unlock(); }
                        }
                    } finally { lockA.unlock(); }
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        t1.start();
        t1.join();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Fixed: Lock Ordering ===");
        fixedLockOrdering();

        System.out.println("\n=== Fixed: tryLock ===");
        fixedTryLock();

        System.out.println("\n(Deadlock example is commented out to prevent hanging)");
    }
}
