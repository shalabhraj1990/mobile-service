package com.spring.boot.mobile.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class Analyticsfilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest hsr = (HttpServletRequest) request;

		String userAgnet = hsr.getHeader("User-Agent");
		System.out.println("user :" + userAgnet);
		chain.doFilter(request, response);

	}

}
