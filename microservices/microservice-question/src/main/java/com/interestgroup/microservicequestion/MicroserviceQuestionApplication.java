package com.interestgroup.microservicequestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceQuestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceQuestionApplication.class, args);
	}
}
