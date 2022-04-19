package com.hcl.authserver.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.authserver.exceptions.UserNotFoundException;
import com.hcl.authserver.services.AuthService;
import com.hcl.authserver.vo.UserLoginRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid UserLoginRequest userLoginRequest) throws UserNotFoundException{
		log.info("login method by passing email and password to get the authentication token");
		return new ResponseEntity<>(authService.login(userLoginRequest), HttpStatus.CREATED);
	}

}
