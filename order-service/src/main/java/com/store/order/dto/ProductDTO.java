package com.store.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private Long productId;
	private String productName;
	private String productDescription;
	private Double productPrice;
	private Integer quantity;
	
	public Double getTotalPrice() {
		return productPrice*quantity;
	}
}
