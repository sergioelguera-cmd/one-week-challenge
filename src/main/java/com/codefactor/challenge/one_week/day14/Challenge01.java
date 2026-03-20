package com.codefactor.challenge.one_week.day14;

import java.util.function.*;

/**
 * Challenge 01 - Lambda Expressions and Functional Interfaces
 *
 * Demonstrate lambda syntax and built-in functional interfaces:
 * - Function<T,R>, Predicate<T>, Consumer<T>, Supplier<T>
 * - BiFunction, UnaryOperator, BinaryOperator
 *
 * Interview tip: Know the 4 core functional interfaces and their methods.
 */
public class Challenge01 {

    @FunctionalInterface
    interface StringProcessor {
        String process(String input);
    }

    public static void main(String[] args) {
        // Predicate - test (returns boolean)
        Predicate<String> isLong = s -> s.length() > 5;
        System.out.println("Predicate 'hello': " + isLong.test("hello"));
        System.out.println("Predicate 'hello world': " + isLong.test("hello world"));

        // Predicate chaining
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
        System.out.println("4 positive & even: " + isPositiveAndEven.test(4));
        System.out.println("-2 positive & even: " + isPositiveAndEven.test(-2));

        // Function - apply (T -> R)
        Function<String, Integer> length = String::length;
        Function<Integer, String> intToStr = n -> "Number: " + n;
        Function<String, String> composed = length.andThen(intToStr);
        System.out.println("\nFunction compose: " + composed.apply("hello"));

        // Consumer - accept (T -> void)
        Consumer<String> printer = System.out::println;
        Consumer<String> upper = s -> System.out.println(s.toUpperCase());
        Consumer<String> both = printer.andThen(upper);
        System.out.println("\nConsumer chain:");
        both.accept("test");

        // Supplier - get (() -> T)
        Supplier<Double> random = Math::random;
        System.out.println("\nSupplier: " + random.get());

        // Custom functional interface
        StringProcessor reverse = s -> new StringBuilder(s).reverse().toString();
        StringProcessor toUpper = String::toUpperCase;
        System.out.println("\nCustom FI: " + reverse.process("hello"));
        System.out.println("Method ref: " + toUpper.process("hello"));

        // UnaryOperator (Function<T,T>)
        UnaryOperator<String> exclaim = s -> s + "!";
        System.out.println("UnaryOp: " + exclaim.apply("wow"));

        // BinaryOperator
        BinaryOperator<Integer> max = Integer::max;
        System.out.println("BinaryOp max: " + max.apply(3, 7));
    }
}
