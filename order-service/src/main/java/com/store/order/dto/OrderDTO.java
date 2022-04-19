package com.store.order.dto;

import java.util.List;

import com.store.order.model.OrderItem;

import lombok.Data;

@Data
public class OrderDTO {
	
	private Long userId;
	private List<OrderItem> products;
	
	public OrderDTO(Long userId, List<OrderItem> list) {
		this.userId = userId;
		this.products = list;
	}
}
