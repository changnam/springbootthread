package com.honsoft.exception.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.honsoft.exception.BlogAlreadyExistsException;
import com.honsoft.exception.BlogNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;
    @Value(value = "${data.exception.message2}")
    private String message2;
    @Value(value = "${data.exception.message3}")
    private String message3;
    
    @ExceptionHandler(value = BlogAlreadyExistsException.class)
    public ResponseEntity blogAlreadyExistsException(BlogAlreadyExistsException blogAlreadyExistsException) {
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(value = BlogNotFoundException.class)
    public ResponseEntity blogNotFoundException(BlogNotFoundException blogNotFoundException) {
        return new ResponseEntity<String>(message2, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
        return new ResponseEntity<>(message3, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
