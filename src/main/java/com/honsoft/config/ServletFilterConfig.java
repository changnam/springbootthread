package com.honsoft.config;

import org.apache.catalina.servlets.DefaultServlet;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.honsoft.filter.MyFilterOne;
import com.honsoft.filter.MyFilterTwo;
import com.honsoft.servlet.MyServletOne;
import com.honsoft.servlet.MyServletTwo;

@Configuration
public class ServletFilterConfig {
	private static final String mapping = "/h2-console/*";

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings(mapping);
		return registrationBean;
	}

	@Bean
	public ServletRegistrationBean<MyServletOne> registerMyServletOne() {
		ServletRegistrationBean<MyServletOne> servlet = new ServletRegistrationBean<>();

		servlet.setServlet(new MyServletOne());
		// 아래와 같이 하면, /servlet/one 도 해당됨. /servlet/onea 는 해당안됨, /servlet/*에 해당됨
		servlet.addUrlMappings("/servlet/one/*");
		servlet.setOrder(0);

		return servlet;
	}

	@Bean
	public ServletRegistrationBean<MyServletTwo> registerMyServletTwo() {
		ServletRegistrationBean<MyServletTwo> servlet = new ServletRegistrationBean<>();

		servlet.setServlet(new MyServletTwo());
		servlet.addUrlMappings("/servlet/two/*");
		servlet.setOrder(100);

		return servlet;
	}
	
	//@Bean
	//public ServletRegistrationBean<DefaultServlet> registerDefaultServlet() {
	//	ServletRegistrationBean<DefaultServlet> servlet = new ServletRegistrationBean<>();

	//	servlet.setServlet(new DefaultServlet());
		//servlet.addUrlMappings("/*");
	//	servlet.setName("defaultServlet");
		//servlet.setOrder(100);

	//	return servlet;
	//}

	@Bean
	public FilterRegistrationBean<MyFilterOne> registerMyfilterOne() {
		FilterRegistrationBean<MyFilterOne> filter = new FilterRegistrationBean<>();

		filter.setFilter(new MyFilterOne());
		filter.addUrlPatterns("/servlet/*");
		filter.setOrder(0);

		return filter;
	}

	@Bean
	public FilterRegistrationBean<MyFilterTwo> registerMyfilterTwo() {
		FilterRegistrationBean<MyFilterTwo> filter = new FilterRegistrationBean<>();

		filter.setFilter(new MyFilterTwo());
		filter.addUrlPatterns("/servlet/*");
		filter.setOrder(100);

		return filter;
	}
}
