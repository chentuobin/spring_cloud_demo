package com.interestgroup.microservicelogin.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class LoginController {

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getDefaultUser")
	@GetMapping("/{id}")
	public Object login(@PathVariable Long id) {
		return restTemplate.getForEntity("http://MICROSERVICE-USER/" + id, String.class).getBody();
	}

	private String getDefaultUser(Long id) {
		System.out.println("Circuit Breaker");
		return "{\"name\":\"admin\",\"age\":\"-1\"}";
	}
}