package com.spring.boot.mobile.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

@Component
public class RequestResponseFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		double startTime = System.currentTimeMillis();
		chain.doFilter(request, response);
		System.out.println("total time of api :" + (System.currentTimeMillis() - startTime));
	}

}
