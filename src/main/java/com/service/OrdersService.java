package com.service;


import com.entity.Orders;
import com.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.OrdersRepository;

import java.util.List;


@Service
public class OrdersService {
	@Autowired
	private OrdersRepository ordersRepository;

	//Create new order
	public Orders createOrder(Orders order) {
		return ordersRepository.save(order);
	}		

	//Get order by user ID
	public List<Orders> getOrdersByUserId(Long userId) {
		return ordersRepository.findOrderById(userId);
	}

	//Get all orders
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
}
