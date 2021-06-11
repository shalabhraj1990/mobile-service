package com.spring.boot.mobile.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.spring.boot.mobile.filter.Analyticsfilter;
import com.spring.boot.mobile.filter.RequestResponseFilter;

@Configurable
public class FilterConfiguration {
	@Autowired
	RequestResponseFilter requestResponseFilter;
	@Autowired
	Analyticsfilter analyticsfilter;
	@Bean
	FilterRegistrationBean<Filter> loggingFilter() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
		registration.setFilter(new RequestResponseFilter());
		registration.setFilter(new Analyticsfilter());
		registration.addUrlPatterns("/*");
		return registration;
	}
}
