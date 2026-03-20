package com.codefactor.challenge.one_week.day9;

/**
 * Challenge 02 - Factory and Abstract Factory Pattern
 *
 * Implement Factory Method pattern for creating objects without
 * specifying the exact class. Common interview pattern.
 *
 * Example: Create different types of notifications.
 */
public class Challenge02 {

    /** Product interface */
    interface Notification {
        void send(String message);
    }

    static class EmailNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("[EMAIL] " + message);
        }
    }

    static class SMSNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("[SMS] " + message);
        }
    }

    static class PushNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("[PUSH] " + message);
        }
    }

    /** Factory Method */
    static class NotificationFactory {
        public static Notification create(String type) {
            return switch (type.toUpperCase()) {
                case "EMAIL" -> new EmailNotification();
                case "SMS" -> new SMSNotification();
                case "PUSH" -> new PushNotification();
                default -> throw new IllegalArgumentException("Unknown type: " + type);
            };
        }
    }

    /** Abstract Factory for cross-platform UI components */
    interface Button { void render(); }
    interface TextField { void render(); }

    interface UIFactory {
        Button createButton();
        TextField createTextField();
    }

    static class WebUIFactory implements UIFactory {
        @Override public Button createButton() { return () -> System.out.println("<button>Web</button>"); }
        @Override public TextField createTextField() { return () -> System.out.println("<input type='text'/>"); }
    }

    static class MobileUIFactory implements UIFactory {
        @Override public Button createButton() { return () -> System.out.println("[Mobile Button]"); }
        @Override public TextField createTextField() { return () -> System.out.println("[Mobile Input]"); }
    }

    public static void main(String[] args) {
        System.out.println("=== Factory Method ===");
        Notification email = NotificationFactory.create("EMAIL");
        Notification sms = NotificationFactory.create("SMS");
        email.send("Hello via factory!");
        sms.send("Hello via factory!");

        System.out.println("\n=== Abstract Factory ===");
        UIFactory webFactory = new WebUIFactory();
        webFactory.createButton().render();
        webFactory.createTextField().render();

        UIFactory mobileFactory = new MobileUIFactory();
        mobileFactory.createButton().render();
        mobileFactory.createTextField().render();
    }
}
