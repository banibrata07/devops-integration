package com.github.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableJms
@ComponentScan({ "com.github.api.*,com.github.configuration.*,com.github.model.*,com.github.service.*" })
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
