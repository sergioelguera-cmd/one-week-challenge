package com.codefactor.challenge.one_week;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Interview Challenge Index
 *
 * A searchable index of all 100 Java interview coding challenges.
 * Run this class to browse, search, and find challenges by category, difficulty, or keyword.
 *
 * Categories:
 *   day5  - Strings (10 challenges)
 *   day6  - Arrays (10 challenges)
 *   day7  - Collections & Maps (10 challenges)
 *   day8  - Streams (10 challenges)
 *   day9  - OOP & Design Patterns (10 challenges)
 *   day10 - Multithreading (10 challenges)
 *   day11 - File I/O (8 challenges)
 *   day12 - Data Structures (10 challenges)
 *   day13 - Algorithms (10 challenges)
 *   day14 - Java 8+ Features (7 challenges)
 *   day15 - Exception Handling & Generics (5 challenges)
 */
public class InterviewIndex {

    static class Challenge {
        final int id;
        final String day;
        final String category;
        final String title;
        final String difficulty;
        final String className;
        final String description;
        final List<String> tags;

        Challenge(int id, String day, String category, String title,
                  String difficulty, String className, String description, String... tags) {
            this.id = id;
            this.day = day;
            this.category = category;
            this.title = title;
            this.difficulty = difficulty;
            this.className = className;
            this.description = description;
            this.tags = Arrays.asList(tags);
        }

        @Override
        public String toString() {
            return String.format("#%03d [%-6s] %-12s %-45s %-8s %s",
                    id, difficulty, day, title, category, className);
        }
    }

    private static final List<Challenge> CHALLENGES = new ArrayList<>();

    static {
        // ===== DAY 5: STRINGS =====
        CHALLENGES.add(new Challenge(1, "day5", "Strings", "Reverse a String", "Easy",
                "day5.Challenge01", "Reverse a string without StringBuilder.reverse(). Iterative and stream approaches.",
                "string", "reverse", "array", "stream"));
        CHALLENGES.add(new Challenge(2, "day5", "Strings", "Check Palindrome", "Easy",
                "day5.Challenge02", "Check if a string is a palindrome. Case insensitive, ignore non-alphanumeric.",
                "string", "palindrome", "two-pointer"));
        CHALLENGES.add(new Challenge(3, "day5", "Strings", "Count Character Occurrences", "Easy",
                "day5.Challenge03", "Count occurrences of each character using HashMap and streams.",
                "string", "hashmap", "frequency", "stream"));
        CHALLENGES.add(new Challenge(4, "day5", "Strings", "Check Anagram", "Easy",
                "day5.Challenge04", "Check if two strings are anagrams. Sorting and frequency-count approaches.",
                "string", "anagram", "sorting", "hashmap"));
        CHALLENGES.add(new Challenge(5, "day5", "Strings", "First Non-Repeated Character", "Easy",
                "day5.Challenge05", "Find first non-repeated character using LinkedHashMap.",
                "string", "linkedhashmap", "frequency"));
        CHALLENGES.add(new Challenge(6, "day5", "Strings", "Remove Duplicates from String", "Easy",
                "day5.Challenge06", "Remove duplicate characters preserving order. Set and Stream approaches.",
                "string", "set", "duplicates", "stream"));
        CHALLENGES.add(new Challenge(7, "day5", "Strings", "String Contains Only Digits", "Easy",
                "day5.Challenge07", "Check if string contains only digits. Regex, Character.isDigit, stream.",
                "string", "regex", "validation"));
        CHALLENGES.add(new Challenge(8, "day5", "Strings", "Reverse Words in Sentence", "Easy",
                "day5.Challenge08", "Reverse word order in a sentence. Split/join and stream approaches.",
                "string", "reverse", "split", "stream"));
        CHALLENGES.add(new Challenge(9, "day5", "Strings", "String Compression", "Medium",
                "day5.Challenge09", "Basic string compression: aabcccccaaa -> a2b1c5a3.",
                "string", "compression", "run-length"));
        CHALLENGES.add(new Challenge(10, "day5", "Strings", "String Rotation Check", "Medium",
                "day5.Challenge10", "Check if one string is a rotation of another using concatenation trick.",
                "string", "rotation", "contains"));

        // ===== DAY 6: ARRAYS =====
        CHALLENGES.add(new Challenge(11, "day6", "Arrays", "Find Second Largest Element", "Easy",
                "day6.Challenge01", "Find second largest element without sorting.",
                "array", "max", "second-largest"));
        CHALLENGES.add(new Challenge(12, "day6", "Arrays", "Rotate Array", "Medium",
                "day6.Challenge02", "Rotate array by k positions. In-place reversal approach.",
                "array", "rotation", "in-place"));
        CHALLENGES.add(new Challenge(13, "day6", "Arrays", "Find Missing Number", "Easy",
                "day6.Challenge03", "Find missing number in 1-n array. Sum formula and XOR approaches.",
                "array", "missing", "xor", "math"));
        CHALLENGES.add(new Challenge(14, "day6", "Arrays", "Merge Two Sorted Arrays", "Easy",
                "day6.Challenge04", "Merge two sorted arrays. O(n+m) two-pointer approach.",
                "array", "merge", "sorted", "two-pointer"));
        CHALLENGES.add(new Challenge(15, "day6", "Arrays", "Find Duplicate Elements", "Easy",
                "day6.Challenge05", "Find duplicates using HashSet and streams.",
                "array", "duplicates", "set", "stream"));
        CHALLENGES.add(new Challenge(16, "day6", "Arrays", "Two Sum Problem", "Medium",
                "day6.Challenge06", "Find two indices summing to target. HashMap O(n) approach.",
                "array", "two-sum", "hashmap"));
        CHALLENGES.add(new Challenge(17, "day6", "Arrays", "Move Zeros to End", "Easy",
                "day6.Challenge07", "Move zeros to end maintaining order. In-place.",
                "array", "in-place", "zeros"));
        CHALLENGES.add(new Challenge(18, "day6", "Arrays", "Intersection of Two Arrays", "Easy",
                "day6.Challenge08", "Find common elements. Set and stream approaches.",
                "array", "intersection", "set", "stream"));
        CHALLENGES.add(new Challenge(19, "day6", "Arrays", "Maximum Subarray Sum (Kadane's)", "Medium",
                "day6.Challenge09", "Kadane's algorithm for max contiguous subarray sum.",
                "array", "kadane", "dynamic-programming", "subarray"));
        CHALLENGES.add(new Challenge(20, "day6", "Arrays", "Dutch National Flag (Sort 0,1,2)", "Medium",
                "day6.Challenge10", "Sort array of 0,1,2 in one pass using three pointers.",
                "array", "sorting", "three-pointer", "dutch-flag"));

        // ===== DAY 7: COLLECTIONS & MAPS =====
        CHALLENGES.add(new Challenge(21, "day7", "Collections", "Word Frequency Counter", "Easy",
                "day7.Challenge01", "Count word frequency using HashMap. Sort by frequency.",
                "hashmap", "frequency", "sorting"));
        CHALLENGES.add(new Challenge(22, "day7", "Collections", "First Duplicate Using Set", "Easy",
                "day7.Challenge02", "Find first duplicate using HashSet.",
                "set", "duplicates"));
        CHALLENGES.add(new Challenge(23, "day7", "Collections", "Sort HashMap by Values", "Medium",
                "day7.Challenge03", "Sort HashMap by values ascending and descending.",
                "hashmap", "sorting", "comparator"));
        CHALLENGES.add(new Challenge(24, "day7", "Collections", "LRU Cache (LinkedHashMap)", "Medium",
                "day7.Challenge04", "LRU cache using LinkedHashMap with removeEldestEntry.",
                "linkedhashmap", "cache", "lru", "design"));
        CHALLENGES.add(new Challenge(25, "day7", "Collections", "Group Anagrams", "Medium",
                "day7.Challenge05", "Group strings into anagram groups using HashMap.",
                "hashmap", "anagram", "grouping"));
        CHALLENGES.add(new Challenge(26, "day7", "Collections", "Find Pairs With Given Sum", "Easy",
                "day7.Challenge06", "Find all pairs summing to target using HashSet.",
                "set", "pairs", "sum"));
        CHALLENGES.add(new Challenge(27, "day7", "Collections", "Stack Using Queue", "Medium",
                "day7.Challenge07", "Implement stack using Queue operations.",
                "stack", "queue", "design"));
        CHALLENGES.add(new Challenge(28, "day7", "Collections", "Queue Using Stacks", "Medium",
                "day7.Challenge08", "Implement queue using two stacks.",
                "queue", "stack", "design"));
        CHALLENGES.add(new Challenge(29, "day7", "Collections", "Top K Frequent Elements", "Medium",
                "day7.Challenge09", "Find k most frequent elements. HashMap + PriorityQueue.",
                "hashmap", "priority-queue", "frequency", "top-k"));
        CHALLENGES.add(new Challenge(30, "day7", "Collections", "Merge Overlapping Intervals", "Medium",
                "day7.Challenge10", "Merge overlapping intervals. Sort + linear scan.",
                "intervals", "sorting", "merge"));

        // ===== DAY 8: STREAMS =====
        CHALLENGES.add(new Challenge(31, "day8", "Streams", "Filter Employees by Salary", "Easy",
                "day8.Challenge01", "Filter employees with salary > threshold using streams.",
                "stream", "filter", "employee"));
        CHALLENGES.add(new Challenge(32, "day8", "Streams", "Find Max/Min in List", "Easy",
                "day8.Challenge02", "Find max/min using reduce() and max()/min().",
                "stream", "reduce", "max", "min"));
        CHALLENGES.add(new Challenge(33, "day8", "Streams", "Group by Department", "Medium",
                "day8.Challenge03", "Group employees by department using Collectors.groupingBy.",
                "stream", "grouping", "collectors"));
        CHALLENGES.add(new Challenge(34, "day8", "Streams", "FlatMap Example", "Medium",
                "day8.Challenge04", "Flatten list of lists. Extract unique words from sentences.",
                "stream", "flatmap", "flatten"));
        CHALLENGES.add(new Challenge(35, "day8", "Streams", "Partition By Example", "Easy",
                "day8.Challenge05", "Collectors.partitioningBy to split into two groups.",
                "stream", "partition", "collectors"));
        CHALLENGES.add(new Challenge(36, "day8", "Streams", "Stream Reduce Operation", "Medium",
                "day8.Challenge06", "Reduce for sum, product, string concat, custom comparator.",
                "stream", "reduce", "aggregation"));
        CHALLENGES.add(new Challenge(37, "day8", "Streams", "Convert List to Map", "Medium",
                "day8.Challenge07", "Collectors.toMap with duplicate key handling.",
                "stream", "collectors", "map"));
        CHALLENGES.add(new Challenge(38, "day8", "Streams", "Find Duplicates with Streams", "Medium",
                "day8.Challenge08", "Find duplicates using groupingBy and filtering.",
                "stream", "duplicates", "grouping"));
        CHALLENGES.add(new Challenge(39, "day8", "Streams", "Custom Object Sorting", "Easy",
                "day8.Challenge09", "Sort by multiple fields: Comparator.comparing + thenComparing.",
                "stream", "sorting", "comparator"));
        CHALLENGES.add(new Challenge(40, "day8", "Streams", "Parallel Streams", "Medium",
                "day8.Challenge10", "Parallel streams performance and thread safety.",
                "stream", "parallel", "performance", "concurrency"));

        // ===== DAY 9: OOP & DESIGN PATTERNS =====
        CHALLENGES.add(new Challenge(41, "day9", "OOP", "Singleton Pattern", "Medium",
                "day9.Challenge01", "Thread-safe Singleton: eager, lazy synchronized, double-checked, enum.",
                "pattern", "singleton", "thread-safe"));
        CHALLENGES.add(new Challenge(42, "day9", "OOP", "Factory Pattern", "Medium",
                "day9.Challenge02", "Factory pattern with Shape hierarchy.",
                "pattern", "factory", "polymorphism"));
        CHALLENGES.add(new Challenge(43, "day9", "OOP", "Builder Pattern", "Medium",
                "day9.Challenge03", "Builder pattern for complex objects with optional params.",
                "pattern", "builder", "fluent-api"));
        CHALLENGES.add(new Challenge(44, "day9", "OOP", "Observer Pattern", "Medium",
                "day9.Challenge04", "Observer pattern with EventManager and listeners.",
                "pattern", "observer", "event"));
        CHALLENGES.add(new Challenge(45, "day9", "OOP", "Strategy Pattern", "Medium",
                "day9.Challenge05", "Strategy pattern for runtime behavior switching.",
                "pattern", "strategy", "polymorphism"));
        CHALLENGES.add(new Challenge(46, "day9", "OOP", "Immutable Class", "Medium",
                "day9.Challenge06", "Proper immutable class: final, defensive copies.",
                "immutable", "defensive-copy", "final"));
        CHALLENGES.add(new Challenge(47, "day9", "OOP", "Deep Copy vs Shallow Copy", "Medium",
                "day9.Challenge07", "Deep vs shallow copy. Cloneable pitfalls.",
                "copy", "clone", "deep-copy", "shallow-copy"));
        CHALLENGES.add(new Challenge(48, "day9", "OOP", "Comparable vs Comparator", "Easy",
                "day9.Challenge08", "Natural ordering vs custom ordering for Student class.",
                "comparable", "comparator", "sorting"));
        CHALLENGES.add(new Challenge(49, "day9", "OOP", "Abstract Class vs Interface", "Easy",
                "day9.Challenge09", "When to use abstract class vs interface. Default methods.",
                "abstract", "interface", "default-method"));
        CHALLENGES.add(new Challenge(50, "day9", "OOP", "Enum with Behavior", "Medium",
                "day9.Challenge10", "Enum with fields, constructor, methods, abstract methods.",
                "enum", "behavior", "abstract-method"));

        // ===== DAY 10: MULTITHREADING =====
        CHALLENGES.add(new Challenge(51, "day10", "Threading", "Thread via Runnable vs Thread", "Easy",
                "day10.Challenge01", "Creating threads: extending Thread vs implementing Runnable.",
                "thread", "runnable", "basics"));
        CHALLENGES.add(new Challenge(52, "day10", "Threading", "Producer-Consumer Problem", "Hard",
                "day10.Challenge02", "Producer-consumer with wait()/notify() and bounded buffer.",
                "thread", "producer-consumer", "wait-notify", "synchronization"));
        CHALLENGES.add(new Challenge(53, "day10", "Threading", "Deadlock Example & Prevention", "Hard",
                "day10.Challenge03", "Create deadlock scenario. Prevent with lock ordering.",
                "thread", "deadlock", "lock-ordering"));
        CHALLENGES.add(new Challenge(54, "day10", "Threading", "Thread-Safe Singleton", "Medium",
                "day10.Challenge04", "Double-checked locking singleton with concurrent access.",
                "thread", "singleton", "double-checked-locking"));
        CHALLENGES.add(new Challenge(55, "day10", "Threading", "ExecutorService Example", "Medium",
                "day10.Challenge05", "Fixed thread pool, submit tasks, Future results, shutdown.",
                "thread", "executor", "future", "thread-pool"));
        CHALLENGES.add(new Challenge(56, "day10", "Threading", "CompletableFuture Example", "Medium",
                "day10.Challenge06", "Async: supplyAsync, thenApply, thenCombine, exceptionally.",
                "thread", "completable-future", "async"));
        CHALLENGES.add(new Challenge(57, "day10", "Threading", "CountDownLatch Example", "Medium",
                "day10.Challenge07", "CountDownLatch for waiting on multiple threads.",
                "thread", "countdown-latch", "synchronization"));
        CHALLENGES.add(new Challenge(58, "day10", "Threading", "ReadWriteLock Example", "Medium",
                "day10.Challenge08", "ReentrantReadWriteLock for concurrent reads, exclusive writes.",
                "thread", "read-write-lock", "concurrency"));
        CHALLENGES.add(new Challenge(59, "day10", "Threading", "Synchronized vs ReentrantLock", "Medium",
                "day10.Challenge09", "Compare synchronized vs ReentrantLock. tryLock, fairness.",
                "thread", "synchronized", "reentrant-lock"));
        CHALLENGES.add(new Challenge(60, "day10", "Threading", "Thread Pool with Custom Tasks", "Medium",
                "day10.Challenge10", "Custom thread pool, batch task processing, result collection.",
                "thread", "thread-pool", "executor", "callable"));

        // ===== DAY 11: FILE I/O =====
        CHALLENGES.add(new Challenge(61, "day11", "File I/O", "Read File Line by Line", "Easy",
                "day11.Challenge01", "BufferedReader, Scanner, Files.readAllLines with try-with-resources.",
                "file", "read", "buffered-reader", "nio"));
        CHALLENGES.add(new Challenge(62, "day11", "File I/O", "Write to File", "Easy",
                "day11.Challenge02", "BufferedWriter, PrintWriter, Files.write. Append vs overwrite.",
                "file", "write", "buffered-writer", "nio"));
        CHALLENGES.add(new Challenge(63, "day11", "File I/O", "Read and Write CSV", "Medium",
                "day11.Challenge03", "Parse and write CSV files using string splitting.",
                "file", "csv", "parsing"));
        CHALLENGES.add(new Challenge(64, "day11", "File I/O", "Serialize/Deserialize Object", "Medium",
                "day11.Challenge04", "ObjectOutputStream/InputStream. Transient keyword effect.",
                "file", "serialization", "transient"));
        CHALLENGES.add(new Challenge(65, "day11", "File I/O", "Watch Directory for Changes", "Medium",
                "day11.Challenge05", "WatchService to monitor directory events.",
                "file", "watch-service", "nio", "events"));
        CHALLENGES.add(new Challenge(66, "day11", "File I/O", "Read Properties File", "Easy",
                "day11.Challenge06", "Properties class usage with defaults.",
                "file", "properties", "configuration"));
        CHALLENGES.add(new Challenge(67, "day11", "File I/O", "NIO File Operations", "Medium",
                "day11.Challenge07", "Files.copy, Files.move, Files.walk, Path operations.",
                "file", "nio", "path", "walk"));
        CHALLENGES.add(new Challenge(68, "day11", "File I/O", "Try-With-Resources Deep Dive", "Medium",
                "day11.Challenge08", "Multiple resources, custom AutoCloseable, suppressed exceptions.",
                "file", "try-with-resources", "autocloseable"));

        // ===== DAY 12: DATA STRUCTURES =====
        CHALLENGES.add(new Challenge(69, "day12", "DataStruct", "Implement Singly Linked List", "Medium",
                "day12.Challenge01", "Linked list from scratch: insert, delete, search, reverse.",
                "linked-list", "data-structure", "implementation"));
        CHALLENGES.add(new Challenge(70, "day12", "DataStruct", "Implement Stack", "Easy",
                "day12.Challenge02", "Stack using array: push, pop, peek, isEmpty.",
                "stack", "data-structure", "array"));
        CHALLENGES.add(new Challenge(71, "day12", "DataStruct", "Implement Queue", "Easy",
                "day12.Challenge03", "Circular queue using array: enqueue, dequeue, peek.",
                "queue", "data-structure", "circular-buffer"));
        CHALLENGES.add(new Challenge(72, "day12", "DataStruct", "Binary Search", "Easy",
                "day12.Challenge04", "Binary search iterative and recursive on sorted array.",
                "binary-search", "search", "divide-conquer"));
        CHALLENGES.add(new Challenge(73, "day12", "DataStruct", "BFS Traversal (Graph)", "Medium",
                "day12.Challenge05", "Breadth-First Search on adjacency list graph.",
                "bfs", "graph", "traversal", "queue"));
        CHALLENGES.add(new Challenge(74, "day12", "DataStruct", "DFS Traversal (Graph)", "Medium",
                "day12.Challenge06", "Depth-First Search recursive and iterative.",
                "dfs", "graph", "traversal", "stack"));
        CHALLENGES.add(new Challenge(75, "day12", "DataStruct", "Simple HashMap Implementation", "Hard",
                "day12.Challenge07", "HashMap with chaining: put, get, remove.",
                "hashmap", "data-structure", "chaining", "implementation"));
        CHALLENGES.add(new Challenge(76, "day12", "DataStruct", "Trie (Prefix Tree)", "Hard",
                "day12.Challenge08", "Trie with insert, search, startsWith.",
                "trie", "prefix-tree", "data-structure"));
        CHALLENGES.add(new Challenge(77, "day12", "DataStruct", "Min Heap / Priority Queue", "Hard",
                "day12.Challenge09", "Min heap from scratch: insert, extractMin, heapify.",
                "heap", "priority-queue", "data-structure"));
        CHALLENGES.add(new Challenge(78, "day12", "DataStruct", "Detect Cycle in Linked List", "Medium",
                "day12.Challenge10", "Floyd's tortoise and hare. Find cycle start.",
                "linked-list", "cycle-detection", "floyd"));

        // ===== DAY 13: ALGORITHMS =====
        CHALLENGES.add(new Challenge(79, "day13", "Algorithms", "Bubble Sort", "Easy",
                "day13.Challenge01", "Bubble sort with early termination optimization.",
                "sorting", "bubble-sort", "algorithm"));
        CHALLENGES.add(new Challenge(80, "day13", "Algorithms", "Quick Sort", "Medium",
                "day13.Challenge02", "Quicksort with Lomuto partition. Recursive.",
                "sorting", "quick-sort", "divide-conquer", "partition"));
        CHALLENGES.add(new Challenge(81, "day13", "Algorithms", "Merge Sort", "Medium",
                "day13.Challenge03", "Merge sort divide-and-conquer with merge operation.",
                "sorting", "merge-sort", "divide-conquer"));
        CHALLENGES.add(new Challenge(82, "day13", "Algorithms", "Binary Search Variants", "Medium",
                "day13.Challenge04", "First/last occurrence, insert position in sorted array.",
                "binary-search", "search", "variants"));
        CHALLENGES.add(new Challenge(83, "day13", "Algorithms", "Fibonacci Sequence", "Easy",
                "day13.Challenge05", "Iterative, recursive, memoized. Performance comparison.",
                "fibonacci", "recursion", "dynamic-programming", "memoization"));
        CHALLENGES.add(new Challenge(84, "day13", "Algorithms", "Factorial", "Easy",
                "day13.Challenge06", "Iterative, recursive, streams. BigInteger for large numbers.",
                "factorial", "recursion", "biginteger", "stream"));
        CHALLENGES.add(new Challenge(85, "day13", "Algorithms", "GCD and LCM", "Easy",
                "day13.Challenge07", "Euclidean algorithm for GCD. LCM derivation.",
                "gcd", "lcm", "euclidean", "math"));
        CHALLENGES.add(new Challenge(86, "day13", "Algorithms", "Check Prime Number", "Easy",
                "day13.Challenge08", "Primality check. Sieve of Eratosthenes.",
                "prime", "sieve", "math"));
        CHALLENGES.add(new Challenge(87, "day13", "Algorithms", "Power of Two", "Easy",
                "day13.Challenge09", "Loop, bit manipulation (n & n-1), Integer.bitCount.",
                "bit-manipulation", "power-of-two", "math"));
        CHALLENGES.add(new Challenge(88, "day13", "Algorithms", "FizzBuzz", "Easy",
                "day13.Challenge10", "Standard, streams, custom rules, no-if approaches.",
                "fizzbuzz", "classic", "stream"));

        // ===== DAY 14: JAVA 8+ FEATURES =====
        CHALLENGES.add(new Challenge(89, "day14", "Java8+", "Optional Usage", "Medium",
                "day14.Challenge01", "Optional: of, ofNullable, map, flatMap, orElse patterns.",
                "optional", "null-safety", "java8"));
        CHALLENGES.add(new Challenge(90, "day14", "Java8+", "Method References", "Easy",
                "day14.Challenge02", "All 4 types: static, bound instance, unbound instance, constructor.",
                "method-reference", "lambda", "java8"));
        CHALLENGES.add(new Challenge(91, "day14", "Java8+", "Functional Interfaces", "Medium",
                "day14.Challenge03", "Predicate, Function, Consumer, Supplier, custom @FunctionalInterface.",
                "functional-interface", "predicate", "function", "java8"));
        CHALLENGES.add(new Challenge(92, "day14", "Java8+", "Default Methods in Interface", "Medium",
                "day14.Challenge04", "Default and static methods. Diamond problem resolution.",
                "default-method", "interface", "diamond-problem", "java8"));
        CHALLENGES.add(new Challenge(93, "day14", "Java8+", "Lambda Expressions", "Easy",
                "day14.Challenge05", "Lambdas: Runnable, Comparator, variable capture, common patterns.",
                "lambda", "closure", "effectively-final", "java8"));
        CHALLENGES.add(new Challenge(94, "day14", "Java8+", "Date and Time API", "Medium",
                "day14.Challenge06", "LocalDate, LocalTime, ZonedDateTime, Period, Duration, formatting.",
                "date-time", "localdate", "duration", "java8"));
        CHALLENGES.add(new Challenge(95, "day14", "Java8+", "Records (Java 16+)", "Easy",
                "day14.Challenge07", "Record classes: syntax, custom constructors, vs POJO.",
                "record", "java16", "pojo"));

        // ===== DAY 15: EXCEPTION HANDLING & GENERICS =====
        CHALLENGES.add(new Challenge(96, "day15", "Generics", "Custom Exception Hierarchy", "Medium",
                "day15.Challenge01", "AppException base, ValidationException, chained exceptions.",
                "exception", "hierarchy", "custom-exception"));
        CHALLENGES.add(new Challenge(97, "day15", "Generics", "Exception Handling Best Practices", "Medium",
                "day15.Challenge02", "Multi-catch, finally, checked vs unchecked, anti-patterns.",
                "exception", "best-practices", "try-catch"));
        CHALLENGES.add(new Challenge(98, "day15", "Generics", "Generic Methods", "Medium",
                "day15.Challenge03", "Generic max/min, swap, bounded type parameters.",
                "generics", "generic-method", "bounded-type"));
        CHALLENGES.add(new Challenge(99, "day15", "Generics", "Bounded Type Parameters & Wildcards", "Hard",
                "day15.Challenge04", "Upper/lower bounded, unbounded wildcards. PECS principle.",
                "generics", "wildcard", "pecs", "bounded"));
        CHALLENGES.add(new Challenge(100, "day15", "Generics", "Generic Data Structures", "Hard",
                "day15.Challenge05", "Generic Stack<T>, Pair<K,V>. Type erasure effects.",
                "generics", "type-erasure", "data-structure"));
    }

    public static List<Challenge> searchByKeyword(String keyword) {
        String lower = keyword.toLowerCase();
        return CHALLENGES.stream()
                .filter(c -> c.title.toLowerCase().contains(lower)
                        || c.description.toLowerCase().contains(lower)
                        || c.tags.stream().anyMatch(t -> t.contains(lower))
                        || c.category.toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    public static List<Challenge> searchByCategory(String category) {
        String lower = category.toLowerCase();
        return CHALLENGES.stream()
                .filter(c -> c.category.toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    public static List<Challenge> searchByDay(String day) {
        return CHALLENGES.stream()
                .filter(c -> c.day.equalsIgnoreCase(day))
                .collect(Collectors.toList());
    }

    public static List<Challenge> searchByDifficulty(String difficulty) {
        return CHALLENGES.stream()
                .filter(c -> c.difficulty.equalsIgnoreCase(difficulty))
                .collect(Collectors.toList());
    }

    public static List<Challenge> searchByTag(String tag) {
        String lower = tag.toLowerCase();
        return CHALLENGES.stream()
                .filter(c -> c.tags.stream().anyMatch(t -> t.equals(lower)))
                .collect(Collectors.toList());
    }

    private static void printResults(String header, List<Challenge> results) {
        System.out.println("\n" + "=".repeat(110));
        System.out.println(header + " (" + results.size() + " results)");
        System.out.println("=".repeat(110));
        System.out.printf("%-5s %-8s %-12s %-45s %-12s %s%n",
                "ID", "Level", "Day", "Title", "Category", "Class");
        System.out.println("-".repeat(110));
        results.forEach(System.out::println);
    }

    public static void printAll() {
        printResults("ALL CHALLENGES", CHALLENGES);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     JAVA INTERVIEW CHALLENGE INDEX - 100 Problems       ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║  Categories:                                            ║");
        System.out.println("║    day5  - Strings (10)       day6  - Arrays (10)        ║");
        System.out.println("║    day7  - Collections (10)   day8  - Streams (10)       ║");
        System.out.println("║    day9  - OOP/Patterns (10)  day10 - Threading (10)     ║");
        System.out.println("║    day11 - File I/O (8)       day12 - Data Struct (10)   ║");
        System.out.println("║    day13 - Algorithms (10)    day14 - Java 8+ (7)        ║");
        System.out.println("║    day15 - Exceptions/Generics (5)                       ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║  Search commands:                                       ║");
        System.out.println("║    all        - Show all 100 challenges                 ║");
        System.out.println("║    day <num>  - Filter by day (e.g., day day5)          ║");
        System.out.println("║    cat <name> - Filter by category                      ║");
        System.out.println("║    level <d>  - Filter by difficulty (Easy/Medium/Hard)  ║");
        System.out.println("║    tag <name> - Filter by tag                           ║");
        System.out.println("║    <keyword>  - Free text search                        ║");
        System.out.println("║    quit       - Exit                                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        while (true) {
            System.out.print("\nSearch> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;
            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) break;

            if (input.equalsIgnoreCase("all")) {
                printAll();
            } else if (input.toLowerCase().startsWith("day ")) {
                printResults("DAY: " + input.substring(4), searchByDay(input.substring(4).trim()));
            } else if (input.toLowerCase().startsWith("cat ")) {
                printResults("CATEGORY: " + input.substring(4), searchByCategory(input.substring(4).trim()));
            } else if (input.toLowerCase().startsWith("level ")) {
                printResults("DIFFICULTY: " + input.substring(6), searchByDifficulty(input.substring(6).trim()));
            } else if (input.toLowerCase().startsWith("tag ")) {
                printResults("TAG: " + input.substring(4), searchByTag(input.substring(4).trim()));
            } else {
                printResults("SEARCH: " + input, searchByKeyword(input));
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}
