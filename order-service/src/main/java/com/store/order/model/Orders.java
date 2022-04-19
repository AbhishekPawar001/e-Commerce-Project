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
	
	@JoinColumn(name = "order_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderItem> products;
	
	public Orders(OrderDTO orderDTO) {
		this.userId = orderDTO.getUserId();
		this.products = orderDTO.getProducts();
		this.orderDate = LocalDateTime.now();
	}
	
}
