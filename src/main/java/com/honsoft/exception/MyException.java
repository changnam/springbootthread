package com.honsoft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason=" My Exception message No blog found message annotated with responsestatus")
public class MyException extends RuntimeException {
    private String message;
    public MyException(String message) {
        super(message);
        this.message = message;
    }
    public MyException() {
    }
}
