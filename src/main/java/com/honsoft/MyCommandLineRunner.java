package com.honsoft;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

//@Component
public class MyCommandLineRunner implements CommandLineRunner{
	private static Logger logger = LoggerFactory.getLogger(MyCommandLineRunner.class);
	
	private HandlerMappingIntrospector introspector;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("@@@@@@@ Thread Name : "+Thread.currentThread().getName());
		
		List<HandlerMapping> list = introspector.getHandlerMappings();
		
		for (HandlerMapping mapping : list) {
			logger.info("###### "+mapping.toString());
		}
		
		
		
	}
	
	@Autowired
	public void setIntrospector(@Qualifier("handlerMappingIntrospector") HandlerMappingIntrospector introspector) {
		this.introspector = introspector;
	}

}
