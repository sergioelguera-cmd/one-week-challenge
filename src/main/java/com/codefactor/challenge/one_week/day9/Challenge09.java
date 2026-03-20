package com.codefactor.challenge.one_week.day9;

/**
 * Challenge 09 - Equals, HashCode, and Comparable Contract
 *
 * Properly implement equals(), hashCode(), and Comparable.
 * Common interview question: Why must you override both equals and hashCode?
 *
 * Contract:
 * - If a.equals(b), then a.hashCode() == b.hashCode()
 * - If hashCode differs, objects are NOT equal
 * - equals must be reflexive, symmetric, transitive, consistent
 */
public class Challenge09 {

    static class Employee implements Comparable<Employee> {
        private final int id;
        private final String name;
        private final String department;

        Employee(int id, String name, String department) {
            this.id = id;
            this.name = name;
            this.department = department;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return id == employee.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }

        @Override
        public int compareTo(Employee other) {
            return Integer.compare(this.id, other.id);
        }

        @Override
        public String toString() {
            return "Employee{id=" + id + ", name='" + name + "', dept='" + department + "'}";
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee(1, "Alice", "Engineering");
        Employee e2 = new Employee(1, "Alice", "Engineering");
        Employee e3 = new Employee(2, "Bob", "Marketing");

        // equals contract
        System.out.println("=== Equals Contract ===");
        System.out.println("e1.equals(e1): " + e1.equals(e1));       // reflexive
        System.out.println("e1.equals(e2): " + e1.equals(e2));       // symmetric
        System.out.println("e1.equals(e3): " + e1.equals(e3));
        System.out.println("e1.equals(null): " + e1.equals(null));

        // hashCode contract
        System.out.println("\n=== HashCode Contract ===");
        System.out.println("e1.hashCode(): " + e1.hashCode());
        System.out.println("e2.hashCode(): " + e2.hashCode());
        System.out.println("Same hash (e1==e2): " + (e1.hashCode() == e2.hashCode()));

        // HashMap behavior
        System.out.println("\n=== HashMap Behavior ===");
        java.util.Map<Employee, String> map = new java.util.HashMap<>();
        map.put(e1, "Team Lead");
        System.out.println("Get by e1: " + map.get(e1));
        System.out.println("Get by e2: " + map.get(e2)); // works because equals+hashCode

        // Comparable - natural ordering
        System.out.println("\n=== Comparable ===");
        java.util.List<Employee> list = new java.util.ArrayList<>();
        list.add(e3);
        list.add(e1);
        java.util.Collections.sort(list);
        System.out.println("Sorted: " + list);
    }
}
