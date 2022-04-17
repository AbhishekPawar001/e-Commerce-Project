package com.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.banking.entities.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

	@Query(value = "SELECT * FROM transactions WHERE (YEAR(created_date) = :year AND MONTH(created_date) = :month AND from_account_no = :accountNo) OR (YEAR(created_date) = :year AND MONTH(created_date) = :month AND to_account_no = :accountNo)", nativeQuery = true)
	List<TransactionEntity> findByMonthAndYear(int month, int year, long accountNo);

}
