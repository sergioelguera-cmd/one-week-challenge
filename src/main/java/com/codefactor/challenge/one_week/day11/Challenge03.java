package com.codefactor.challenge.one_week.day11;

import java.io.*;

/**
 * Challenge 03 - Object Serialization and Deserialization
 *
 * Serialize Java objects to bytes and deserialize them back.
 *
 * Key concepts:
 * - Serializable interface
 * - transient keyword (fields not serialized)
 * - serialVersionUID (version control)
 * - ObjectOutputStream / ObjectInputStream
 */
public class Challenge03 {

    static class User implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String name;
        private final String email;
        private transient String password; // NOT serialized
        private final int age;

        User(String name, String email, String password, int age) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("User{name='%s', email='%s', password='%s', age=%d}",
                name, email, password, age);
        }
    }

    /** Serialize object to file */
    public static void serialize(Object obj, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(obj);
        }
    }

    /** Deserialize object from file */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (T) ois.readObject();
        }
    }

    /** Serialize to byte array (useful for network transfer) */
    public static byte[] toBytes(Object obj) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            return baos.toByteArray();
        }
    }

    /** Deserialize from byte array */
    @SuppressWarnings("unchecked")
    public static <T> T fromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return (T) ois.readObject();
        }
    }

    public static void main(String[] args) {
        String filePath = "user.ser";
        try {
            User original = new User("Alice", "alice@mail.com", "secret123", 30);
            System.out.println("Original:     " + original);

            // File-based serialization
            serialize(original, filePath);
            User deserialized = deserialize(filePath);
            System.out.println("Deserialized: " + deserialized);
            System.out.println("(Note: password is null - transient field)");

            // Byte array serialization
            byte[] bytes = toBytes(original);
            System.out.println("\nSerialized size: " + bytes.length + " bytes");
            User fromBytes = fromBytes(bytes);
            System.out.println("From bytes:   " + fromBytes);

            // Cleanup
            new File(filePath).delete();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
