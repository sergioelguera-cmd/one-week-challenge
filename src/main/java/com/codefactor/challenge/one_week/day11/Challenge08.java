package com.codefactor.challenge.one_week.day11;

import java.sql.*;

/**
 * Challenge 08 - JDBC Database Connection Example
 *
 * Connect to a database using JDBC.
 * Demonstrates the standard JDBC pattern (without Spring).
 *
 * NOTE: This example shows the pattern. Requires a real DB driver to run.
 * For H2 in-memory DB, add h2 dependency.
 *
 * Spring Boot JPA equivalent shown in comments for comparison.
 */
public class Challenge08 {

    /** Standard JDBC connection pattern */
    public static void jdbcExample() {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        // Try-with-resources ensures connection is closed
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            // Create table
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE employees (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100), " +
                    "department VARCHAR(50), " +
                    "salary DECIMAL(10,2))");
                System.out.println("Table created.");
            }

            // Insert with PreparedStatement (prevents SQL injection)
            String insertSql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                String[][] data = {
                    {"Alice", "Engineering", "95000"},
                    {"Bob", "Marketing", "75000"},
                    {"Charlie", "Engineering", "88000"}
                };
                for (String[] row : data) {
                    ps.setString(1, row[0]);
                    ps.setString(2, row[1]);
                    ps.setDouble(3, Double.parseDouble(row[2]));
                    ps.addBatch();
                }
                ps.executeBatch();
                System.out.println("Data inserted.");
            }

            // Query with ResultSet
            String selectSql = "SELECT * FROM employees WHERE department = ?";
            try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
                ps.setString(1, "Engineering");
                try (ResultSet rs = ps.executeQuery()) {
                    System.out.println("\nEngineering employees:");
                    while (rs.next()) {
                        System.out.printf("  %d: %-10s $%.2f%n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("salary"));
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            System.out.println("(This example needs H2 database driver on classpath)");
        }
    }

    /*
     * Spring Boot JPA equivalent (for reference):
     *
     * @Entity
     * public class Employee {
     *     @Id @GeneratedValue
     *     private Long id;
     *     private String name;
     *     private String department;
     *     private double salary;
     * }
     *
     * @Repository
     * public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     *     List<Employee> findByDepartment(String department);
     *     List<Employee> findBySalaryGreaterThan(double salary);
     * }
     *
     * @Service
     * public class EmployeeService {
     *     @Autowired
     *     private EmployeeRepository repo;
     *
     *     public List<Employee> getEngineers() {
     *         return repo.findByDepartment("Engineering");
     *     }
     * }
     *
     * application.properties:
     * spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
     * spring.datasource.username=admin
     * spring.datasource.password=secret
     * spring.jpa.hibernate.ddl-auto=update
     */

    public static void main(String[] args) {
        System.out.println("=== JDBC Example ===");
        jdbcExample();
        System.out.println("\n(See comments for Spring Boot JPA equivalent)");
    }
}
