package com.honsoft.config;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

//@Configuration
public class CustomContainerConfig2 {
	private static Logger logger = LoggerFactory.getLogger(CustomContainerConfig2.class);
	
	 @Value("${tomcat.file.base}")  // C:\\some\\parent\\child
	    String tomcatBaseDir;


//	@Bean
//	public ConfigurableServletWebServerFactory tomcatWebServerFactory() {
//		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//		// factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED,
//		// "/unauthenticated"));
//		// factory.setPort(9090);
//		factory.setRegisterDefaultServlet(true);
//
//		Map<String, String> maps = new HashMap<>();
//		maps.put("listings", "true");
//		maps.put("readOnly", "false");
//
//		factory.setInitParameters(maps);
//		return factory;
//	}

//  
//  //@Bean
//  public ConfigurableServletWebServerFactory undertowWebServerFactory() {
//      UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
//      factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/unauthenticated"));
//      factory.setPort(9090);
//      factory.setRegisterDefaultServlet(true);
//      return factory;
//  }
//  
	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer() {
		return new WebServerFactoryCustomizer<TomcatServletWebServerFactory>() {

			@Override
			public void customize(TomcatServletWebServerFactory factory) {
				// factory.setPort(9090);
				// factory.setContextPath("");
				
				logger.info(" customizing tomcat *********"+tomcatBaseDir);
				
				//factory.setBaseDirectory(new File("BOOT-INF/classes"));
				///Collection<TomcatContextCustomizer> customizers = factory.getTomcatContextCustomizers();
				//for (TomcatContextCustomizer customizer : customizers) {
				//	customizer.customize(null);
				//}
				//Map<String, String> maps = new HashMap<>();
				//maps.put("listings", "true");
				//maps.put("readOnly", "false");

				//factory.setInitParameters(maps);
				
				factory.setRegisterDefaultServlet(true);

				
//				factory.addContextCustomizers(new TomcatContextCustomizer() {
//
//					@Override
//					public void customize(Context context) { //
//						//context.setDocBase("/");
//						Wrapper defservlet = (Wrapper) context.findChild("default");
//						defservlet.addInitParameter("listings", "true");
//						defservlet.addInitParameter("readOnly", "false");
//						defservlet.addMapping("/*");
//					}
//				});
				 
			}
		};
	}
}