package com.codefactor.challenge.one_week.day8;

import java.util.*;
import java.util.stream.*;

/**
 * Challenge 01 - Filter Employees by Salary using Streams
 *
 * Given a list of Employee objects, use Java Streams to:
 * 1. Filter employees by salary threshold
 * 2. Group employees by department
 * 3. Find highest paid employee per department
 *
 * Demonstrates: filter, map, collect, groupingBy, maxBy
 */
public class Challenge01 {

    static class Employee {
        String name, department;
        double salary;

        Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return name + "(" + department + ", $" + salary + ")";
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 95000),
            new Employee("Bob", "Engineering", 85000),
            new Employee("Charlie", "Marketing", 70000),
            new Employee("Diana", "Marketing", 80000),
            new Employee("Eve", "HR", 65000),
            new Employee("Frank", "HR", 72000),
            new Employee("Grace", "Engineering", 110000)
        );

        // 1. Filter by salary > 80000
        System.out.println("=== Salary > $80,000 ===");
        employees.stream()
            .filter(e -> e.salary > 80000)
            .forEach(System.out::println);

        // 2. Group by department
        System.out.println("\n=== Grouped by Department ===");
        Map<String, List<Employee>> byDept = employees.stream()
            .collect(Collectors.groupingBy(e -> e.department));
        byDept.forEach((dept, emps) ->
            System.out.println(dept + ": " + emps));

        // 3. Highest paid per department
        System.out.println("\n=== Highest Paid per Department ===");
        Map<String, Optional<Employee>> topPerDept = employees.stream()
            .collect(Collectors.groupingBy(
                e -> e.department,
                Collectors.maxBy(Comparator.comparingDouble(e -> e.salary))
            ));
        topPerDept.forEach((dept, emp) ->
            System.out.println(dept + ": " + emp.orElse(null)));

        // 4. Average salary per department
        System.out.println("\n=== Average Salary per Department ===");
        Map<String, Double> avgSalary = employees.stream()
            .collect(Collectors.groupingBy(
                e -> e.department,
                Collectors.averagingDouble(e -> e.salary)
            ));
        avgSalary.forEach((dept, avg) ->
            System.out.printf("%s: $%.2f%n", dept, avg));

        // 5. Total salary sum
        double totalSalary = employees.stream()
            .mapToDouble(e -> e.salary)
            .sum();
        System.out.printf("\nTotal salary: $%.2f%n", totalSalary);
    }
}
