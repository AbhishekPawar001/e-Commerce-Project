package com.hcl.authserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.hcl.authserver.config.JwtUtil;
import com.hcl.authserver.dto.UserDTO;
import com.hcl.authserver.exceptions.UserNotFoundException;
import com.hcl.authserver.feignclient.UserFeignCLient;
import com.hcl.authserver.vo.UserLoginRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {
	
	@Autowired
	UserFeignCLient userFeignCLient;
    
	@Autowired
	JwtUtil jwt;

	public String login(UserLoginRequest userLoginRequest) throws UserNotFoundException{
		String accessTokenOrMessage = "";
		log.info("finding user for checking the user is registered or not and throw userNotFoundException");
		UserDTO userDTO = userFeignCLient.findUser(userLoginRequest);
		Boolean check = BCrypt.checkpw(userLoginRequest.getPassword(), userDTO.getPassword());
        if(check == true) {
        	log.info("generating auth token after matching the login credentials");
        	accessTokenOrMessage = "[accessToken]: "+jwt.generate(userDTO, "ACCESS")+" [refreshToken]: "+jwt.generate(userDTO, "REFRESH");
        } else {
        	throw new UserNotFoundException("Invalid Email-Id or Password");
        }
        return accessTokenOrMessage;
	}

}
