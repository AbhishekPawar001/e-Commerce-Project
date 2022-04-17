package com.store.order.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.order.ao.OrderRequest;
import com.store.order.ao.OrderResponse;
import com.store.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/saveOrder")
	public String saveOrder(@RequestBody @Valid OrderRequest orderRequest) {
		return orderService.saveOrder(orderRequest);
	}
	
	@GetMapping("/{id}")
	public OrderResponse getOrderById(@PathVariable("id") Long orderId) {
		return orderService.getOrderById(orderId);
	}

}
