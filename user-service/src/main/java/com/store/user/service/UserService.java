package com.store.user.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.user.model.User;
import com.store.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserById(Long userId){
		return userRepository.findByUserId(userId);
	}

	public User saveUser(User user) {
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		user.setTimestamp(LocalDateTime.now());
		return userRepository.save(user);
	}

	public User getUser(User user) {
		return userRepository.findByEmail(user.getEmail());
		
	}
	
}
