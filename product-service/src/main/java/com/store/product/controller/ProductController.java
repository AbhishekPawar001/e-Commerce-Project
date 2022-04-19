package com.store.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.product.exceptions.ProductNotFoundException;
import com.store.product.model.Product;
import com.store.product.service.impl.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@GetMapping("/search/{pname}/{page}")
	public ResponseEntity<List<Product>> getProductByProductCode(@PathVariable("pname") String productName, @PathVariable("page") Integer page) throws ProductNotFoundException {
		log.info("searching products from database in pagination format by passing page number and product name");
		return new ResponseEntity<List<Product>>(productServiceImpl.getProductByProductName(productName, page), HttpStatus.OK);
	}
	
	@GetMapping("/product/{pid}")
	public ResponseEntity<List<Double>> getProductById(@PathVariable("pid") Long productId) throws ProductNotFoundException {
		log.info("getProductById method is used by the order service to get the price of product");
		return  new ResponseEntity<List<Double>>(productServiceImpl.getProductById(productId), HttpStatus.OK);
	}
	
	@GetMapping("/allProduct/{id}")
	public ResponseEntity<List<Product>> getAllProductById(@PathVariable("id") Long productId) throws ProductNotFoundException {
		log.info("getAllProductById method used by order service to get the products for respose");
		return  new ResponseEntity<List<Product>>(productServiceImpl.getAllProductById(productId), HttpStatus.OK);
	}

}
