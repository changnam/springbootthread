package com.honsoft.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

public class CustomizeContainerBean {
	@Autowired
	private ConfigurableServletWebServerFactory cswf;
	
	//@Autowired
	//private WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> customizer;
	
	@PostConstruct
	public  void init() {
		cswf.setRegisterDefaultServlet(true);
	}
}
