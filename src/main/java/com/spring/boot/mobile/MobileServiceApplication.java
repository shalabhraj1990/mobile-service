package com.spring.boot.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
//add below line if the entity is present in different package or different project
//as by default spring JPA will unable to find the enity while connecting to database
@EntityScan("msk.spring.boot.common.mobile.entity")
public class MobileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileServiceApplication.class, args);
	}

}
