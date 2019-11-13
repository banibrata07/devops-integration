package com.scm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJms
@ComponentScan({ "com.scm.api,com.scm.configuration,com.scm.model.github.commit,com.scm.service,com.scm.entity,com.scm.repository" })
@EnableMongoRepositories(basePackageClasses = com.scm.repository.CommitDtlsRepository.class)
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
