package com.honsoft.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver{
	private static Logger logger = LoggerFactory.getLogger(MyHandlerExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.info("MyHandlerExceptionResolver called");
		
		ModelAndView m = new ModelAndView();
		m.addObject("mymessage", "blog not found exception occured");
		
		m.setViewName("error");
		
		return m;
	}

}
