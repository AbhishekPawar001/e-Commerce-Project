package com.banking.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.banking.entities.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>{
	
	List<AccountEntity> getUserAccountsByAccountNo(long accountNo);
	
	@Query(value = "SELECT current_balance FROM account WHERE account_no = :accountNo", nativeQuery = true)
	int getAccountBalanceByAccountNo(long accountNo);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE account SET current_balance = :newBalance, last_updated_date = :lastUpdatedDate WHERE account_no = :accountNo", nativeQuery = true)
	void updateAccountBalanceByAccountNo(int newBalance, long accountNo, LocalDateTime lastUpdatedDate);
}
