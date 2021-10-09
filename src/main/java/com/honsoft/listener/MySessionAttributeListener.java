package com.honsoft.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebListener
public class MySessionAttributeListener implements HttpSessionAttributeListener{
	private static Logger logger = LoggerFactory.getLogger(MySessionAttributeListener.class);
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		logger.info(se.getName()+" : " +se.getValue() +" added.");
	}
	
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		logger.info(se.getName()+" : " +se.getValue() +" added.");
	}
	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		logger.info(se.getName()+" : " +se.getValue() +" added.");
	}

}
