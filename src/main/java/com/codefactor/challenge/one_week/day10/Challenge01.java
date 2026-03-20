package com.codefactor.challenge.one_week.day10;

/**
 * Challenge 01 - Creating Threads: Thread vs Runnable vs Callable
 *
 * Three ways to create threads in Java:
 * 1. Extending Thread class
 * 2. Implementing Runnable interface
 * 3. Using Callable with Future (returns a value)
 *
 * Interview tip: Prefer Runnable/Callable over extending Thread
 * because Java doesn't support multiple inheritance.
 */
public class Challenge01 {

    /** Approach 1: Extend Thread */
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("  Thread class: " + Thread.currentThread().getName());
        }
    }

    /** Approach 2: Implement Runnable */
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("  Runnable: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== Extending Thread ===");
        MyThread t1 = new MyThread();
        t1.start();
        t1.join();

        System.out.println("\n=== Implementing Runnable ===");
        Thread t2 = new Thread(new MyRunnable());
        t2.start();
        t2.join();

        System.out.println("\n=== Lambda Runnable ===");
        Thread t3 = new Thread(() ->
            System.out.println("  Lambda: " + Thread.currentThread().getName()));
        t3.start();
        t3.join();

        System.out.println("\n=== Callable with Future ===");
        java.util.concurrent.ExecutorService executor =
            java.util.concurrent.Executors.newSingleThreadExecutor();
        java.util.concurrent.Future<Integer> future = executor.submit(() -> {
            System.out.println("  Callable: " + Thread.currentThread().getName());
            return 42;
        });
        System.out.println("  Result: " + future.get());
        executor.shutdown();
    }
}
