package com.repository;

import com.entity.OrdersDetails;	
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDetailsRepository extends JpaRepository<OrdersDetails, Long> {
	
	// Find order details by order ID
	@Query("SELECT od FROM OrdersDetails od WHERE od.order.id = :orderId") // Corrected this line
	List<OrdersDetails> findOrderDetailsByOrderId(@Param("orderId") Long orderId);
	
	// Find order details by product ID
	@Query("SELECT od FROM OrdersDetails od WHERE od.productId = :productId")
	List<OrdersDetails> findAllByProductId(@Param("productId") Long productId);
}
