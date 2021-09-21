package com.javinava.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javinava.demo.controller.product.ProductController;
import com.javinava.demo.model.product.Product;
import com.javinava.demo.model.product.ProductService;

import javassist.NotFoundException;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

	@Autowired
	private ProductController controller;

	@MockBean
	ProductService productService;

	Product PRODUCT_1 = new Product(1l, "Pera", "Viene del peral");
	Product PRODUCT_2 = new Product(2l, "Piña", "Pincha");
	Product PRODUCT_3 = new Product(3l, "Manzana", "Lo mejor");
	Product PRODUCT_4 = new Product(3l, "Mandarina", "Una naranja pequeñita");

	@Test
	public void getAllWithResults() throws Exception {
		List<Product> products = new ArrayList<>(Arrays.asList(PRODUCT_1, PRODUCT_2, PRODUCT_3, PRODUCT_4));

		Mockito.when(productService.getAll()).thenReturn(products);

		ResponseEntity<List<Product>> productList = controller.getAllProducts();

		assertThat(productList.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void getAllNoResults() throws Exception {

		Mockito.when(productService.getAll()).thenReturn(new ArrayList<>());

		ResponseEntity<List<Product>> productList = controller.getAllProducts();

		assertThat(productList.getStatusCodeValue()).isEqualTo(HttpStatus.NO_CONTENT.value());
	}

	@Test
	public void findByIdOk() throws Exception {

		Mockito.when(productService.getById(Mockito.any())).thenReturn(PRODUCT_1);

		assertThat(controller.getById(1l).getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void findByIdNoResult() throws Exception {

		Mockito.when(productService.getById(Mockito.any())).thenThrow(NotFoundException.class);

		assertThat(controller.getById(1l).getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void findByNameOk() throws Exception {
		List<Product> products = new ArrayList<>(Arrays.asList(PRODUCT_2));
		
		Mockito.when(productService.getByName(Mockito.any())).thenReturn(products);

		assertThat(controller.getByName("Piña").getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void findByNameNoResult() throws Exception {

		Mockito.when(productService.getByName(Mockito.any())).thenReturn(new ArrayList<>());

		assertThat(controller.getByName("Nothing").getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void findByDesriptionOk() throws Exception {
		List<Product> products = new ArrayList<>(Arrays.asList(PRODUCT_3));
		
		Mockito.when(productService.getByDescription(Mockito.any())).thenReturn(products);

		assertThat(controller.getByDescription("Manzana").getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void findByDescriptionNoResult() throws Exception {

		Mockito.when(productService.getByDescription(Mockito.any())).thenReturn(new ArrayList<>());

		assertThat(controller.getByDescription("Nothing").getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void createOk() throws Exception {
		Mockito.when(productService.createProduct(Mockito.any())).thenReturn(PRODUCT_1);

		ResponseEntity<Product> product = controller.createProduct(PRODUCT_1);

		assertThat(product.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	public void createError() throws Exception {
		Mockito.when(productService.createProduct(Mockito.any())).thenThrow(PersistenceException.class);

		ResponseEntity<Product> product = controller.createProduct(PRODUCT_1);

		assertThat(product.getStatusCodeValue()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	@Test
	public void updateOk() throws Exception {
		Mockito.when(productService.updateProduct(Mockito.anyLong(), Mockito.any())).thenReturn(PRODUCT_3);

		ResponseEntity<Product> product = controller.updateProduct(2l,PRODUCT_3);

		assertThat(product.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	public void updateNotFound() throws Exception {
		Mockito.when(productService.createProduct(Mockito.any())).thenReturn(null);

		ResponseEntity<Product> product = controller.updateProduct(2l,PRODUCT_1);

		assertThat(product.getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deleteOk() throws Exception {
		Mockito.doNothing().when(productService).deleteProduct(1l);
		
		assertThat(controller.deleteProduct(2l).getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}
}
