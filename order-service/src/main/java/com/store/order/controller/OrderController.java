package com.store.order.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.order.exceptions.OrderNotFoundException;
import com.store.order.exceptions.UserNotFoundException;
import com.store.order.service.OrderService;
import com.store.order.vo.OrderRequest;
import com.store.order.vo.OrderResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/saveOrder")
	public ResponseEntity<String> saveOrder(@Valid @RequestBody OrderRequest orderRequest) throws UserNotFoundException{
		log.info("saving the ordes in Database");
		return new ResponseEntity<>(orderService.saveOrder(orderRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") Long orderId) throws OrderNotFoundException{
		log.info("get the list of ordes from Database");
		return new ResponseEntity<OrderResponse>(orderService.getOrderById(orderId), HttpStatus.OK);
	}

}
