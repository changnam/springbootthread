package com.honsoft.exception;

public class BlogNotFoundException extends RuntimeException {
    private String message;
    public BlogNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public BlogNotFoundException() {
    }
}
