package com.store.order.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long productId;
	private int quantity;
	private Double productPrice;
	private LocalDateTime timestamp;
	
	public Double getPrice() {
		return productPrice*quantity;
	}

	public OrderItem() {
		this.timestamp = LocalDateTime.now();
	}
}
