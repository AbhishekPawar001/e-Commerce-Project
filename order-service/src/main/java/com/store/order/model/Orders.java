package com.store.order.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.store.order.dto.OrderDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private Long userId;
	private LocalDateTime orderDate;
	@Transient
	private Long accountNumber;
	
	@JoinColumn(name = "order_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderItem> products;
	
	public Orders(OrderDTO orderDTO) {
		this.userId = orderDTO.getUserId();
		this.accountNumber = orderDTO.getAccountNumber();
		this.products = orderDTO.getProducts();
		this.orderDate = LocalDateTime.now();
	}
	
}
