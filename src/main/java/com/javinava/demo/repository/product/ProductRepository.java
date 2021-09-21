package com.javinava.demo.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javinava.demo.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	 List<Product> findByName(String name);
	 
	 List<Product> findByDescription(String description);
	 
}
