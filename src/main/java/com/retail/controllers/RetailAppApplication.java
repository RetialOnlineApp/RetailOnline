package com.retail.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackages = { "com.retail.entities" })
@EnableJpaRepositories(basePackages = { "com.retail.repositories" })
@EnableTransactionManagement
@SpringBootApplication
public class RetailAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailAppApplication.class, args);
	}
}
