package com.banking.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.banking.entities.AccountEntity;

@Service
public interface AccountService {

	int getAccountBalanceByAccountNo(Long accountNo);
	
	void updateAccountBalanceByAccountNo(int newBalance, Long accountNo, LocalDateTime lastUpdatedDate);

	AccountEntity checkAccountNumber(Long accountNumber);
	
}
