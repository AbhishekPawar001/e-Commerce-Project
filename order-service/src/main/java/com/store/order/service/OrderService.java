package com.store.order.service;

import org.springframework.stereotype.Service;

import com.store.order.exceptions.OrderNotFoundException;
import com.store.order.exceptions.UserNotFoundException;
import com.store.order.vo.OrderRequest;
import com.store.order.vo.OrderResponse;

@Service
public interface OrderService {

	public String saveOrder(OrderRequest orderRequest) throws UserNotFoundException;

	public OrderResponse getOrderById(Long orderId) throws OrderNotFoundException;
}
