package com.interestgroup.microservicelogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.netflix.discovery.EurekaClient;

@RestController
public class LoginController {
	@Autowired
	private EurekaClient discoveryClient;
	
    @Bean
    RestTemplate restTemplate() {
       return new RestTemplate();
   }
    
	@GetMapping("/{id}")
	public Object login(@PathVariable Long id) {
		String url = discoveryClient.getNextServerFromEureka("MICROSERVICE-USER", false).getHomePageUrl();;
		return restTemplate().getForEntity(url + "/" + id, String.class).getBody();
	}

}