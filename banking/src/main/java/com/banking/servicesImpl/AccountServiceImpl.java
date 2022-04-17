package com.banking.servicesImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.repositories.AccountRepository;
import com.banking.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public int getAccountBalanceByAccountNo(long accountNo) {
		return accountRepository.getAccountBalanceByAccountNo(accountNo);
	}

	@Override
	public void updateAccountBalanceByAccountNo(int newBalance, long accountNo, LocalDateTime lastUpdatedDate) {
		accountRepository.updateAccountBalanceByAccountNo(newBalance, accountNo, lastUpdatedDate);
	}
	
}
