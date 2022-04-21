package com.store.order.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.store.order.vo.User;

@FeignClient(name = "http://USER-SERVICE/users")
public interface UserFeignClient {
	
	@GetMapping("/getUser/{userId}")
	public User getUser(@PathVariable("userId") Long userId);
}
