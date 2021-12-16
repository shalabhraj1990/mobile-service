package com.spring.boot.mobile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {
	@Bean
	public OpenAPI customeOpenApi() {
		return new OpenAPI().components(new Components())
				.info(new Info().title("mobile-servic").description("Open API 3 contract"));
	}
//	@Bean
//	public LocalValidatorFactoryBean  validator() {
//		return new LocalValidatorFactoryBean();
//	}
}
