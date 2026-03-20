package com.codefactor.challenge.one_week.day10;

import java.util.concurrent.*;

/**
 * Challenge 07 - CountDownLatch and CyclicBarrier
 *
 * Synchronization aids for coordinating multiple threads.
 *
 * CountDownLatch: one-time gate - threads wait until count reaches zero
 * CyclicBarrier: reusable barrier - threads wait for each other at a point
 */
public class Challenge07 {

    public static void main(String[] args) throws InterruptedException {
        // CountDownLatch: Wait for 3 services to start
        System.out.println("=== CountDownLatch ===");
        CountDownLatch latch = new CountDownLatch(3);

        String[] services = {"Database", "Cache", "MessageQueue"};
        for (String service : services) {
            new Thread(() -> {
                try {
                    Thread.sleep((long) (Math.random() * 500));
                    System.out.println("  " + service + " started");
                    latch.countDown();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        System.out.println("  Waiting for all services...");
        latch.await();
        System.out.println("  All services ready! Application starting.");

        // CyclicBarrier: Parallel phases
        System.out.println("\n=== CyclicBarrier ===");
        int numWorkers = 3;
        CyclicBarrier barrier = new CyclicBarrier(numWorkers, () ->
            System.out.println("  --- All workers completed phase ---"));

        for (int i = 0; i < numWorkers; i++) {
            final int workerId = i + 1;
            new Thread(() -> {
                try {
                    // Phase 1
                    System.out.println("  Worker " + workerId + " doing phase 1");
                    Thread.sleep((long) (Math.random() * 300));
                    barrier.await();

                    // Phase 2
                    System.out.println("  Worker " + workerId + " doing phase 2");
                    Thread.sleep((long) (Math.random() * 300));
                    barrier.await();

                    System.out.println("  Worker " + workerId + " done!");
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        Thread.sleep(2000); // wait for demo to complete
    }
}
