package com.service;

import com.entity.OrdersDetails;	
import com.repository.OrdersDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersDetailsService {
	
	@Autowired
	private OrdersDetailsRepository ordersDetailsRepository;
	
	//Create order detail
	public OrdersDetails createOrderDetail(OrdersDetails orderDetail) {
		return ordersDetailsRepository.save(orderDetail);
	}
	//Get order details by order ID
	public List<OrdersDetails> getOrderDetailsByOrderId(Long orderId) {
		return ordersDetailsRepository.findOrderDetailsByOrderId(orderId);
	}
} 

