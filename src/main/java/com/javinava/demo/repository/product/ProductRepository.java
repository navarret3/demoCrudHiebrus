package com.javinava.demo.repository.product;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javinava.demo.model.product.Product;

@Service
public class ProductRepository {
	private static ConcurrentHashMap<Long, Product> productStorage = new ConcurrentHashMap<>();

	public Optional<Product> findById(Long id) {
		return Optional.of(productStorage.get(id));
	}
	
	public List<Product> findByName(String name) {
		return productStorage
				.values()
				.stream()
				.filter(p -> p.getName().equals(name))
				.collect(Collectors.toList());
	}
	
	public List<Product> findByDescription(String description) {
		return productStorage
				.values()
				.stream()
				.filter(p -> p.getDescription().equals(description))
				.collect(Collectors.toList());
	}

	public List<Product> findByBoth(String name, String description) {
		return productStorage
				.values()
				.stream()
				.filter(p -> p.getName().equals(name) && p.getDescription().equals(description))
				.collect(Collectors.toList());
	}

	public List<Product> findAll() {
		return productStorage.values().stream().collect(Collectors.toList());
	}

	public Product create(Product product) {
		productStorage.put(product.getId(), product);
		
		return product;
	}
	
	public Product update(Product product) {
		return productStorage.replace(product.getId(), product);
    }
	
	public Product delete(Long id) {
		Product p = productStorage.get(id);
		productStorage.remove(id);

        return p;
    }
}
