package com.codefactor.challenge.one_week.day10;

import java.util.concurrent.*;

/**
 * Challenge 04 - Producer-Consumer Pattern
 *
 * Classic concurrency pattern using BlockingQueue.
 * Producer adds items, Consumer takes items.
 *
 * BlockingQueue handles synchronization automatically.
 */
public class Challenge04 {

    static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final int itemCount;

        Producer(BlockingQueue<Integer> queue, int itemCount) {
            this.queue = queue;
            this.itemCount = itemCount;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= itemCount; i++) {
                    System.out.println("  Producing: " + i);
                    queue.put(i);
                    Thread.sleep(100);
                }
                queue.put(-1); // poison pill to signal completion
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;

        Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int item = queue.take();
                    if (item == -1) break; // poison pill
                    System.out.println("  Consuming: " + item);
                    Thread.sleep(150); // consumer is slower
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // ArrayBlockingQueue with capacity 3 (bounded buffer)
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        System.out.println("=== Producer-Consumer with BlockingQueue ===");
        Thread producer = new Thread(new Producer(queue, 5), "Producer");
        Thread consumer = new Thread(new Consumer(queue), "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
        System.out.println("Done!");
    }
}
