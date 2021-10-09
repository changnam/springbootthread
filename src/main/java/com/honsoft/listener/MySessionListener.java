package com.honsoft.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class MySessionListener implements HttpSessionListener{
	private static Logger logger = LoggerFactory.getLogger(MySessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("###################### " +se.toString()+" : " +se.getSession().getId()+" : created.");
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info(se.toString()+" : " +se.getSession().getId()+" : destroyed.");
	}

}
