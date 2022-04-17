package com.store.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.order.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

}
