package com.codefactor.challenge.one_week.day14;

/**
 * Challenge 06 - Interface Default and Static Methods
 *
 * Java 8+ allows default and static methods in interfaces.
 *
 * Key concepts:
 * - default: provides implementation, can be overridden
 * - static: belongs to interface, cannot be overridden
 * - Diamond problem resolution
 */
public class Challenge06 {

    interface Loggable {
        default void log(String message) {
            System.out.println("[" + getLogPrefix() + "] " + message);
        }

        String getLogPrefix();

        static String timestamp() {
            return java.time.LocalTime.now().toString();
        }
    }

    interface Auditable {
        default void log(String message) {
            System.out.println("[AUDIT] " + message);
        }
    }

    /** Diamond problem: must override conflicting default methods */
    static class Service implements Loggable, Auditable {
        @Override
        public String getLogPrefix() { return "SERVICE"; }

        @Override
        public void log(String message) {
            // Must resolve conflict explicitly
            Loggable.super.log(message); // call specific default
        }
    }

    /** Interface with private methods (Java 9+) */
    interface Validator {
        boolean validate(String input);

        default boolean validateAndLog(String input) {
            boolean result = validate(input);
            logResult(input, result);
            return result;
        }

        private void logResult(String input, boolean result) {
            System.out.println("  Validated '" + input + "': " + result);
        }

        // Static factory method in interface
        static Validator emailValidator() {
            return input -> input != null && input.contains("@") && input.contains(".");
        }

        static Validator lengthValidator(int min) {
            return input -> input != null && input.length() >= min;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Default Methods ===");
        Service service = new Service();
        service.log("Starting up");
        System.out.println("Timestamp: " + Loggable.timestamp());

        System.out.println("\n=== Interface Static Factory ===");
        Validator emailVal = Validator.emailValidator();
        emailVal.validateAndLog("user@email.com");
        emailVal.validateAndLog("invalid");

        Validator lengthVal = Validator.lengthValidator(5);
        lengthVal.validateAndLog("hi");
        lengthVal.validateAndLog("hello world");

        // Compose validators
        Validator combined = input -> emailVal.validate(input) && lengthVal.validate(input);
        System.out.println("\nCombined validator:");
        combined.validateAndLog("a@b.c");
        combined.validateAndLog("user@email.com");
    }
}
