package com.codefactor.challenge.one_week.day14;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Challenge 02 - Java Date/Time API (java.time)
 *
 * Modern date/time handling introduced in Java 8.
 * Replaces old Date/Calendar classes.
 *
 * Key classes: LocalDate, LocalTime, LocalDateTime, ZonedDateTime,
 *              Duration, Period, Instant
 */
public class Challenge02 {

    public static void main(String[] args) {
        // LocalDate (date without time)
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1990, Month.JUNE, 15);
        System.out.println("Today: " + today);
        System.out.println("Birthday: " + birthday);
        System.out.println("Day of week: " + today.getDayOfWeek());

        // Period between dates
        Period age = Period.between(birthday, today);
        System.out.println("Age: " + age.getYears() + " years");

        // Date arithmetic
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate lastMonth = today.minusMonths(1);
        System.out.println("Next week: " + nextWeek);
        System.out.println("Last month: " + lastMonth);

        // LocalTime (time without date)
        LocalTime now = LocalTime.now();
        LocalTime meeting = LocalTime.of(14, 30);
        System.out.println("\nTime now: " + now);
        System.out.println("Meeting: " + meeting);
        System.out.println("Until meeting: " + ChronoUnit.MINUTES.between(now, meeting) + " min");

        // LocalDateTime
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("\nDateTime: " + dateTime);

        // Formatting
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        System.out.println("Formatted: " + dateTime.format(fmt));

        // Parsing
        LocalDate parsed = LocalDate.parse("2024-12-25");
        System.out.println("Parsed: " + parsed);

        // ZonedDateTime
        ZonedDateTime zoned = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("\nNY time: " + zoned);

        // Instant (machine timestamp)
        Instant instant = Instant.now();
        System.out.println("Instant: " + instant);
        System.out.println("Epoch seconds: " + instant.getEpochSecond());

        // Duration
        Duration d = Duration.between(LocalTime.of(9, 0), LocalTime.of(17, 30));
        System.out.println("\nWorkday: " + d.toHours() + "h " + (d.toMinutes() % 60) + "m");
    }
}
