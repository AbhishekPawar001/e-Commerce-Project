package com.store.order.ao;

import lombok.Data;

@Data
public class ProductRequest {
	
	private Long productId;
	private int quantity;
}
