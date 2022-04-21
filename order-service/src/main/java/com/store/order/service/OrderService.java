package com.store.order.service;

import org.springframework.stereotype.Service;

import com.store.order.vo.OrderRequest;
import com.store.order.vo.OrderResponse;

@Service
public interface OrderService {

	public String saveOrder(OrderRequest orderRequest);

	public OrderResponse getOrderById(Long orderId);
}