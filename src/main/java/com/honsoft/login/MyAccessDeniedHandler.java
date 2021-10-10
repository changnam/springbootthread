package com.honsoft.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandler implements AccessDeniedHandler{
	private static Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.info("@@@@@@@@@@@@@@@@@@@@@@MyAccessDeniedHandler handle method called.");
		
		response.sendRedirect("/my-error-page");
		
	}

}
