package com.honsoft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No blog found message annotated with responsestatus")
public class BlogNotFoundException extends RuntimeException {
    private String message;
    public BlogNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public BlogNotFoundException() {
    }
}
