package com.store.order.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private Long productId;
	private String productCode;
	private String productName;
	private String productDescription;
	private Double price;
	private LocalDateTime timestamp;
}
