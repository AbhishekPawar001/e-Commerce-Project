package com.store.order.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class ProductRequest {
	
	@Range(min = 1, message = "product id must be greater than or equal to 1")
	@NotNull(message = "product id must not be empty")
	private Long productId;
	@Min(1)
	@Max(5)
	@NotNull(message = "quantity must not be empty")
	private int quantity;
}
