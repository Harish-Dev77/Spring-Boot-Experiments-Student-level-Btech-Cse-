package com.example.status;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) { super(message); }
}
