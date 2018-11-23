package com.interestgroup.microserviceuser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.interestgroup.microserviceuser.entity.User;

@RestController
public class UserController {

	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		//Should retrieve user from DB using JPA later
		User user = new User();
		user.setId((long) 1);
		user.setAge(18);
		user.setName("bryan");
		user.setUserName("bryan chen");
		return user;
	}

}