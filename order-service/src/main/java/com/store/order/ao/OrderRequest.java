package com.store.order.ao;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderRequest {
	
	@NotBlank(message = "{userId.not-null}")
	private Long userId;
	private Long accountNumber;
	private List<ProductRequest> products;
}
