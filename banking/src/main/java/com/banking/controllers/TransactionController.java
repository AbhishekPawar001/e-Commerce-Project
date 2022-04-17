package com.banking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.entities.TransactionEntity;
import com.banking.services.AccountService;
import com.banking.services.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/transfer")
	public TransactionEntity transfer(@RequestBody TransactionEntity transactionEntity) {
		
		int currentBalanceAccountFrom = accountService.getAccountBalanceByAccountNo(transactionEntity.getFromAccountNo());
		int currentBalanceAccountTo = accountService.getAccountBalanceByAccountNo(transactionEntity.getToAccountNo());
		
		int newBalanceAccountFrom = currentBalanceAccountFrom - transactionEntity.getAmount();
		int newBalanceAccountTo = currentBalanceAccountTo + transactionEntity.getAmount();
		
		accountService.updateAccountBalanceByAccountNo(newBalanceAccountFrom, transactionEntity.getFromAccountNo(), transactionEntity.getCreatedDate());
		accountService.updateAccountBalanceByAccountNo(newBalanceAccountTo, transactionEntity.getToAccountNo(), transactionEntity.getCreatedDate());
		
		return transactionService.saveTransaction(transactionEntity);
	}
	
	@GetMapping("/transaction_history/{month}/{year}/{account_no}")
	public List<TransactionEntity> transactionHistory(@PathVariable int month, @PathVariable int year, @PathVariable long account_no) {
		return transactionService.findByMonthAndYear(month, year, account_no);
	}
	
//	@GetMapping("/transaction_history/{account_number}")
//	public List<TransactionEntity> transactionHistoryByAccountNumber(@PathVariable long account_number) {
//		return transactionService.findByAccountNo(account_number);
//	}
}
