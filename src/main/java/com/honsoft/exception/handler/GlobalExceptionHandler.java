package com.honsoft.exception.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

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
    
//    @ExceptionHandler(value = BlogAlreadyExistsException.class)
//    public ModelAndView blogAlreadyExistsException(BlogAlreadyExistsException blogAlreadyExistsException) {
//        ModelAndView m = new ModelAndView();
//        
//    	m.addObject("message",message1);
//    	m.setViewName("error");
//        
//    	return m;
//    }
//    
//    @ExceptionHandler(value = BlogNotFoundException.class)
//    public ModelAndView blogNotFoundException(BlogNotFoundException blogNotFoundException) {
//    	ModelAndView m = new ModelAndView();
//    	
//    	m.addObject("message",message2);
//        m.setViewName("error");
//        
//     	return m;
//    }

//    @ExceptionHandler(value = Exception.class)
//    public String  databaseConnectionFailsException(Exception exception,Model m) {
//    	 m.addAttribute("message",message3);
//         
//     	return "error";
//    }
}
