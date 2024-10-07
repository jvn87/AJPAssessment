package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Product;
import com.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    
    // Get product by name
    // curl -X GET http://localhost:9191/api/products/name/ProductName
    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productService.getProductByName(name);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Get products below maximum price
    // curl -X GET http://localhost:9191/api/products/under-price/100.00
    @GetMapping("/under-price/{maxPrice}")
    public ResponseEntity<List<Product>> getProductsUnderMaxPrice(@PathVariable Double maxPrice) {
        List<Product> products = productService.getProductUnderMaxPrice(maxPrice);
        if(products != null && !products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }
    
    // Get products by size
    // curl -X GET http://localhost:9191/api/products/size/42
    @GetMapping("/size/{size}") 
    public ResponseEntity<List<Product>> getProductsBySize(@PathVariable Integer size) {
        List<Product> products = productService.getProductBySize(size);
        if(products != null && !products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }    
}
