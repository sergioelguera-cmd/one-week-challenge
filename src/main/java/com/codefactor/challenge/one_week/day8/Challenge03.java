package com.codefactor.challenge.one_week.day8;

import java.util.*;
import java.util.stream.*;

/**
 * Challenge 03 - Stream Collectors: toMap, partitioningBy, summarizing
 *
 * Advanced Collectors usage including:
 * - toMap with merge function
 * - partitioningBy (true/false split)
 * - summarizingInt/Double
 * - toUnmodifiableList
 */
public class Challenge03 {

    record Student(String name, int grade, boolean honors) {}

    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Alice", 95, true),
            new Student("Bob", 82, false),
            new Student("Charlie", 91, true),
            new Student("Diana", 78, false),
            new Student("Eve", 88, true),
            new Student("Frank", 65, false)
        );

        // toMap: name -> grade
        Map<String, Integer> nameToGrade = students.stream()
            .collect(Collectors.toMap(Student::name, Student::grade));
        System.out.println("toMap: " + nameToGrade);

        // partitioningBy: honors vs non-honors
        Map<Boolean, List<Student>> partitioned = students.stream()
            .collect(Collectors.partitioningBy(Student::honors));
        System.out.println("\nPartitioned by honors:");
        System.out.println("  Honors:     " + partitioned.get(true).stream()
            .map(Student::name).collect(Collectors.toList()));
        System.out.println("  Non-honors: " + partitioned.get(false).stream()
            .map(Student::name).collect(Collectors.toList()));

        // summarizingInt: statistics on grades
        IntSummaryStatistics stats = students.stream()
            .collect(Collectors.summarizingInt(Student::grade));
        System.out.println("\nGrade Statistics:");
        System.out.println("  Count:   " + stats.getCount());
        System.out.println("  Min:     " + stats.getMin());
        System.out.println("  Max:     " + stats.getMax());
        System.out.println("  Average: " + stats.getAverage());
        System.out.println("  Sum:     " + stats.getSum());

        // counting per partition
        Map<Boolean, Long> countByHonors = students.stream()
            .collect(Collectors.partitioningBy(Student::honors, Collectors.counting()));
        System.out.println("\nCount by honors: " + countByHonors);

        // collectingAndThen: collect then transform
        int highestGrade = students.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(Student::grade)),
                opt -> opt.map(Student::grade).orElse(0)
            ));
        System.out.println("Highest grade: " + highestGrade);
    }
}
