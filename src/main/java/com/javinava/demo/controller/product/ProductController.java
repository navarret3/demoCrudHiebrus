package com.javinava.demo.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javinava.demo.model.product.Product;
import com.javinava.demo.model.product.ProductService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("{id}")
	public ResponseEntity<Product> getById(@PathVariable() Long id) {
		try {
			Product responseProduct = productService.getById(id);

			return new ResponseEntity<>(responseProduct, HttpStatus.OK);
		} catch (NotFoundException e) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@GetMapping("/findByName/{name}")
	public ResponseEntity<List<Product>> getByName(@PathVariable() String name) {
		try {
			List<Product> productList = productService.getByName(name);

			if (productList.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(productList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@GetMapping("/findByDescription/{description}")
	public ResponseEntity<List<Product>> getByDescription(@PathVariable() String description) {
		try {
			List<Product> productList = productService.getByDescription(description);

			if (productList.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(productList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProducts() {
		try {
			List<Product> response = productService.getAll();

			if (response.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody Product entity) {
		try {
			Product response = productService.createProduct(entity);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable() Long id, @RequestBody Product entity) {
		try {
			Product product = productService.updateProduct(id, entity);
			
			if (product == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(product, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable() Long id) {
		try {
			productService.deleteProduct(id);

			return new ResponseEntity<>("Product " + id + " deleted.", HttpStatus.OK);
			
		} catch (EmptyResultDataAccessException em) {
			return new ResponseEntity<>("Product " + id + " doesnt exists.", HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
