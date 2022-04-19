package com.hcl.authserver.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hcl.authserver.dto.UserDTO;
import com.hcl.authserver.vo.UserLoginRequest;

@FeignClient(name = "http://USER-SERVICE/users")
public interface UserFeignCLient {
	
	@PostMapping("/userLogin")
	public UserDTO findUser(@RequestBody UserLoginRequest userLoginRequest);
}
