package com.store.order.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.store.order.vo.Product;

@FeignClient(name = "http://PRODUCT-SERVICE/products")
public interface ProductFeignClient {
	
	@GetMapping("/product/{id}")
	public List<Double> getProductById(@PathVariable("id") Long productId);
	
	@GetMapping("/allProduct/{id}")
	public List<Product> getAllProductById(@PathVariable("id") Long productId);
		
}
