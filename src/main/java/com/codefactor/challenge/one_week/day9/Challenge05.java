package com.codefactor.challenge.one_week.day9;

/**
 * Challenge 05 - Strategy Pattern
 *
 * Define a family of algorithms, encapsulate each one, and make them
 * interchangeable. The Strategy pattern lets the algorithm vary independently
 * from clients that use it.
 *
 * Example: Different sorting/payment/compression strategies.
 */
public class Challenge05 {

    /** Strategy interface */
    @FunctionalInterface
    interface PaymentStrategy {
        void pay(double amount);
    }

    /** Concrete strategies */
    static class CreditCardPayment implements PaymentStrategy {
        private final String cardNumber;

        CreditCardPayment(String cardNumber) { this.cardNumber = cardNumber; }

        @Override
        public void pay(double amount) {
            System.out.printf("  Paid $%.2f via Credit Card ending in %s%n",
                amount, cardNumber.substring(cardNumber.length() - 4));
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        private final String email;

        PayPalPayment(String email) { this.email = email; }

        @Override
        public void pay(double amount) {
            System.out.printf("  Paid $%.2f via PayPal (%s)%n", amount, email);
        }
    }

    /** Context class */
    static class ShoppingCart {
        private double total;

        public void addItem(String name, double price) {
            total += price;
            System.out.println("  Added " + name + ": $" + price);
        }

        public void checkout(PaymentStrategy strategy) {
            System.out.printf("  Total: $%.2f%n", total);
            strategy.pay(total);
            total = 0;
        }
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        System.out.println("=== Credit Card Payment ===");
        cart.addItem("Laptop", 999.99);
        cart.addItem("Mouse", 29.99);
        cart.checkout(new CreditCardPayment("1234567890121234"));

        System.out.println("\n=== PayPal Payment ===");
        cart.addItem("Keyboard", 79.99);
        cart.checkout(new PayPalPayment("user@email.com"));

        // Using lambda (since PaymentStrategy is @FunctionalInterface)
        System.out.println("\n=== Lambda Payment ===");
        cart.addItem("Monitor", 399.99);
        cart.checkout(amount -> System.out.printf("  Paid $%.2f via Bitcoin%n", amount));
    }
}
