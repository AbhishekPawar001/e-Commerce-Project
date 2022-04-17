package com.banking.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {

	int getAccountBalanceByAccountNo(long accountNo);
	
	void updateAccountBalanceByAccountNo(int newBalance, long accountNo, LocalDateTime lastUpdatedDate);
	
}
