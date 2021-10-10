package com.honsoft.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class MyHandlerInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if (request.getParameter("testParam") != null) {
            throw new Exception("exception from interceptor");
        } else if (request.getParameter("stop") != null) {
        	return false;
        }
        return true;
	}

}
