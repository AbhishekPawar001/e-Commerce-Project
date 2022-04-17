package com.store.order.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.store.order.dto.PaymentDTO;

@FeignClient(name = "http://USER-SERVICE/transaction")
public interface UserFeignClient {
	
	@PostMapping("/transfer")
	public PaymentDTO transfer(@RequestBody PaymentDTO transactionEntity);
}
