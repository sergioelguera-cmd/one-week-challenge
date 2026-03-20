package com.codefactor.challenge.one_week.day11;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

/**
 * Challenge 06 - Directory Traversal and File Search
 *
 * Walk directory trees, find files, and filter by extension.
 *
 * Methods: Files.walk, Files.find, Files.walkFileTree (visitor pattern)
 */
public class Challenge06 {

    /** List all Java files in directory tree */
    public static void findJavaFiles(Path startDir) throws IOException {
        System.out.println("Java files in " + startDir + ":");
        try (Stream<Path> paths = Files.walk(startDir)) {
            paths.filter(p -> p.toString().endsWith(".java"))
                 .forEach(p -> System.out.println("  " + p));
        }
    }

    /** Find files larger than given size */
    public static void findLargeFiles(Path startDir, long minBytes) throws IOException {
        System.out.println("Files > " + minBytes + " bytes:");
        try (Stream<Path> paths = Files.find(startDir, Integer.MAX_VALUE,
            (path, attrs) -> attrs.isRegularFile() && attrs.size() > minBytes)) {
            paths.forEach(p -> {
                try {
                    System.out.printf("  %-40s %d bytes%n", p, Files.size(p));
                } catch (IOException e) { /* skip */ }
            });
        }
    }

    /** FileVisitor pattern - complete control over traversal */
    static class FileCounter extends SimpleFileVisitor<Path> {
        int fileCount = 0;
        int dirCount = 0;
        long totalSize = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            fileCount++;
            totalSize += attrs.size();
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            dirCount++;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println("  Failed to visit: " + file);
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {
        Path currentDir = Paths.get(".");

        // Count files and directories
        System.out.println("=== File Counter (FileVisitor) ===");
        FileCounter counter = new FileCounter();
        Files.walkFileTree(currentDir, counter);
        System.out.println("  Files: " + counter.fileCount);
        System.out.println("  Directories: " + counter.dirCount);
        System.out.println("  Total size: " + counter.totalSize + " bytes");

        // Find Java files (if in project directory)
        Path srcDir = Paths.get("src");
        if (Files.exists(srcDir)) {
            System.out.println("\n=== Java Files ===");
            findJavaFiles(srcDir);
        }
    }
}
