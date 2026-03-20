package com.codefactor.challenge.one_week.day15;

/**
 * Challenge 05 - Generic Interfaces and Enum with Behavior
 *
 * Combine generics with interfaces and enums for type-safe designs.
 *
 * Covers: generic interfaces, enum methods, enum with abstract methods
 */
public class Challenge05 {

    /** Generic repository interface */
    interface Repository<T, ID> {
        T findById(ID id);
        java.util.List<T> findAll();
        T save(T entity);
        void deleteById(ID id);
    }

    record User(int id, String name) {}

    /** In-memory implementation */
    static class InMemoryUserRepo implements Repository<User, Integer> {
        private final java.util.Map<Integer, User> store = new java.util.HashMap<>();

        @Override
        public User findById(Integer id) { return store.get(id); }

        @Override
        public java.util.List<User> findAll() { return new java.util.ArrayList<>(store.values()); }

        @Override
        public User save(User user) { store.put(user.id(), user); return user; }

        @Override
        public void deleteById(Integer id) { store.remove(id); }
    }

    /** Enum with behavior */
    enum Operation {
        ADD("+") {
            @Override public double apply(double a, double b) { return a + b; }
        },
        SUBTRACT("-") {
            @Override public double apply(double a, double b) { return a - b; }
        },
        MULTIPLY("*") {
            @Override public double apply(double a, double b) { return a * b; }
        },
        DIVIDE("/") {
            @Override public double apply(double a, double b) {
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };

        private final String symbol;
        Operation(String symbol) { this.symbol = symbol; }

        public abstract double apply(double a, double b);

        @Override
        public String toString() { return symbol; }
    }

    /** Enum with fields and methods */
    enum HttpStatus {
        OK(200, "Success"),
        NOT_FOUND(404, "Not Found"),
        INTERNAL_ERROR(500, "Internal Server Error");

        private final int code;
        private final String message;

        HttpStatus(int code, String message) { this.code = code; this.message = message; }

        public int getCode() { return code; }
        public String getMessage() { return message; }

        public boolean isError() { return code >= 400; }

        public static HttpStatus fromCode(int code) {
            for (HttpStatus status : values()) {
                if (status.code == code) return status;
            }
            throw new IllegalArgumentException("Unknown code: " + code);
        }
    }

    public static void main(String[] args) {
        // Generic repository
        System.out.println("=== Generic Repository ===");
        InMemoryUserRepo repo = new InMemoryUserRepo();
        repo.save(new User(1, "Alice"));
        repo.save(new User(2, "Bob"));
        System.out.println("Find 1: " + repo.findById(1));
        System.out.println("All: " + repo.findAll());

        // Enum with behavior
        System.out.println("\n=== Enum Operations ===");
        double a = 10, b = 3;
        for (Operation op : Operation.values()) {
            System.out.printf("  %.0f %s %.0f = %.2f%n", a, op, b, op.apply(a, b));
        }

        // Enum utility methods
        System.out.println("\n=== HttpStatus Enum ===");
        for (HttpStatus status : HttpStatus.values()) {
            System.out.printf("  %d %s (error: %s)%n", status.getCode(), status.getMessage(), status.isError());
        }
        System.out.println("  fromCode(404): " + HttpStatus.fromCode(404));
    }
}
