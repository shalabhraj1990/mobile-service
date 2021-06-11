package com.spring.boot.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class MobileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileServiceApplication.class, args);
	}

}
