package com.javinava.demo.model.product;

import java.util.List;

import com.javinava.demo.controller.product.ProductDTO;

public interface ProductService {
	public Product getById(Long id);

	public List<Product> getAll();
	
	public List<Product> getByParams(String name, String description);

	public ProductDTO createProduct(ProductDTO dto);

	public Product updateProduct(Long id, ProductDTO dto);

	public void deleteProduct(Long id);
}
