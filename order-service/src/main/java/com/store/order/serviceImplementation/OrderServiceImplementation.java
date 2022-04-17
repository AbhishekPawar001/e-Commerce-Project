package com.store.order.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.order.ao.OrderRequest;
import com.store.order.ao.OrderResponse;
import com.store.order.ao.ProductRequest;
import com.store.order.dto.OrderDTO;
import com.store.order.dto.PaymentDTO;
import com.store.order.dto.ProductDTO;
import com.store.order.feignClients.Product;
import com.store.order.feignClients.ProductFeignClient;
import com.store.order.feignClients.UserFeignClient;
import com.store.order.model.OrderItem;
import com.store.order.model.Orders;
import com.store.order.repository.OrderRepository;
import com.store.order.service.OrderService;

@Service
public class OrderServiceImplementation implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Override
	public String saveOrder(OrderRequest orderRequest) {
		Double grandTotal = (double) 0;
		
		List<ProductRequest> products = orderRequest.getProducts();
		List<Double> priceList = new ArrayList<Double>();
		List<OrderItem> list = new ArrayList<OrderItem>();
		
		for (ProductRequest product : products) {
			OrderItem orderItem = new OrderItem();
			list.add(orderItem);
			priceList = productFeignClient.getProductById(product.getProductId());
			for (Double priceLists : priceList) {
				grandTotal+= priceLists*product.getQuantity();
				orderItem.setProductPrice(priceLists);
				orderItem.setProductId(product.getProductId());
				orderItem.setQuantity(product.getQuantity());
			}
		}
		OrderDTO orderDTO = new OrderDTO(orderRequest.getAccountNumber(),orderRequest.getUserId(),list);
		Orders orders = new Orders(orderDTO);
		PaymentDTO paymentDTO = new PaymentDTO(orderRequest.getAccountNumber(), grandTotal);
		userFeignClient.transfer(paymentDTO);
		Orders order = orderRepository.save(orders);
		
		return order == null ? "Order not placed please try again" : "Order placed successfully";
	}
	
	@Override
	public OrderResponse getOrderById(Long orderId) {
		OrderResponse orderResponse = null;
		Double grandTotal = (double) 0;
		Optional<Orders> order = orderRepository.findById(orderId);
		List<ProductDTO> productAOList = new ArrayList<ProductDTO>();
		if(order.isPresent()) {
			Orders orders = order.get();
			for(OrderItem orderList : orders.getProducts()) {
				ProductDTO productDTO = new ProductDTO();
				productAOList.add(productDTO);
				List<Product> productList = productFeignClient.getAllProductById(orderList.getProductId());
				for(Product list : productList) {
					grandTotal+= list.getPrice()*orderList.getQuantity();
					productDTO.setProductId(list.getProductId());
					productDTO.setProductName(list.getProductName());
					productDTO.setProductDescription(list.getProductDescription());
					productDTO.setProductPrice(list.getPrice());
					productDTO.setQuantity(orderList.getQuantity());
				}
			}
			orderResponse = new OrderResponse(orders, grandTotal, productAOList);
		}
		return orderResponse;
	}

}
