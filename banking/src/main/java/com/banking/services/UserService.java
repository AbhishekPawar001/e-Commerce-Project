package com.banking.services;

import org.springframework.stereotype.Service;

import com.banking.dto.UserDTO;

@Service
public interface UserService {

	UserDTO saveUser(UserDTO userdto);
	
}
