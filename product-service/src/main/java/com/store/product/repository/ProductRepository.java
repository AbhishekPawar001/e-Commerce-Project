package com.store.product.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.store.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByProductNameContainsOrderByProductNameAsc(@Param("productName") String productName, Pageable pageable);

	List<Product> findByProductId(Long productId);


}
