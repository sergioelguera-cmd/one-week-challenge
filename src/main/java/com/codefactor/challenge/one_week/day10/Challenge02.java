package com.codefactor.challenge.one_week.day10;

/**
 * Challenge 02 - Synchronized and Thread Safety
 *
 * Demonstrate race conditions and how to fix them with synchronization.
 *
 * Key concepts:
 * - synchronized method
 * - synchronized block
 * - volatile keyword
 * - AtomicInteger
 */
public class Challenge02 {

    /** Unsafe counter - has race condition */
    static class UnsafeCounter {
        private int count = 0;
        public void increment() { count++; }
        public int getCount() { return count; }
    }

    /** Thread-safe counter using synchronized */
    static class SyncCounter {
        private int count = 0;
        public synchronized void increment() { count++; }
        public synchronized int getCount() { return count; }
    }

    /** Thread-safe counter using AtomicInteger */
    static class AtomicCounter {
        private final java.util.concurrent.atomic.AtomicInteger count =
            new java.util.concurrent.atomic.AtomicInteger(0);
        public void increment() { count.incrementAndGet(); }
        public int getCount() { return count.get(); }
    }

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 10;
        int incrementsPerThread = 10000;

        // Test unsafe counter
        UnsafeCounter unsafe = new UnsafeCounter();
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) unsafe.increment();
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("Unsafe counter:  " + unsafe.getCount() +
            " (expected " + (numThreads * incrementsPerThread) + ")");

        // Test synchronized counter
        SyncCounter sync = new SyncCounter();
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) sync.increment();
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("Sync counter:    " + sync.getCount());

        // Test atomic counter
        AtomicCounter atomic = new AtomicCounter();
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) atomic.increment();
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("Atomic counter:  " + atomic.getCount());
    }
}
