package com.codefactor.challenge.one_week.day9;

/**
 * Challenge 07 - Immutable Class Design
 *
 * Create a properly immutable class following best practices.
 *
 * Rules for immutability:
 * 1. Declare class as final
 * 2. Make all fields private and final
 * 3. No setter methods
 * 4. Deep copy mutable objects in constructor and getters
 * 5. Constructor performs validation
 */
public class Challenge07 {

    /** Properly immutable class */
    static final class Money {
        private final double amount;
        private final String currency;

        public Money(double amount, String currency) {
            if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
            if (currency == null || currency.isEmpty()) {
                throw new IllegalArgumentException("Currency required");
            }
            this.amount = amount;
            this.currency = currency;
        }

        public double getAmount() { return amount; }
        public String getCurrency() { return currency; }

        public Money add(Money other) {
            if (!this.currency.equals(other.currency)) {
                throw new IllegalArgumentException("Cannot add different currencies");
            }
            return new Money(this.amount + other.amount, this.currency);
        }

        public Money multiply(double factor) {
            return new Money(this.amount * factor, this.currency);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Money money)) return false;
            return Double.compare(money.amount, amount) == 0
                && currency.equals(money.currency);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(amount, currency);
        }

        @Override
        public String toString() {
            return String.format("%.2f %s", amount, currency);
        }
    }

    /** Immutable class with mutable field (defensive copy) */
    static final class Event {
        private final String name;
        private final java.util.Date date; // Date is mutable!

        public Event(String name, java.util.Date date) {
            this.name = name;
            this.date = new java.util.Date(date.getTime()); // defensive copy
        }

        public String getName() { return name; }

        public java.util.Date getDate() {
            return new java.util.Date(date.getTime()); // defensive copy on return
        }

        @Override
        public String toString() {
            return name + " at " + date;
        }
    }

    public static void main(String[] args) {
        Money price = new Money(29.99, "USD");
        Money tax = new Money(2.40, "USD");
        Money total = price.add(tax);

        System.out.println("Price: " + price);
        System.out.println("Tax:   " + tax);
        System.out.println("Total: " + total);
        System.out.println("Equal: " + price.equals(new Money(29.99, "USD")));

        // Defensive copy demonstration
        java.util.Date date = new java.util.Date();
        Event event = new Event("Meeting", date);
        System.out.println("\nEvent: " + event);

        // Mutating the original date doesn't affect the Event
        date.setTime(0);
        System.out.println("After mutating original date: " + event);
        System.out.println("(Date unchanged - defensive copy works)");
    }
}
