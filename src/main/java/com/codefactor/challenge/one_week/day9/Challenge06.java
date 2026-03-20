package com.codefactor.challenge.one_week.day9;

/**
 * Challenge 06 - SOLID Principles Demonstration
 *
 * Demonstrate all 5 SOLID principles with practical examples.
 * Common interview question: Explain SOLID with code examples.
 *
 * S - Single Responsibility
 * O - Open/Closed
 * L - Liskov Substitution
 * I - Interface Segregation
 * D - Dependency Inversion
 */
public class Challenge06 {

    // === S: Single Responsibility ===
    // Each class has one reason to change
    static class UserValidator {
        public boolean isValid(String email) {
            return email != null && email.contains("@");
        }
    }

    static class UserRepository {
        public void save(String user) {
            System.out.println("  Saved user: " + user);
        }
    }

    // === O: Open/Closed ===
    // Open for extension, closed for modification
    interface Shape {
        double area();
    }

    record Circle(double radius) implements Shape {
        @Override public double area() { return Math.PI * radius * radius; }
    }

    record Rectangle(double width, double height) implements Shape {
        @Override public double area() { return width * height; }
    }

    // New shapes don't require modifying existing code
    record Triangle(double base, double height) implements Shape {
        @Override public double area() { return 0.5 * base * height; }
    }

    // === L: Liskov Substitution ===
    // Subtypes must be substitutable for their base types
    static class Bird {
        public String describe() { return "I am a bird"; }
    }
    static class Sparrow extends Bird {
        public String fly() { return "Flying high!"; }
    }

    // === I: Interface Segregation ===
    // Clients shouldn't depend on interfaces they don't use
    interface Printable { void print(); }
    interface Scannable { void scan(); }
    // Instead of one fat interface with print, scan, fax, copy

    static class SimplePrinter implements Printable {
        @Override public void print() { System.out.println("  Printing..."); }
    }

    static class MultiFunctionDevice implements Printable, Scannable {
        @Override public void print() { System.out.println("  MFD Printing..."); }
        @Override public void scan() { System.out.println("  MFD Scanning..."); }
    }

    // === D: Dependency Inversion ===
    // Depend on abstractions, not concretions
    interface MessageSender {
        void send(String message);
    }

    static class EmailSender implements MessageSender {
        @Override public void send(String msg) { System.out.println("  Email: " + msg); }
    }

    static class NotificationService {
        private final MessageSender sender; // depends on abstraction

        NotificationService(MessageSender sender) { this.sender = sender; }

        public void notify(String msg) { sender.send(msg); }
    }

    public static void main(String[] args) {
        System.out.println("=== S: Single Responsibility ===");
        UserValidator validator = new UserValidator();
        System.out.println("  Valid email: " + validator.isValid("test@mail.com"));

        System.out.println("\n=== O: Open/Closed ===");
        Shape[] shapes = {new Circle(5), new Rectangle(4, 6), new Triangle(3, 8)};
        for (Shape s : shapes) {
            System.out.printf("  %s area: %.2f%n", s.getClass().getSimpleName(), s.area());
        }

        System.out.println("\n=== I: Interface Segregation ===");
        new SimplePrinter().print();
        MultiFunctionDevice mfd = new MultiFunctionDevice();
        mfd.print();
        mfd.scan();

        System.out.println("\n=== D: Dependency Inversion ===");
        NotificationService service = new NotificationService(new EmailSender());
        service.notify("Hello from DI!");
    }
}
