package com.store.order.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends Exception {
		
	public UserNotFoundException(String message) {
		super(message);
	}
}
