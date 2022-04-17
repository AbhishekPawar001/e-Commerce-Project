package com.store.product.service;

import java.time.LocalDateTime;
//import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.store.product.model.Product;
import com.store.product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getProductByProductName(String productName, Pageable pageable) {
		return productRepository.findByProductNameContainsOrderByProductNameAsc(productName, pageable);
	}

	public List<Product> getProductById(Long productId) {
		return productRepository.findByProductId(productId);
	}

	public List<Product> getAllProducts(Long productId) {
		return productRepository.findByProductId(productId);
	}

//	public Product saveProduct(Product product) {
//		product.setTimestamp(LocalDateTime.now());
//		return productRepository.save(product);
//	}
}
