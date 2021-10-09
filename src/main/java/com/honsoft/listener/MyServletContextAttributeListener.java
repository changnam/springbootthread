package com.honsoft.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener{
	private static Logger logger = LoggerFactory.getLogger(MyServletContextAttributeListener.class);
	
	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		logger.info(scae.getName()+" : " +scae.getValue() +" added.");
	}
	
	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		logger.info(scae.getName()+" : " +scae.getValue() +" added.");
	}
	
	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		logger.info(scae.getName()+" : " +scae.getValue() +" added.");
	}

}
