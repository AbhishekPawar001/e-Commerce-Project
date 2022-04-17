package com.store.product.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.store.product.model.Product;
import com.store.product.service.ProductService;

@Service
public class ProductServiceImplementation {
	
	@Autowired
	private ProductService productService;
	
	public List<Product> getProductByProductName(String productName, Integer page) {
		Pageable pageable1 = PageRequest.of(page, 3, Sort.by(Direction.ASC, "productName"));
		return productService.getProductByProductName(productName, pageable1);
	}
	
	public List<Double> getProductById(Long productId) {
		List<Product> products = productService.getProductById(productId);
		List<Double> priceList = new ArrayList<Double>();
		for(Product productList : products) {
			priceList.add(productList.getPrice());
		}
		return priceList;
	}

	public List<Product> getAllProductById(Long productId) {
		return productService.getAllProducts(productId);
	}

	
//	public Product saveProduct(Product product) {
//		product.setTimestamp(LocalDateTime.now());
//		return productService.saveProduct(product);
//	}
}
