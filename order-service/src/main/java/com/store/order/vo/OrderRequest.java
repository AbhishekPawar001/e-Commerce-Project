package com.store.order.vo;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
	
	@Min(1)
	@Max(10)
	@NotNull(message = "user id must not be empty")
	private Long userId;
	@Min(value = 1111111, message = "account number must be between 7 and 8 characters long")
	@Max(99999999)
	@NotNull(message = "account number must not be empty")
	private Long accountNumber;
	@NotNull
	private List<ProductRequest> products;
}
