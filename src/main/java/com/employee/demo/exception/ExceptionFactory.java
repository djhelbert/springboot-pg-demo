package com.employee.demo.exception;

/**
 * Exception Factory
 */
public class ExceptionFactory {
    private ExceptionFactory() {}

    public static ResourceNotFoundException taskNotFound(Long id) {
        return new ResourceNotFoundException("Task Not Found: " + id);
    }

    public static ResourceNotFoundException employeeNotFound(Long id) {
        return new ResourceNotFoundException("Employee Not Found: " + id);
    }

    public static ResourceNotFoundException projectNotFound(Long id) {
        return new ResourceNotFoundException("Project Not Found: " + id);
    }
}
