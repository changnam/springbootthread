package com.honsoft.exception.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.honsoft.exception.BlogAlreadyExistsException;
import com.honsoft.exception.BlogNotFoundException;

@ControllerAdvice(basePackages = "com.honsoft.controller")
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;
    @Value(value = "${data.exception.message2}")
    private String message2;
    @Value(value = "${data.exception.message3}")
    private String message3;
    
    @ExceptionHandler(value = BlogAlreadyExistsException.class)
    public String blogAlreadyExistsException(BlogAlreadyExistsException blogAlreadyExistsException,Model m) {
        m.addAttribute("message",message1);
        
    	return "error";
    }
    
    @ExceptionHandler(value = BlogNotFoundException.class)
    public String blogNotFoundException(BlogNotFoundException blogNotFoundException,Model m) {
    	 m.addAttribute("message",message2);
         
     	return "error";
    }

    @ExceptionHandler(value = Exception.class)
    public String  databaseConnectionFailsException(Exception exception,Model m) {
    	 m.addAttribute("message",message3);
         
     	return "error";
    }
}
