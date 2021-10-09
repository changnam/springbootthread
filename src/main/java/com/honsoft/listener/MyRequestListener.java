package com.honsoft.listener;

import java.util.List;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class MyRequestListener implements ServletRequestListener {
	private static Logger logger = LoggerFactory.getLogger(MyRequestListener.class);

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		logger.info( req.getServletPath() + req.getPathInfo()+ " : " + " initialized.");
	
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			//logger.info("   ===> cookies:");
			for (Cookie cookie : cookies) {
				//logger.info(cookie.getName()+ " : " + cookie.getValue());
			}
		}
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		logger.info(sre.toString() + " : " + " destroyed.");
	}

}
