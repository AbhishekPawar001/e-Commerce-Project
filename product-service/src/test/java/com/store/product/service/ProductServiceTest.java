package com.store.product.service;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.store.product.exceptions.ProductNotFoundException;
import com.store.product.model.Product;
import com.store.product.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService productService;
	@Mock
	private ProductRepository productRepository;
	
	@Test
	public void testGetProductByProductName() throws ProductNotFoundException{
		List<Product> products = new ArrayList<Product>();
		Pageable pageable = PageRequest.of(0, 3);
		Mockito.when(productRepository.findByProductNameContainsOrderByProductNameAsc("prod", pageable)).thenReturn(products);
		List<Product> prodList = productService.getProductByProductName("prod", pageable);
		System.out.println(prodList);
		assertNotNull(prodList);
	}
	
	@Test
	public void testGetProductById() throws ProductNotFoundException {
		List<Product> products = new ArrayList<Product>();
		Mockito.when(productRepository.findByProductId(1L)).thenReturn(products);
		List<Product> product = productService.getAllProducts(1L);
		assertNotNull(product);
	}
	
}
