package com.store.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.product.model.Product;
import com.store.product.service.implementation.ProductServiceImplementation;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductServiceImplementation productServiceImplementation;
	
	@GetMapping("/{pname}/{page}")
	public List<Product> getProductByProductCode(@PathVariable("pname") String productName, @PathVariable("page") Integer page) {
		return productServiceImplementation.getProductByProductName(productName, page);
	}
	
	@GetMapping("/product/{pid}")
	public List<Double> getProductById(@PathVariable("pid") Long productId) {
		return productServiceImplementation.getProductById(productId);
	}
	
	@GetMapping("/allProduct/{id}")
	public List<Product> getAllProductById(@PathVariable("id") Long productId) {
		return productServiceImplementation.getAllProductById(productId);
	}
	
	
//	@PostMapping("/saveProduct")
//	public Product saveProduct(@RequestBody Product product) {
//		return productService.saveProduct(product);
//	}
}
