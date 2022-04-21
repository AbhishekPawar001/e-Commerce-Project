package com.store.product.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleUserNotFoundExceptions(ProductNotFoundException ex) {
		Map<String, String> errMap = new HashMap<>();
		errMap.put("errorMessage", ex.getMessage());
		return new ResponseEntity<Map<String, String>>(errMap, HttpStatus.OK);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ErrorResponse apiResponse = new ErrorResponse(message, false);
		return new ResponseEntity<ErrorResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
}
