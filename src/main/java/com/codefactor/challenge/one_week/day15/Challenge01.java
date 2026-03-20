package com.codefactor.challenge.one_week.day15;

/**
 * Challenge 01 - Exception Handling Best Practices
 *
 * Demonstrate checked vs unchecked exceptions, custom exceptions,
 * try-with-resources, and multi-catch.
 *
 * Interview tip: Know the difference between Error, Exception,
 * RuntimeException, and when to use checked vs unchecked.
 */
public class Challenge01 {

    /** Custom checked exception */
    static class InsufficientFundsException extends Exception {
        private final double balance;
        private final double amount;

        InsufficientFundsException(double balance, double amount) {
            super(String.format("Insufficient funds: balance=%.2f, requested=%.2f", balance, amount));
            this.balance = balance;
            this.amount = amount;
        }

        public double getDeficit() { return amount - balance; }
    }

    /** Custom unchecked exception */
    static class InvalidAccountException extends RuntimeException {
        InvalidAccountException(String accountId) {
            super("Invalid account: " + accountId);
        }
    }

    static class BankAccount {
        private double balance;
        private final String id;

        BankAccount(String id, double balance) {
            if (id == null || id.isEmpty()) throw new InvalidAccountException(id);
            this.id = id;
            this.balance = balance;
        }

        /** Checked exception - caller must handle */
        public void withdraw(double amount) throws InsufficientFundsException {
            if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
            if (amount > balance) throw new InsufficientFundsException(balance, amount);
            balance -= amount;
        }

        public double getBalance() { return balance; }
    }

    public static void main(String[] args) {
        // Try-catch-finally
        System.out.println("=== Basic Exception Handling ===");
        BankAccount account = new BankAccount("ACC001", 100.0);
        try {
            account.withdraw(30.0);
            System.out.println("  Withdrew $30. Balance: $" + account.getBalance());
            account.withdraw(80.0); // will throw
        } catch (InsufficientFundsException e) {
            System.out.println("  " + e.getMessage());
            System.out.println("  Deficit: $" + e.getDeficit());
        } finally {
            System.out.println("  Final balance: $" + account.getBalance());
        }

        // Multi-catch (Java 7+)
        System.out.println("\n=== Multi-catch ===");
        try {
            String s = null;
            s.length(); // NullPointerException
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("  Caught: " + e.getClass().getSimpleName());
        }

        // Unchecked exception
        System.out.println("\n=== Unchecked Exception ===");
        try {
            new BankAccount(null, 100);
        } catch (InvalidAccountException e) {
            System.out.println("  " + e.getMessage());
        }

        // Try-with-resources
        System.out.println("\n=== Try-with-resources ===");
        try (var resource = new AutoCloseable() {
            { System.out.println("  Resource opened"); }
            @Override public void close() { System.out.println("  Resource closed automatically"); }
        }) {
            System.out.println("  Using resource...");
        } catch (Exception e) {
            System.out.println("  Error: " + e.getMessage());
        }
    }
}
