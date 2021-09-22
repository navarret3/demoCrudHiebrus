package com.javinava.demo.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javinava.demo.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	 List<Product> findByName(String name);
	 
	 List<Product> findByDescription(String description);
	 
	 @Query("FROM Product product WHERE product.name = ?1 AND product.description = ?2")
	 List<Product> findByBoth(String name, String description);
	 
}
