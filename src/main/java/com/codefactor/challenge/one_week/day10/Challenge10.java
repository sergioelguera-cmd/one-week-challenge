package com.codefactor.challenge.one_week.day10;

import java.util.concurrent.*;

/**
 * Challenge 10 - Semaphore and Rate Limiting
 *
 * Use Semaphore to limit concurrent access to a resource.
 *
 * Real-world use case: connection pool, rate limiter, resource throttling.
 */
public class Challenge10 {

    /** Connection pool using Semaphore */
    static class ConnectionPool {
        private final Semaphore semaphore;
        private final int maxConnections;

        ConnectionPool(int maxConnections) {
            this.maxConnections = maxConnections;
            this.semaphore = new Semaphore(maxConnections);
        }

        public void useConnection(String taskName) {
            try {
                System.out.println("  " + taskName + " waiting for connection... " +
                    "(available: " + semaphore.availablePermits() + "/" + maxConnections + ")");
                semaphore.acquire();
                System.out.println("  " + taskName + " acquired connection");
                Thread.sleep(500); // simulate work
                System.out.println("  " + taskName + " releasing connection");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        }

        /** Try to acquire with timeout */
        public boolean tryUseConnection(String taskName, long timeoutMs) {
            try {
                if (semaphore.tryAcquire(timeoutMs, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("  " + taskName + " got connection (tryAcquire)");
                        Thread.sleep(200);
                        return true;
                    } finally {
                        semaphore.release();
                    }
                } else {
                    System.out.println("  " + taskName + " timed out waiting for connection");
                    return false;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Semaphore Connection Pool (max 2) ===");
        ConnectionPool pool = new ConnectionPool(2);

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final String name = "Task-" + (i + 1);
            threads[i] = new Thread(() -> pool.useConnection(name));
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("All tasks completed.");
    }
}
