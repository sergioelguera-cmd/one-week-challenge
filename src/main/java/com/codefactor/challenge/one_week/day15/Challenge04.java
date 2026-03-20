package com.codefactor.challenge.one_week.day15;

/**
 * Challenge 04 - Exception Chaining and Custom Exception Hierarchy
 *
 * Build a proper exception hierarchy for an application.
 * Demonstrate exception chaining (cause), suppressed exceptions.
 *
 * Best practice: Create an application-specific base exception.
 */
public class Challenge04 {

    /** Base application exception */
    static class AppException extends Exception {
        private final String errorCode;

        AppException(String errorCode, String message) {
            super(message);
            this.errorCode = errorCode;
        }

        AppException(String errorCode, String message, Throwable cause) {
            super(message, cause);
            this.errorCode = errorCode;
        }

        public String getErrorCode() { return errorCode; }
    }

    /** Specific exceptions extending base */
    static class ValidationException extends AppException {
        ValidationException(String field, String message) {
            super("VALIDATION_ERROR", "Field '" + field + "': " + message);
        }
    }

    static class DataAccessException extends AppException {
        DataAccessException(String message, Throwable cause) {
            super("DATA_ACCESS_ERROR", message, cause);
        }
    }

    static class ServiceException extends AppException {
        ServiceException(String message, Throwable cause) {
            super("SERVICE_ERROR", message, cause);
        }
    }

    /** Simulated service layer showing exception chaining */
    static class UserService {
        public void createUser(String name, String email) throws ServiceException {
            try {
                validate(name, email);
                saveToDatabase(name, email);
            } catch (ValidationException e) {
                throw new ServiceException("Failed to create user", e);
            } catch (DataAccessException e) {
                throw new ServiceException("Failed to persist user", e);
            }
        }

        private void validate(String name, String email) throws ValidationException {
            if (name == null || name.isEmpty()) {
                throw new ValidationException("name", "must not be empty");
            }
            if (email == null || !email.contains("@")) {
                throw new ValidationException("email", "invalid format");
            }
        }

        private void saveToDatabase(String name, String email) throws DataAccessException {
            try {
                // Simulate database error
                throw new java.sql.SQLException("Connection refused");
            } catch (java.sql.SQLException e) {
                throw new DataAccessException("Cannot save user to DB", e);
            }
        }
    }

    public static void main(String[] args) {
        UserService service = new UserService();

        // Validation error chain
        System.out.println("=== Validation Error ===");
        try {
            service.createUser("", "test@email.com");
        } catch (ServiceException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Code:  " + e.getErrorCode());
            System.out.println("Cause: " + e.getCause().getMessage());
        }

        // Database error chain
        System.out.println("\n=== Database Error Chain ===");
        try {
            service.createUser("Alice", "alice@email.com");
        } catch (ServiceException e) {
            System.out.println("Error: " + e.getMessage());
            Throwable cause = e.getCause();
            while (cause != null) {
                System.out.println("  Caused by: " + cause.getClass().getSimpleName()
                    + ": " + cause.getMessage());
                cause = cause.getCause();
            }
        }
    }
}
