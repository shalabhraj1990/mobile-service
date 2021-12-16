package com.spring.boot.mobile.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfiguration {
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messgeSource = new ReloadableResourceBundleMessageSource();
		messgeSource.setBasename("classpath:error-messages");
		messgeSource.setDefaultEncoding("UTF-8");
		return messgeSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
