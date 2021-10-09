package com.honsoft.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFilterTwo implements Filter {

	private static Logger logger = LoggerFactory.getLogger(MyFilterTwo.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("**************** my filter two dofilter called.");

		// Go to the next element (filter or target) in chain.
		chain.doFilter(request, response);

	}

}
