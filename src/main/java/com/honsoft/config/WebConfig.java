package com.honsoft.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.honsoft.exception.handler.MyHandlerExceptionResolver;
import com.honsoft.interceptor.LogInterceptor;
import com.honsoft.interceptor.MyHandlerInterceptor;
import com.honsoft.listener.MyEmbeddedWebServerListener;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	//@Autowired
	//private ConfigurableServletWebServerFactory cswf;

	// simpleurlhandlermapping 에 추가되고, 처리할 handler 는 resourcehttprequesthandeler 로
	// 매핑한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
		// registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

//	 @Bean(name="simpleMappingExceptionResolver")
//	  public SimpleMappingExceptionResolver
//	                  createSimpleMappingExceptionResolver() {
//	    SimpleMappingExceptionResolver r =
//	                new SimpleMappingExceptionResolver();
//
//	    Properties mappings = new Properties();
//	    mappings.setProperty("DatabaseException", "databaseError");
//	    mappings.setProperty("InvalidCreditCardException", "creditCardError");
//
//	    r.setExceptionMappings(mappings);  // None by default
//	    r.setDefaultErrorView("error");    // No default
//	    r.setExceptionAttribute("mymessage");     // Default is "exception"
//	    r.setWarnLogCategory("example.MvcLogger");     // No default
//	    return r;
//	  }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyHandlerInterceptor()).order(1);
		registry.addInterceptor(new LogInterceptor()).order(0);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		//configurer.enable("defaultServlet");
		configurer.enable("default");
		//configurer.enable();
		//WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
	}
	
	//@Bean
	//public CustomizeContainerBean customContainerBean() {
	//	return new CustomizeContainerBean();
	//}
	
	@Bean
	public ApplicationListener<WebServerInitializedEvent> myEmbeddedApplicationListener(){
		return new MyEmbeddedWebServerListener();
	}

}
