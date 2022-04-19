package com.banking.services;

import com.banking.entities.TransactionEntity;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

	public TransactionEntity saveTransaction(TransactionEntity transaction);
	
	public List<TransactionEntity> findByMonthAndYear(int month, int year, Long accountNo);

//	public List<TransactionEntity> findByAccountNo(long accountNo);

}
