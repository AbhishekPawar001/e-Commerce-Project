package com.store.order.dto;

import java.time.LocalDateTime;

import com.store.order.helper.BusinessAccountNumber;

import lombok.Data;

@Data
public class PaymentDTO {
	
	private Long fromAccountNo;
	private Long toAccountNo;
	private String comment;
	private Double amount;
	private LocalDateTime createdDate;
	
	public PaymentDTO(Long fromAccountNumber, Double amount) {
		this.fromAccountNo = fromAccountNumber;
		this.toAccountNo = BusinessAccountNumber.getBusinessAccountNumber();
		this.comment = "Online Shopping";
		this.amount = amount;
		this.createdDate = LocalDateTime.now();
	}

}
