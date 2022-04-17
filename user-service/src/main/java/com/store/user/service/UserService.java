package com.store.user.service;

//import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.user.model.User;
import com.store.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}

//	public User saveUser(User user) {
//		user.setTimestamp(LocalDateTime.now());
//		return userRepository.save(user);
//	}
}
