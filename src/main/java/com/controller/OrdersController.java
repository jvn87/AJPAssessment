package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Orders;
import com.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	    
    // Create a new order
    // curl -X POST http://localhost:9191/orders -H "Content-Type: application/json" -d "{\"userId\": 1, \"orderDate\": \"2024-10-05\", \"totalAmount\": 99.99}"
    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        // Validate mandatory fields: userId and totalAmount must be present and valid
		if (order.getUserId() == null || order.getAmount() <= 0) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if invalid
		}
    	        
        // Proceed with creating the order
        Orders createdOrder = ordersService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    // Get orders by user ID
    // curl -X GET http://localhost:9191/orders/user/1
    @GetMapping("/user/{userId}")
	public ResponseEntity<List<Orders>> getOrdersByUserId(@PathVariable Long userId) {
        List<Orders> orders = ordersService.getOrdersByUserId(userId);
        if (orders.isEmpty()) {
        	return ResponseEntity.noContent().build(); // Return 204 if no orders found
        }
        return ResponseEntity.ok(orders); // Return 200 with the list of orders
    }
            
    // Get all orders
    // curl -X GET http://localhost:9191/orders
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
    	List<Orders> orders = ordersService.getAllOrders();
    	if (orders.isEmpty()) {
    		return ResponseEntity.noContent().build(); // Return 204 if no orders found
    	}
    	return ResponseEntity.ok(orders); // Return 200 with the list of all orders
    }
}	