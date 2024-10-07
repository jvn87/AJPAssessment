package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Product;
import com.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
    // Create a new product
    public Product createProduct(Product product) {
    	return productRepository.save(product);
    }

	//Get product by name
	public Product getProductByName(String name) {
		return productRepository.findProductByName(name);
	}
	//Get product under price
	public List<Product> getProductUnderMaxPrice(Double maxPrice) {
		return productRepository.findProductsByMaxPrice(maxPrice);
	}
	//Get product by size
	public List<Product> getProductBySize(Integer size) {
		return productRepository.findProductsBySize(size);
	}
}
