package com.store.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.store.product.exceptions.ProductNotFoundException;
import com.store.product.exceptions.ResourceNotFoundException;
import com.store.product.model.Product;
import com.store.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl {
	
	@Autowired
	private ProductService productService;
	
	public List<Product> getProductByProductName(String productName, Integer page) throws ProductNotFoundException{
		log.info("getting products from database in pagination format by passing page number and product name");
		Pageable pageable1 = PageRequest.of(page, 3, Sort.by(Direction.ASC, "productName"));
		List<Product> productList = productService.getProductByProductName(productName, pageable1);
		if(productList.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		} else {
			return productList;
		}
	}
	
	public List<Double> getProductById(Long productId) throws ProductNotFoundException {
		log.info("getProductById method is used by the order service to get the price of product");
		List<Product> products = productService.getProductById(productId);
		List<Double> priceList = new ArrayList<Double>();
		if(products.isEmpty()) {
			return null;
		} else {
			for(Product productList : products) {
				priceList.add(productList.getPrice());
			}
		}
		return priceList;
	}

	public List<Product> getAllProductById(Long productId) throws ProductNotFoundException {
		log.info("getAllProductById method used by order service to get the products for response");
		List<Product> productsList = productService.getAllProducts(productId);
		if(productsList.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		} else {
			return productsList;
		}
	}
}
