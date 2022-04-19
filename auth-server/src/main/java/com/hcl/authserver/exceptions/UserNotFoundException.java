package com.hcl.authserver.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends Exception {
		
	public UserNotFoundException(String message) {
		super(message);
	}
}
