package com.store.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.order.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

	Optional<Orders> findByOrderId(Long orderId);

}
