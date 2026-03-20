package com.codefactor.challenge.one_week.day9;

/**
 * Challenge 10 - Adapter Pattern
 *
 * Convert the interface of a class into another interface clients expect.
 * Allows classes to work together that couldn't otherwise due to incompatible interfaces.
 *
 * Example: Adapting legacy payment system to new interface.
 */
public class Challenge10 {

    /** Target interface (what the client expects) */
    interface ModernPaymentProcessor {
        boolean processPayment(String orderId, double amount, String currency);
        boolean refund(String transactionId);
    }

    /** Adaptee (legacy system with incompatible interface) */
    static class LegacyPaymentGateway {
        public int makePayment(int amountInCents, String merchantId) {
            System.out.println("  [Legacy] Processing " + amountInCents + " cents for " + merchantId);
            return 12345; // transaction ID
        }

        public boolean reverseTransaction(int transactionId) {
            System.out.println("  [Legacy] Reversing transaction #" + transactionId);
            return true;
        }
    }

    /** Adapter: wraps legacy system to conform to modern interface */
    static class PaymentAdapter implements ModernPaymentProcessor {
        private final LegacyPaymentGateway legacy;
        private final String merchantId;

        PaymentAdapter(LegacyPaymentGateway legacy, String merchantId) {
            this.legacy = legacy;
            this.merchantId = merchantId;
        }

        @Override
        public boolean processPayment(String orderId, double amount, String currency) {
            int cents = (int) (amount * 100);
            int txId = legacy.makePayment(cents, merchantId);
            System.out.println("  [Adapter] Mapped order " + orderId + " -> tx #" + txId);
            return txId > 0;
        }

        @Override
        public boolean refund(String transactionId) {
            return legacy.reverseTransaction(Integer.parseInt(transactionId));
        }
    }

    /** Client code works with ModernPaymentProcessor */
    static class CheckoutService {
        private final ModernPaymentProcessor processor;

        CheckoutService(ModernPaymentProcessor processor) {
            this.processor = processor;
        }

        public void checkout(String orderId, double amount) {
            System.out.println("Checkout order: " + orderId);
            boolean success = processor.processPayment(orderId, amount, "USD");
            System.out.println("Result: " + (success ? "SUCCESS" : "FAILED"));
        }
    }

    public static void main(String[] args) {
        // Client uses modern interface; adapter handles legacy translation
        LegacyPaymentGateway legacy = new LegacyPaymentGateway();
        ModernPaymentProcessor adapter = new PaymentAdapter(legacy, "MERCHANT_001");
        CheckoutService checkout = new CheckoutService(adapter);

        checkout.checkout("ORD-001", 49.99);
        System.out.println();
        adapter.refund("12345");
    }
}
