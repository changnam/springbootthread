package com.honsoft.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

//@Component
public class CustomContainer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
	private static Logger logger = LoggerFactory.getLogger(CustomContainer.class);

  public void customize(TomcatServletWebServerFactory factory) {
		// factory.setPort(9090);
		// factory.setContextPath("");
		
		logger.info(" customizing tomcat *********");
		
		//factory.setBaseDirectory(new File("BOOT-INF/classes"));
		//Collection<TomcatContextCustomizer> customizers = factory.getTomcatContextCustomizers();
		//for (TomcatContextCustomizer customizer : customizers) {
		//	customizer.customize(null);
		//}
		
		Map<String, String> maps = new HashMap<>();
		maps.put("listings", "true");
		maps.put("readOnly", "false");

		factory.setInitParameters(maps);
		
		factory.setRegisterDefaultServlet(true);
   }
}
