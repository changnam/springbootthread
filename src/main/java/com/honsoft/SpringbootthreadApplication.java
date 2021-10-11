package com.honsoft;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, H2ConsoleAutoConfiguration.class })
@ServletComponentScan
public class SpringbootthreadApplication implements CommandLineRunner {
	private static Logger logger = LoggerFactory.getLogger(SpringbootthreadApplication.class);

	private HandlerMappingIntrospector introspector;

	public static void main(String[] args) {
		logger.info("==== Thread Name before : " + Thread.currentThread().getName());
		SpringApplication.run(SpringbootthreadApplication.class, args);

		logger.info("==== Thread Name after :" + Thread.currentThread().getName());
	}

	@Bean
	public HandlerMappingIntrospector handlerMappingIntrospector() {
		return new HandlerMappingIntrospector();
	}

	@Autowired
	public void setIntrospector(@Qualifier("handlerMappingIntrospector") HandlerMappingIntrospector introspector) {
		this.introspector = introspector;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("@@@@@@@ Thread Name : " + Thread.currentThread().getName());

		List<HandlerMapping> list = introspector.getHandlerMappings();

		for (HandlerMapping mapping : list) {
			if (mapping instanceof Ordered) {
				logger.info("###### " + mapping.toString() + " , Order : " + ((Ordered) mapping).getOrder());
			} else {
				logger.info("###### " + mapping.toString());
			}
			if (mapping instanceof SimpleUrlHandlerMapping) {
				Map<String, ?> maps = ((SimpleUrlHandlerMapping) mapping).getUrlMap();
				for (String key : maps.keySet()) {
					logger.info("$$$$$ " + key + " : " + maps.get(key).toString());
				}

			}
			if (mapping instanceof RequestMappingHandlerMapping) {
				Map<RequestMappingInfo, HandlerMethod> maps = ((RequestMappingHandlerMapping)mapping).getHandlerMethods();
				
				for (RequestMappingInfo key : maps.keySet()) {
					logger.info(key.toString()+","+maps.get(key).getMethod().toString());
				}
			}

		}

	}

}
