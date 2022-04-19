package com.store.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.user.model.User;
import com.store.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getUser/{userId}")
	public User getUser(@PathVariable("userId") Long userId) {
		return userService.getUserById(userId);
	}
	
	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User users) {
		return userService.saveUser(users);
	}
	
	@PostMapping("/userLogin")
	public User userLogin(@RequestBody User users) {
		return userService.getUser(users);
	}
	
}
