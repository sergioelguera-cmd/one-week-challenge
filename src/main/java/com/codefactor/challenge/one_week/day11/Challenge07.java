package com.codefactor.challenge.one_week.day11;

import java.io.*;
import java.nio.file.*;

/**
 * Challenge 07 - JSON File Reading and Writing (Manual)
 *
 * Read and write simple JSON without external libraries.
 * In real projects use Jackson or Gson.
 *
 * Demonstrates manual JSON generation for interview scenarios
 * where external libs may not be available.
 */
public class Challenge07 {

    /** Simple JSON builder */
    static class JsonBuilder {
        private final StringBuilder sb = new StringBuilder();
        private boolean firstField = true;

        public JsonBuilder startObject() { sb.append("{"); firstField = true; return this; }
        public JsonBuilder endObject() { sb.append("}"); return this; }
        public JsonBuilder startArray() { sb.append("["); firstField = true; return this; }
        public JsonBuilder endArray() { sb.append("]"); return this; }

        public JsonBuilder field(String key, String value) {
            addComma();
            sb.append("\"").append(key).append("\":\"").append(escapeJson(value)).append("\"");
            return this;
        }

        public JsonBuilder field(String key, int value) {
            addComma();
            sb.append("\"").append(key).append("\":").append(value);
            return this;
        }

        public JsonBuilder field(String key, double value) {
            addComma();
            sb.append("\"").append(key).append("\":").append(value);
            return this;
        }

        public JsonBuilder field(String key, boolean value) {
            addComma();
            sb.append("\"").append(key).append("\":").append(value);
            return this;
        }

        private void addComma() {
            if (!firstField) sb.append(",");
            firstField = false;
        }

        private String escapeJson(String s) {
            return s.replace("\\", "\\\\").replace("\"", "\\\"")
                    .replace("\n", "\\n").replace("\t", "\\t");
        }

        public String build() { return sb.toString(); }
    }

    /** Write JSON to file */
    public static void writeJsonFile(String filePath) throws IOException {
        JsonBuilder json = new JsonBuilder();
        String content = json.startObject()
            .field("name", "Alice")
            .field("age", 30)
            .field("salary", 95000.50)
            .field("active", true)
            .endObject()
            .build();

        Files.writeString(Path.of(filePath), content);
        System.out.println("Written JSON: " + content);
    }

    /** Read JSON file */
    public static String readJsonFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    /** Simple JSON value extractor (for demo - use a proper library in production) */
    public static String extractValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) return null;

        int valueStart = keyIndex + searchKey.length();
        if (json.charAt(valueStart) == '"') {
            int valueEnd = json.indexOf('"', valueStart + 1);
            return json.substring(valueStart + 1, valueEnd);
        } else {
            int valueEnd = valueStart;
            while (valueEnd < json.length() && json.charAt(valueEnd) != ',' && json.charAt(valueEnd) != '}') {
                valueEnd++;
            }
            return json.substring(valueStart, valueEnd);
        }
    }

    public static void main(String[] args) {
        String filePath = "test.json";
        try {
            writeJsonFile(filePath);
            String json = readJsonFile(filePath);
            System.out.println("Read: " + json);
            System.out.println("Name: " + extractValue(json, "name"));
            System.out.println("Age:  " + extractValue(json, "age"));

            new File(filePath).delete();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
