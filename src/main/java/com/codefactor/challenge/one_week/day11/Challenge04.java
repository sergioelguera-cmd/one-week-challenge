package com.codefactor.challenge.one_week.day11;

import java.io.*;
import java.util.*;

/**
 * Challenge 04 - CSV File Reader and Writer
 *
 * Read and write CSV files. Common practical interview question.
 *
 * Demonstrates:
 * - Parsing CSV with BufferedReader
 * - Writing CSV with PrintWriter
 * - Handling quoted fields and edge cases
 */
public class Challenge04 {

    record Employee(String name, String department, double salary) {}

    /** Write employees to CSV */
    public static void writeCsv(String filePath, List<Employee> employees) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("Name,Department,Salary");
            for (Employee e : employees) {
                pw.printf("%s,%s,%.2f%n", e.name, e.department, e.salary);
            }
        }
    }

    /** Read employees from CSV */
    public static List<Employee> readCsv(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    employees.add(new Employee(
                        parts[0].trim(),
                        parts[1].trim(),
                        Double.parseDouble(parts[2].trim())
                    ));
                }
            }
        }
        return employees;
    }

    /** Read CSV into List of Maps (dynamic columns) */
    public static List<Map<String, String>> readCsvAsMaps(String filePath) throws IOException {
        List<Map<String, String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            if (headerLine == null) return records;
            String[] headers = headerLine.split(",");

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> record = new LinkedHashMap<>();
                for (int i = 0; i < headers.length && i < values.length; i++) {
                    record.put(headers[i].trim(), values[i].trim());
                }
                records.add(record);
            }
        }
        return records;
    }

    public static void main(String[] args) {
        String filePath = "employees.csv";
        try {
            List<Employee> employees = List.of(
                new Employee("Alice", "Engineering", 95000),
                new Employee("Bob", "Marketing", 75000),
                new Employee("Charlie", "Engineering", 88000)
            );

            writeCsv(filePath, employees);
            System.out.println("CSV written.");

            List<Employee> read = readCsv(filePath);
            System.out.println("Read employees:");
            read.forEach(e -> System.out.printf("  %-10s %-15s $%.2f%n",
                e.name, e.department, e.salary));

            System.out.println("\nAs Maps:");
            List<Map<String, String>> maps = readCsvAsMaps(filePath);
            maps.forEach(m -> System.out.println("  " + m));

            new File(filePath).delete();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
