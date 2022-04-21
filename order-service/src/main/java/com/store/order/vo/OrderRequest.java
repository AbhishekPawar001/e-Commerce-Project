package com.store.order.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
	
	@Range(min = 1, max = 6, message = "user id must be between 1 to 6")
	@NotNull(message = "user id must not be empty")
	private Long userId;

	@Range(min = 10000000, max = 99999999, message = "account number must be 8 characters long")
	@NotNull(message = "account number must not be empty")
	private Long accountNumber;
	
	@NotEmpty(message = "List of product must not be empty")
	private List<@Valid ProductRequest> products;
}
