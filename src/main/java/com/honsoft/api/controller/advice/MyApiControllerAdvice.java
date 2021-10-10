package com.honsoft.api.controller.advice;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.honsoft.exception.BlogAlreadyExistsException;
import com.honsoft.exception.BlogNotFoundException;
import com.honsoft.exception.ErrorDetails;

@RestControllerAdvice(basePackages = "com.honsoft.api.controller")
public class MyApiControllerAdvice {
	@Value(value = "${data.exception.message1}")
	private String message1;
	@Value(value = "${data.exception.message2}")
	private String message2;
	@Value(value = "${data.exception.message3}")
	private String message3;

	@ExceptionHandler(value = BlogAlreadyExistsException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ErrorDetails blogAlreadyExistsException(BlogAlreadyExistsException blogAlreadyExistsException,
			WebRequest request) {
		return new ErrorDetails(new Date(), blogAlreadyExistsException.getMessage(), request.getDescription(false));
	}

	@ExceptionHandler(value = BlogNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorDetails blogNotFoundException(BlogNotFoundException blogNotFoundException, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), blogNotFoundException.getMessage(),
				request.getDescription(false));
		return errorDetails;
	}

}
