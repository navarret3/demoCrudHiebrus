package com.javinava.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javinava.demo.controller.product.ProductController;
import com.javinava.demo.controller.product.ProductDTO;
import com.javinava.demo.model.ProductMother;
import com.javinava.demo.model.product.ProductServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	@Autowired
	private ProductController controller;

	@MockBean
	ProductServiceImpl productService;
	
	@Test
	void getAllWithResults() throws Exception {

		Mockito.when(productService.getAll()).thenReturn(ProductMother.products);

		ResponseEntity<List<ProductDTO>> productList = controller.getAllProducts();

		assertThat(productList.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void findById() throws Exception {

		Mockito.when(productService.getById(Mockito.any())).thenReturn(ProductMother.PRODUCT_1);

		assertThat(controller.getById(1l).getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	void create() throws Exception {
		Mockito.when(productService.createProduct(Mockito.any())).thenReturn(ProductMother.PRODUCT_DTO_1);

		ResponseEntity<ProductDTO> product = controller.createProduct(ProductMother.PRODUCT_DTO_1);

		assertThat(product.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	void update() throws Exception {
		Mockito.when(productService.updateProduct(Mockito.anyLong(), Mockito.any())).thenReturn(ProductMother.PRODUCT_3);

		ResponseEntity<ProductDTO> product = controller.updateProduct(2l,ProductMother.PRODUCT_DTO_3);

		assertThat(product.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	void delete() throws Exception {
		Mockito.doNothing().when(productService).deleteProduct(1l);
		
		assertThat(controller.deleteProduct(2l).getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}
}
