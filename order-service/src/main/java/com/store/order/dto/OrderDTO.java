package com.store.order.dto;

import java.util.List;

import com.store.order.model.OrderItem;

import lombok.Data;

@Data
public class OrderDTO {
	
	private Long userId;
	private Long accountNumber;
	private List<OrderItem> products;
	
	public OrderDTO(Long fromAccountNumber, Long userId, List<OrderItem> list) {
		this.userId = userId;
		this.accountNumber = fromAccountNumber;
		this.products = list;
	}
}
