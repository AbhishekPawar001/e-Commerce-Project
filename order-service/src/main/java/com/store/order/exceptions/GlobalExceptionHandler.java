package com.store.order.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleInvalidArgumentExceptions(MethodArgumentNotValidException ex) {
		log.info("customising and binding the MethodArgumentNotValidException and sending custom error messages");
		Map<String, String> errMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errMap.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<Map<String, String>>(errMap, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleUserNotFoundExceptions(UserNotFoundException ex) {
		log.info("defining user not found custom exceptions");
		Map<String, String> errMap = new HashMap<>();
		errMap.put("errorMessage", ex.getMessage());
		return new ResponseEntity<Map<String, String>>(errMap, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleOrderNotFoundExceptions(OrderNotFoundException ex) {
		log.info("defining order not found custom exceptions");
		Map<String, String> errMap = new HashMap<>();
		errMap.put("errorMessage", ex.getMessage());
		return new ResponseEntity<Map<String, String>>(errMap, HttpStatus.NOT_FOUND);
	}
}
