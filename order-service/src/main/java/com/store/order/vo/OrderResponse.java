package com.store.order.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.store.order.dto.ProductDTO;
import com.store.order.model.Orders;

import lombok.Data;

@Data
public class OrderResponse {
	
	private Long orderId;
	private Long userId;
	private Double grandTotal;
	private LocalDateTime orderDate;
	private List<ProductDTO> products;
	
	public OrderResponse(Orders orders, Double totalAmount, List<ProductDTO> list) {
		this.orderId = orders.getOrderId();
		this.userId = orders.getUserId();
		this.grandTotal = totalAmount;
		this.products = list;
		this.orderDate = orders.getOrderDate();
	}
}
