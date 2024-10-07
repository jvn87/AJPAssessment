package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	//Find product by name
	@Query("SELECT p FROM Product p WHERE p.name = :name")
	Product findProductByName(@Param("name") String name);
	
	//Find all products with price less than
	@Query("SELECT p FROM Product p WHERE p.price < :maxPrice")
	List<Product> findProductsByMaxPrice(@Param("maxPrice") Double maxPrice);
	
	//Find all products with size
	@Query("SELECT p FROM Product p WHERE p.size = :size")
	List<Product> findProductsBySize(@Param("size") Integer size);
}
