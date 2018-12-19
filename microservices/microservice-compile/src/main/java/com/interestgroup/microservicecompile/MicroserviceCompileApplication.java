package com.interestgroup.microservicecompile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceCompileApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCompileApplication.class, args);
	}
}
