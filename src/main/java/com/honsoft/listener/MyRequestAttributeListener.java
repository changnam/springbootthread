package com.honsoft.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebListener
public class MyRequestAttributeListener implements ServletRequestAttributeListener{
	private static Logger logger = LoggerFactory.getLogger(MyRequestAttributeListener.class);
	
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		logger.info(srae.getName()+" : " +srae.getValue() +" added.");
	}
	
	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		logger.info(srae.getName()+" : " +srae.getValue() +" removed.");
	}
	
	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		logger.info(srae.getName()+" : " +srae.getValue() +" replaced.");
	}

}
