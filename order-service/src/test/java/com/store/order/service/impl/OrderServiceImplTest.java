package com.store.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.store.order.dto.OrderDTO;
import com.store.order.dto.PaymentDTO;
import com.store.order.exceptions.ResourceNotFoundException;
import com.store.order.feignClients.BankFeignClient;
import com.store.order.feignClients.ProductFeignClient;
import com.store.order.feignClients.UserFeignClient;
import com.store.order.model.OrderItem;
import com.store.order.model.Orders;
import com.store.order.repository.OrderRepository;
import com.store.order.service.OrderService;
import com.store.order.vo.OrderRequest;
import com.store.order.vo.ProductRequest;

@ExtendWith(SpringExtension.class)
public class OrderServiceImplTest {
	
	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	@Mock
	private OrderService orderService;
	@Mock
	private ProductFeignClient productFeignClient;
	@Mock
	private UserFeignClient userFeignClient;
	@Mock
	private BankFeignClient bankFeignClient;
	@Mock
	private OrderRepository orderRepository;
	
	@Test
	public void testSaveOrder() throws ResourceNotFoundException {
		Double grandTotal = (double) 0;
		List<ProductRequest> listProductRequest = new ArrayList<ProductRequest>();
		ProductRequest productRequest = new ProductRequest();
		productRequest.setProductId(1L);
		productRequest.setQuantity(1);
		listProductRequest.add(productRequest);
		
		OrderRequest orderRequest = new OrderRequest();
		
		userFeignClient.getUser(1L);

//		List<ProductRequest> products = orderRequest.getProducts();
		List<Double> priceList = new ArrayList<Double>();
		List<OrderItem> listOrderItem = new ArrayList<OrderItem>();

		for (ProductRequest product : listProductRequest) {
			OrderItem orderItem = new OrderItem();
			listOrderItem.add(orderItem);
			priceList = productFeignClient.getProductById(product.getProductId());
			for (Double priceLists : priceList) {
				grandTotal+= priceLists*product.getQuantity();
				orderItem.setProductPrice(priceLists);
				orderItem.setProductId(product.getProductId());
				orderItem.setQuantity(product.getQuantity());
			}
		}			
		OrderDTO orderDTO = new OrderDTO(orderRequest.getUserId(),listOrderItem);
		Orders orders = new Orders(orderDTO);
		Mockito.when(orderRepository.save(orders)).thenReturn(orders);
		orderService.saveOrder(orderRequest);
		bankFeignClient.checkAccountNumber(orderRequest.getAccountNumber());
		PaymentDTO paymentDTO = new PaymentDTO(orderRequest.getAccountNumber(), grandTotal);
		PaymentDTO payment = bankFeignClient.transfer(paymentDTO);
		assertNotNull(payment);
	}
}
