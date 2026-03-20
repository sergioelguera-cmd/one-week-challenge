package com.codefactor.challenge.one_week.day14;

/**
 * Challenge 03 - Records, Sealed Classes, and Pattern Matching (Java 14-17+)
 *
 * Modern Java features commonly asked about in interviews.
 *
 * Records: immutable data carriers (replaces POJO boilerplate)
 * Sealed: restrict class hierarchy
 * Pattern matching: enhanced instanceof and switch
 */
public class Challenge03 {

    /** Record - generates equals, hashCode, toString, getters automatically */
    record Point(double x, double y) {
        // Custom constructor with validation
        Point {
            if (Double.isNaN(x) || Double.isNaN(y)) {
                throw new IllegalArgumentException("Coordinates cannot be NaN");
            }
        }

        // Custom method
        double distanceTo(Point other) {
            return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
        }
    }

    /** Sealed interface - only permitted subtypes can implement */
    sealed interface Shape permits CircleShape, RectangleShape, TriangleShape {}

    record CircleShape(double radius) implements Shape {}
    record RectangleShape(double width, double height) implements Shape {}
    record TriangleShape(double base, double height) implements Shape {}

    /** Pattern matching with instanceof to calculate area */
    static double calculateArea(Shape shape) {
        if (shape instanceof CircleShape c) {
            return Math.PI * c.radius() * c.radius();
        } else if (shape instanceof RectangleShape r) {
            return r.width() * r.height();
        } else if (shape instanceof TriangleShape t) {
            return 0.5 * t.base() * t.height();
        }
        throw new IllegalArgumentException("Unknown shape: " + shape);
    }

    /** Pattern matching with instanceof (Java 16+) */
    static String describe(Object obj) {
        if (obj instanceof String s && !s.isEmpty()) {
            return "Non-empty string of length " + s.length();
        } else if (obj instanceof Integer i && i > 0) {
            return "Positive integer: " + i;
        } else if (obj instanceof Point p) {
            return "Point at (" + p.x() + ", " + p.y() + ")";
        }
        return "Unknown: " + obj;
    }

    /** Text blocks (Java 15+) */
    static String getJson() {
        return """
                {
                    "name": "Alice",
                    "age": 30,
                    "active": true
                }
                """;
    }

    public static void main(String[] args) {
        // Records
        Point p1 = new Point(3, 4);
        Point p2 = new Point(0, 0);
        System.out.println("Point: " + p1);
        System.out.println("Distance to origin: " + p1.distanceTo(p2));
        System.out.println("Equals: " + p1.equals(new Point(3, 4)));

        // Sealed + pattern matching switch
        System.out.println("\n=== Sealed + Pattern Matching ===");
        Shape[] shapes = {new CircleShape(5), new RectangleShape(4, 6), new TriangleShape(3, 8)};
        for (Shape s : shapes) {
            System.out.printf("  %s -> area = %.2f%n", s, calculateArea(s));
        }

        // Pattern matching instanceof
        System.out.println("\n=== instanceof Pattern ===");
        Object[] objects = {"hello", 42, -5, p1, null};
        for (Object o : objects) {
            System.out.println("  " + describe(o));
        }

        // Text blocks
        System.out.println("\n=== Text Block ===");
        System.out.println(getJson());
    }
}
