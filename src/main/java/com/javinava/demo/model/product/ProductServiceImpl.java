package com.javinava.demo.model.product;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javinava.demo.controller.product.ProductDTO;
import com.javinava.demo.exceptions.ProductGenericException;
import com.javinava.demo.exceptions.ProductNotFoundException;
import com.javinava.demo.repository.product.ProductRepository;
import com.javinava.demo.utils.ProductUtils;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Transactional
	public Product getById(Long id) throws ProductNotFoundException {
		return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
	}

	@Transactional
	public List<Product> getByParams(String name, String description) {

		List<Product> list = new ArrayList<>();

		if (name != null && description != null) {
			list = repository.findByBoth(name, description);
		} else if (name != null) {
			list = repository.findByName(name);
		} else if (description != null) {
			list = repository.findByDescription(description);
		}

		if (list.isEmpty()) {
			throw new ProductNotFoundException();
		}

		return list;
	}

	@Transactional
	public List<Product> getAll() {
		List<Product> list = repository.findAll();

		if (list.isEmpty()) {
			throw new ProductNotFoundException();
		}

		return list;
	}

	@Transactional
	public ProductDTO createProduct(ProductDTO dto) throws ProductGenericException {

		Product entity = modelMapper.map(dto, Product.class);

		if (validateProduct(entity)) {
			return ProductUtils.entityToDto(repository.create(entity));
		}

		throw new ProductGenericException("Record cannot be created.");
	}

	@Transactional
	public Product updateProduct(Long id, ProductDTO dtoUpdated) {

		try {
			if (repository.findById(id).isPresent()) {
				Product product = repository.findById(id).get();

				if (dtoUpdated.getName() != null) {
					product.setName(dtoUpdated.getName());
				}

				if (dtoUpdated.getDescription() != null) {
					product.setDescription(dtoUpdated.getDescription());
				}

				return repository.update(product);
			} else {
				throw new ProductNotFoundException();
			}
		} catch (NullPointerException npe) {
			throw new ProductNotFoundException();
		}

	}

	@Transactional
	public void deleteProduct(Long id) {
		if (repository.delete(id) == null) {
			throw new ProductNotFoundException();
		}
	}

	private boolean validateProduct(Product product) {
		return product.getName() != null && product.getDescription() != null;
	}
}
