package com.banking.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.entities.TransactionEntity;
import com.banking.repositories.TransactionRepository;
import com.banking.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public TransactionEntity saveTransaction(TransactionEntity transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public List<TransactionEntity> findByMonthAndYear(int month, int year, long accountNo) {
		return transactionRepository.findByMonthAndYear(month, year, accountNo);
	}

//	@Override
//	public List<TransactionEntity> findByAccountNo(long accountNo) {
//		return transactionRepository.findByFromAccountNoOrToAccountNo(accountNo);
//	}
}
