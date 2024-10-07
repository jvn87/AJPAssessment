package com.repository;

import com.entity.Orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
	//Find all orders by user ID
	//@Query("SELECT o FROM Orders o WHERE o.userId = :userID")
	//Orders findAllByUserId(Long userId);
	
	//Find by order ID
	@Query("SELECT o FROM Orders o WHERE o.id = :orderId")
	List<Orders> findOrderById(Long orderId); 
}
