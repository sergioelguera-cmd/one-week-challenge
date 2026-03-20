package com.codefactor.challenge.one_week.day9;

/**
 * Challenge 01 - Singleton Pattern
 *
 * Implement the Singleton design pattern in multiple ways:
 * 1. Eager initialization
 * 2. Lazy initialization (thread-safe with double-checked locking)
 * 3. Enum-based (recommended by Effective Java)
 *
 * Common interview question: What are the pitfalls of Singleton?
 */
public class Challenge01 {

    /** Approach 1: Eager Singleton */
    static class EagerSingleton {
        private static final EagerSingleton INSTANCE = new EagerSingleton();
        private EagerSingleton() {}
        public static EagerSingleton getInstance() { return INSTANCE; }
    }

    /** Approach 2: Lazy Singleton with double-checked locking */
    static class LazySingleton {
        private static volatile LazySingleton instance;
        private LazySingleton() {}

        public static LazySingleton getInstance() {
            if (instance == null) {
                synchronized (LazySingleton.class) {
                    if (instance == null) {
                        instance = new LazySingleton();
                    }
                }
            }
            return instance;
        }
    }

    /** Approach 3: Enum Singleton (serialization-safe, reflection-safe) */
    enum EnumSingleton {
        INSTANCE;

        private int value;

        public int getValue() { return value; }
        public void setValue(int value) { this.value = value; }
    }

    /** Approach 4: Bill Pugh Singleton (inner static holder) */
    static class BillPughSingleton {
        private BillPughSingleton() {}

        private static class Holder {
            private static final BillPughSingleton INSTANCE = new BillPughSingleton();
        }

        public static BillPughSingleton getInstance() {
            return Holder.INSTANCE;
        }
    }

    public static void main(String[] args) {
        // All return the same instance
        System.out.println("Eager:    " + (EagerSingleton.getInstance() == EagerSingleton.getInstance()));
        System.out.println("Lazy:     " + (LazySingleton.getInstance() == LazySingleton.getInstance()));
        System.out.println("BillPugh: " + (BillPughSingleton.getInstance() == BillPughSingleton.getInstance()));

        // Enum singleton
        EnumSingleton.INSTANCE.setValue(42);
        System.out.println("Enum:     " + EnumSingleton.INSTANCE.getValue());
        System.out.println("Same:     " + (EnumSingleton.INSTANCE == EnumSingleton.INSTANCE));
    }
}
