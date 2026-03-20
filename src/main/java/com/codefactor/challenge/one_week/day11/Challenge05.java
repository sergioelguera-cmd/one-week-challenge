package com.codefactor.challenge.one_week.day11;

import java.io.*;
import java.util.Properties;

/**
 * Challenge 05 - Properties File Reading and Writing
 *
 * Read and write Java Properties files.
 * Common for application configuration.
 *
 * Also demonstrates loading properties from classpath.
 */
public class Challenge05 {

    /** Write properties to file */
    public static void writeProperties(String filePath) throws IOException {
        Properties props = new Properties();
        props.setProperty("db.host", "localhost");
        props.setProperty("db.port", "5432");
        props.setProperty("db.name", "mydb");
        props.setProperty("db.username", "admin");
        props.setProperty("app.debug", "true");
        props.setProperty("app.maxConnections", "10");

        try (OutputStream os = new FileOutputStream(filePath)) {
            props.store(os, "Application Configuration");
        }
    }

    /** Read properties from file */
    public static Properties readProperties(String filePath) throws IOException {
        Properties props = new Properties();
        try (InputStream is = new FileInputStream(filePath)) {
            props.load(is);
        }
        return props;
    }

    /** Get property with default value */
    public static String getProperty(Properties props, String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    /** Type-safe property access */
    public static int getIntProperty(Properties props, String key, int defaultValue) {
        String value = props.getProperty(key);
        if (value == null) return defaultValue;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean getBoolProperty(Properties props, String key, boolean defaultValue) {
        String value = props.getProperty(key);
        if (value == null) return defaultValue;
        return Boolean.parseBoolean(value);
    }

    public static void main(String[] args) {
        String filePath = "app.properties";
        try {
            writeProperties(filePath);
            System.out.println("Properties written.");

            Properties props = readProperties(filePath);
            System.out.println("\nAll properties:");
            props.forEach((k, v) -> System.out.println("  " + k + " = " + v));

            System.out.println("\nTyped access:");
            System.out.println("  db.host: " + getProperty(props, "db.host", "unknown"));
            System.out.println("  db.port: " + getIntProperty(props, "db.port", 3306));
            System.out.println("  app.debug: " + getBoolProperty(props, "app.debug", false));
            System.out.println("  missing: " + getProperty(props, "missing.key", "DEFAULT"));

            new File(filePath).delete();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
