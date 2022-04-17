package com.store.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.user.model.User;
import com.store.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public List<User> getUsers() {
		return userService.getAllUser();
	}
	
//	@PostMapping("saveUser")
//	public User saveUser(@RequestBody User user) {
//		return userService.saveUser(user);
//	}
}
