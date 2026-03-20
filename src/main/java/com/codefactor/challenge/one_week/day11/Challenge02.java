package com.codefactor.challenge.one_week.day11;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;


/**
 * Challenge 02 - NIO.2 File Operations (java.nio.file)
 *
 * Modern file I/O using java.nio.file.Files and Path.
 * Preferred over classic java.io for most use cases.
 *
 * Key classes: Path, Paths, Files
 */
public class Challenge02 {
    private static final MeterRegistry registry = new SimpleMeterRegistry();

    public static void main(String[] args) throws IOException {
        Path testDir = Paths.get("files-test");
        Path testFile = testDir.resolve("equity_bo_euro.px.4441715.20200424");

        try {
            // Create directories
            Files.getFileStore(testDir);
            //Files.createDirectories(testDir);
            //System.out.println("Directory created: " + testDir);

            // Write file (one-liner)
            //Files.writeString(testFile, "Hello NIO\nJava is great\nThird line");
           // System.out.println("File written: " + testFile);

            // Read entire file as string
           // String content = Files.readString(testFile);
           // System.out.println("Content:\n" + content);

            // Read all lines
            //List<String> lines = Files.readAllLines(testFile);
            //System.out.println("\nLines: " + lines);
            long lineCount;
            // Stream lines (lazy, good for large files)
            System.out.println("\nStreaming lines:");
            Timer.Sample sample = Timer.start(registry);
            //readFileFast(testFile);
            readFileStream(testFile);
//            try (Stream<String> stream = Files.lines(testFile)) {
//                lineCount =  stream.parallel()
//                            .peek(line -> System.out.println( line))
//                            .count();
//            }
            // File and time metadata by meter registry
            sample.stop(Timer.builder("file.read.time")
                    .description("Time spent streaming a file")
                    .tag("method", "files-lines")
                    .tag("result", "success")
                    .register(registry));

            //registry.summary("file.read.lines").record(lineCount);

            Timer timer = registry.find("file.read.time").timer();
            if (timer != null) {
                System.out.println("=============== Micrometer Summary ===============");
                //System.out.println("Lines read: " + lineCount);
                System.out.println("Read time (ms): " + timer.totalTime(java.util.concurrent.TimeUnit.MILLISECONDS));
                System.out.println("Read count: " + timer.count());
            }

            // File metadata
            System.out.println("\nFile size: " + Files.size(testFile) + " bytes");
            System.out.println("Exists: " + Files.exists(testFile));
            System.out.println("Is regular file: " + Files.isRegularFile(testFile));

            // Copy file
            Path copyFile = testDir.resolve("copy.txt");
            Files.copy(testFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied to: " + copyFile);

            // List directory contents
            System.out.println("\nDirectory listing:");
            try (var dirStream = Files.list(testDir)) {
                dirStream.forEach(p -> System.out.println("  " + p.getFileName()));
            }

        } finally {
            // Cleanup
            if (Files.exists(testDir)) {
                try (var walk = Files.walk(testDir)) {
                    walk.sorted(java.util.Comparator.reverseOrder())
                        .forEach(p -> { try { Files.delete(p); } catch (IOException e) { /* ignore */ } });
                }
            }
            System.out.println("\nCleaned up test directory.");
        }
    }

    private static long readFileStream(Path testFile) throws IOException {
        long lineCount = 0L;
            try (Stream<String> stream = Files.lines(testFile)) {
                lineCount =  stream.parallel()
                            .peek(line -> System.out.println( line))
                            .count();
            }
            return lineCount;

    }
    private static long readFileFast(Path file) throws IOException {
        long totalBytes = 0L;
        byte[] buffer = new byte[64 * 1024];
        StringBuilder lineBuffer = new StringBuilder();

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file.toFile()), buffer.length)) {
            int read;
            while ((read = in.read(buffer)) != -1) {
                totalBytes += read;

                String chunk = new String(buffer, 0, read, StandardCharsets.UTF_8);
                lineBuffer.append(chunk);

                int newlineIndex;
                while ((newlineIndex = lineBuffer.indexOf("\n")) >= 0) {
                    String line = lineBuffer.substring(0, newlineIndex);
                    System.out.println(line.replace("\r", ""));
                    lineBuffer.delete(0, newlineIndex + 1);
                }
            }
        }

        if (lineBuffer.length() > 0) {
            System.out.println(lineBuffer.toString().replace("\r", ""));
        }

        return totalBytes;
    }

}
