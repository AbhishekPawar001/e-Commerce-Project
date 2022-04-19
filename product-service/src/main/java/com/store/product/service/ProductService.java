package com.store.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.store.product.exceptions.ProductNotFoundException;
import com.store.product.model.Product;
import com.store.product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getProductByProductName(String productName, Pageable pageable) throws ProductNotFoundException {
		return productRepository.findByProductNameContainsOrderByProductNameAsc(productName, pageable);
	}

	public List<Product> getProductById(Long productId) throws ProductNotFoundException {
		return productRepository.findByProductId(productId);
	}

	public List<Product> getAllProducts(Long productId) throws ProductNotFoundException {
		return productRepository.findByProductId(productId);
	}
}
