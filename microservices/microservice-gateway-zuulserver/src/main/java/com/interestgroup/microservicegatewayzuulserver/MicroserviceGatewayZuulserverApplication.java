package com.interestgroup.microservicegatewayzuulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MicroserviceGatewayZuulserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayZuulserverApplication.class, args);
	}
}
