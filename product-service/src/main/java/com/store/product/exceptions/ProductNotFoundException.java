package com.store.product.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductNotFoundException extends Exception {
		
	public ProductNotFoundException(String message) {
		super(message);
	}
}
