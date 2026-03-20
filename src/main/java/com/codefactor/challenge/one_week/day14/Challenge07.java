package com.codefactor.challenge.one_week.day14;

/**
 * Challenge 07 - Switch Expressions and Enhanced Switch (Java 14+)
 *
 * Modern switch with:
 * - Arrow syntax (no fall-through)
 * - Expression (returns value)
 * - Multiple case labels
 * - Pattern matching (Java 21+)
 */
public class Challenge07 {

    enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

    /** Classic switch vs arrow switch */
    static String getDayType(Day day) {
        return switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
            case SATURDAY, SUNDAY -> "Weekend";
        };
    }

    /** Switch expression with block */
    static int getDayNumber(Day day) {
        return switch (day) {
            case MONDAY -> 1;
            case TUESDAY -> 2;
            case WEDNESDAY -> 3;
            case THURSDAY -> 4;
            case FRIDAY -> 5;
            case SATURDAY -> 6;
            case SUNDAY -> {
                System.out.println("  (Last day of the week!)");
                yield 7; // yield returns value from block
            }
        };
    }

    /** Pattern matching in switch (Java 21+) */
    static String formatValue(Object obj) {
        return switch (obj) {
            case Integer i when i > 0 -> "Positive int: " + i;
            case Integer i -> "Non-positive int: " + i;
            case String s when s.length() > 10 -> "Long string: " + s.substring(0, 10) + "...";
            case String s -> "String: " + s;
            case Double d -> String.format("Double: %.2f", d);
            case null -> "null";
            default -> "Other: " + obj;
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Day Type ===");
        for (Day day : Day.values()) {
            System.out.printf("  %-10s -> %s%n", day, getDayType(day));
        }

        System.out.println("\n=== Day Number ===");
        System.out.println("Sunday = " + getDayNumber(Day.SUNDAY));

        System.out.println("\n=== Pattern Matching Switch ===");
        Object[] values = {42, -5, "hello", "a very long string indeed", 3.14, null, true};
        for (Object v : values) {
            System.out.println("  " + formatValue(v));
        }
    }
}
