package com.honsoft.listener;

import java.util.Map;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class MyServletContextListener implements ServletContextListener{
	private static Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);
	
	private ServletContext sc;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info(sce.toString()+" : " + " initialized.");
		
		this.sc = sce.getServletContext();
		
		printAllServletsAndFileters();
	}
	
	private void printAllServletsAndFileters() {
		Map<String, ? extends ServletRegistration> servletsList = sc.getServletRegistrations();
		ServletRegistration sr = null;
		
		for(String key : servletsList.keySet()) {
			sr = servletsList.get(key);
			logger.info("== servlet : "+sr.getName()+" , "+sr.getClassName()+" , "+sr.getMappings());
			
		}
		
		logger.info("--------------------------------------");
		
		Map<String, ? extends FilterRegistration> filtersList = sc.getFilterRegistrations();
		FilterRegistration fr = null;
		for(String key : filtersList.keySet()) {
			fr = filtersList.get(key);
			logger.info("== filter : "+fr.getName()+" , "+fr.getClassName()+" , "+fr.getUrlPatternMappings());
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info(sce.toString()+" : " +" destroyed.");
	}

}
