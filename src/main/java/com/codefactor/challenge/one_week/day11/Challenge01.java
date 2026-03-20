package com.codefactor.challenge.one_week.day11;

import java.io.*;

/**
 * Challenge 01 - Reading and Writing Files (Classic I/O)
 *
 * Demonstrate reading and writing files using:
 * 1. BufferedReader / BufferedWriter
 * 2. FileReader / FileWriter
 * 3. Try-with-resources for automatic closing
 *
 * Interview tip: Always use try-with-resources for I/O operations.
 */
public class Challenge01 {

    /** Write text to a file using BufferedWriter */
    public static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    /** Read entire file using BufferedReader */
    public static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (sb.length() > 0) sb.append("\n");
                sb.append(line);
            }
        }
        return sb.toString();
    }

    /** Append to file */
    public static void appendToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine();
            writer.write(content);
        }
    }

    /** Read file line by line into a list */
    public static java.util.List<String> readLines(String filePath) throws IOException {
        java.util.List<String> lines = new java.util.ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static void main(String[] args) {
        String testFile = "test_io.txt";
        try {
            // Write
            writeFile(testFile, "Hello World\nJava File I/O\nLine Three");
            System.out.println("Written to file.");

            // Read entire file
            String content = readFile(testFile);
            System.out.println("Read:\n" + content);

            // Append
            appendToFile(testFile, "Appended Line");
            System.out.println("\nAfter append:");
            System.out.println(readFile(testFile));

            // Read lines
            System.out.println("\nLines: " + readLines(testFile));

            // Cleanup
            new File(testFile).delete();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
