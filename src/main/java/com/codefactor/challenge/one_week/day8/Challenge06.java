package com.codefactor.challenge.one_week.day8;

import java.util.*;
import java.util.stream.*;

/**
 * Challenge 06 - Custom Sorting with Streams and Comparators
 *
 * Sort objects using multiple criteria with Comparator chaining.
 *
 * Demonstrates:
 * - Comparator.comparing
 * - thenComparing
 * - reversed()
 * - nullsFirst/nullsLast
 */
public class Challenge06 {

    record Product(String name, String category, double price, Integer rating) {}

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 999.99, 4),
            new Product("Phone", "Electronics", 699.99, 5),
            new Product("Tablet", "Electronics", 499.99, 3),
            new Product("Shirt", "Clothing", 29.99, 4),
            new Product("Pants", "Clothing", 49.99, null),
            new Product("Book", "Books", 14.99, 5),
            new Product("Notebook", "Books", 9.99, 3)
        );

        // Sort by price ascending
        System.out.println("=== By Price (ascending) ===");
        products.stream()
            .sorted(Comparator.comparingDouble(Product::price))
            .forEach(p -> System.out.printf("  %-10s $%.2f%n", p.name, p.price));

        // Sort by category, then by price descending
        System.out.println("\n=== By Category, then Price (descending) ===");
        products.stream()
            .sorted(Comparator.comparing(Product::category)
                .thenComparing(Comparator.comparingDouble(Product::price).reversed()))
            .forEach(p -> System.out.printf("  %-12s %-10s $%.2f%n", p.category, p.name, p.price));

        // Sort with nulls handling
        System.out.println("\n=== By Rating (nulls last) ===");
        products.stream()
            .sorted(Comparator.comparing(Product::rating,
                Comparator.nullsLast(Comparator.reverseOrder())))
            .forEach(p -> System.out.printf("  %-10s rating=%s%n", p.name, p.rating));

        // Min and Max
        products.stream()
            .min(Comparator.comparingDouble(Product::price))
            .ifPresent(p -> System.out.println("\nCheapest: " + p.name + " $" + p.price));

        products.stream()
            .max(Comparator.comparingDouble(Product::price))
            .ifPresent(p -> System.out.println("Most expensive: " + p.name + " $" + p.price));
    }
}
