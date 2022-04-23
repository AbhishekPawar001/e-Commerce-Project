package com.store.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.store.order.dto.OrderDTO;
import com.store.order.dto.PaymentDTO;
import com.store.order.dto.ProductDTO;
import com.store.order.exceptions.ResourceNotFoundException;
import com.store.order.feignClients.BankFeignClient;
import com.store.order.feignClients.ProductFeignClient;
import com.store.order.feignClients.UserFeignClient;
import com.store.order.model.OrderItem;
import com.store.order.model.Orders;
import com.store.order.repository.OrderRepository;
import com.store.order.service.OrderService;
import com.store.order.vo.OrderRequest;
import com.store.order.vo.OrderResponse;
import com.store.order.vo.Product;
import com.store.order.vo.ProductRequest;
import com.store.order.vo.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductFeignClient productFeignClient;
	@Autowired
	private UserFeignClient userFeignClient;
	@Autowired
	private BankFeignClient bankFeignClient;
	
	@Override
	public String saveOrder(OrderRequest orderRequest) throws ResourceNotFoundException {
		Double grandTotal = (double) 0;
		String msg = "";
		log.info("Finding the user from User Service APi using feignClient");
		User user = userFeignClient.getUser(orderRequest.getUserId());
		if(user != null) {
			List<ProductRequest> products = orderRequest.getProducts();
			List<Double> priceList = new ArrayList<Double>();
			List<OrderItem> list = new ArrayList<OrderItem>();

			log.info("Iterate the products and passing product id using feignclient to get the price of related products");
			for (ProductRequest product : products) {
				OrderItem orderItem = new OrderItem();
				list.add(orderItem);
				priceList = productFeignClient.getProductById(product.getProductId());
				if(priceList == null) {
					throw new ResourceNotFoundException("Product", "id", product.getProductId());
				} else {
					for (Double priceLists : priceList) {
						grandTotal+= priceLists*product.getQuantity();
						orderItem.setProductPrice(priceLists);
						orderItem.setProductId(product.getProductId());
						orderItem.setQuantity(product.getQuantity());
					}
				}
			}			
			OrderDTO orderDTO = new OrderDTO(orderRequest.getUserId(),list);
			Orders orders = new Orders(orderDTO);
			log.info("save order instance in Database");
			Orders order = orderRepository.save(orders);
			if (order == null) {
				msg = "Order not placed please try again";
			} else {
				String value = bankFeignClient.checkAccountNumber(orderRequest.getAccountNumber());
				if(value == null) {
					throw new ResourceNotFoundException("Account!", "AccountNumber", orderRequest.getAccountNumber());
				} else {
					PaymentDTO paymentDTO = new PaymentDTO(orderRequest.getAccountNumber(), grandTotal);
					PaymentDTO payment = bankFeignClient.transfer(paymentDTO);
					if(payment == null) {
						msg = "payment failed please check your account details";
					} else {
						msg = "Order placed successfully for Order Id "+order.getOrderId(); 
					}
				}
			}
		} else {
			throw new ResourceNotFoundException("User", "id", orderRequest.getUserId());
		}
		return msg;
	}
	
	@Override
	public OrderResponse getOrderById(Long orderId) throws ResourceNotFoundException {
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		OrderResponse orderResponse;
		Double grandTotal = (double) 0;
		log.info("finding orders from Database");
		Optional<Orders> order = orderRepository.findByOrderId(orderId);
		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
		if(order.isPresent()) {
			log.info("check whether the order is present in database or not");
			Orders orders = order.get();
			log.info("using feignClient get all the produtcs from database");
			for(OrderItem orderList : orders.getProducts()) {
				ProductDTO productDTO = new ProductDTO();
				productDTOList.add(productDTO);
				List<Product> productList = circuitBreaker.run(()-> productFeignClient.getAllProductById(orderList.getProductId()), throwable -> fallbackMethod());
				for(Product list : productList) {
					grandTotal+= list.getPrice()*orderList.getQuantity();
					productDTO.setProductId(list.getProductId());
					productDTO.setProductName(list.getProductName());
					productDTO.setProductDescription(list.getProductDescription());
					productDTO.setProductPrice(list.getPrice());
					productDTO.setQuantity(orderList.getQuantity());
				}
			}
			orderResponse = new OrderResponse(orders, grandTotal, productDTOList);
		} else {
			throw new ResourceNotFoundException("Order", "id", orderId);
		}
		return orderResponse;
	}
	
	public List<Product> fallbackMethod() {
		throw new ResourceNotFoundException("Currently Service is down please try again later");
	}
}