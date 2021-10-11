package com.honsoft.listener;


import org.apache.catalina.servlets.DefaultServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.WebApplicationContext;

public class MyEmbeddedWebServerListener implements ApplicationListener<WebServerInitializedEvent>{
	private static Logger logger = LoggerFactory.getLogger(MyEmbeddedWebServerListener.class);
	
	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		WebServerApplicationContext context =  event.getApplicationContext();
		String[] beans = context.getBeanDefinitionNames();
		
		for(String bean : beans) {
			logger.info("+++++++++++++++++ "+bean+","+context.getBean(bean).toString());
		}
		//DefaultServlet servlet = context.getBean(DefaultServlet.class);
		//logger.info("+++++++++++++++++ " + servlet.getInitParameter("listings"));
		
		//context.get
		WebServer server = event.getWebServer();
		logger.info("+++++++++++++ " +server.getPort()+","+server.getClass().toString());
		
		//context.ser
		
	}

}
