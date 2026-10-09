package com.example.student;

/*
 * Custom exception used when a student is not found in the database.
 */
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
