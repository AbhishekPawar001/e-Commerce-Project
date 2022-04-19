package com.store.order.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.store.order.dto.PaymentDTO;
import com.store.order.exceptions.UserNotFoundException;

@FeignClient(name = "http://BANK-SERVICE/transaction")
public interface BankFeignClient {
	
	@PostMapping("/transfer")
	public PaymentDTO transfer(@RequestBody PaymentDTO paymentDTO) throws UserNotFoundException;
	
	@GetMapping("/checkAccountNumber/{accountNumber}")
	public String checkAccountNumber(@PathVariable("accountNumber") Long accountNumber);
}
