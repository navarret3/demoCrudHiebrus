package com.javinava.demo.controller.product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javinava.demo.model.product.Product;
import com.javinava.demo.model.product.ProductServiceImpl;
import com.javinava.demo.utils.ProductUtils;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	ProductServiceImpl productService;
	
	@GetMapping("{id}")
	public ResponseEntity<ProductDTO> getById(@PathVariable() Long id) {

		Product responseProduct = productService.getById(id);
		
		return new ResponseEntity<>(ProductUtils.entityToDto(responseProduct), HttpStatus.OK);
	}
	
	@GetMapping("/getByParams")
	public ResponseEntity<List<ProductDTO>> getByParams(@RequestParam(required = false) String name, @RequestParam(required = false) String description) {
		
		List<Product> productList = productService.getByParams(name, description);

		return new ResponseEntity<>(ProductUtils.entitiesToDtos(productList), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {

		List<Product> response = productService.getAll();

		return new ResponseEntity<>(ProductUtils.entitiesToDtos(response), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {

		ProductDTO response = productService.createProduct(dto);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Async
	@PostMapping("/async")
	public CompletableFuture<ProductDTO> createAsync(@RequestBody ProductDTO dto) {

		return CompletableFuture.supplyAsync(() -> productService.createProduct(dto));
	}

	@PutMapping("{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable() Long id, @RequestBody ProductDTO dto) {

		Product product = productService.updateProduct(id, dto);

		return new ResponseEntity<>(ProductUtils.entityToDto(product), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable() Long id) {
		
		productService.deleteProduct(id);

		return new ResponseEntity<>("Product " + id + " deleted.", HttpStatus.OK);
	}
}
