package com.javinava.demo.model.product;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javinava.demo.repository.product.ProductRepository;

import javassist.NotFoundException;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	@Transactional
	public Product getById(Long id) throws NotFoundException {
		return repository.findById(id).orElseThrow(() -> new NotFoundException(id.toString()));
	}
	
	@Transactional
	public List<Product> getByName(String name) throws NotFoundException {
		return repository.findByName(name);
	}
	
	@Transactional
	public List<Product> getByDescription(String description) throws NotFoundException {
		return repository.findByDescription(description);
	}

	@Transactional
	public List<Product> getAll() {
		return repository.findAll();
	}

	@Transactional
	public Product createProduct(Product entity) throws PersistenceException {
		if (validateProduct(entity)) {
			return repository.save(entity);
		}

		throw new PersistenceException("Record cannot be created.");
	}

	@Transactional
	public Product updateProduct(Long id, Product entityUpdated) {

		if (repository.findById(id).isPresent()){
			Product product = repository.findById(id).get();
			
			if(entityUpdated.getName() != null) {
				product.setName(entityUpdated.getName());
			}
			
			if(entityUpdated.getDescription() != null) {
				product.setDescription(entityUpdated.getDescription());
			}
			
			return repository.save(product);
		} else {
			entityUpdated = null;
		}
		
		return entityUpdated;
	}

	@Transactional
	public void deleteProduct(Long id) {
		repository.deleteById(id);
	}

	private boolean validateProduct(Product product) {
		return product.getName() != null && product.getDescription() != null;
	}

}
