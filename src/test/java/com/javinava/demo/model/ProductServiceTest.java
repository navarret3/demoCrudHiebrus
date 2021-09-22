package com.javinava.demo.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javinava.demo.controller.product.ProductDTO;
import com.javinava.demo.model.product.Product;
import com.javinava.demo.model.product.ProductServiceImpl;
import com.javinava.demo.repository.product.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@InjectMocks
	ProductServiceImpl service;
	
    @Mock
    ProductRepository productRepository;
    
    @Test
    void getById() throws Exception {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(ProductMother.PRODUCT_5);
        
        Optional<Product> product = Optional.of(service.getById(1l));
        
    	assertThat(product).isEqualTo(ProductMother.PRODUCT_5);
    }
    
    @Test
    void getByParams() throws Exception {
    	List<Product> products = new ArrayList<>(Arrays.asList(ProductMother.PRODUCT_1));
    	
        Mockito.when(productRepository.findByBoth(Mockito.anyString(),Mockito.anyString())).thenReturn(products);
        
        List<Product> productList = service.getByParams("Pera", "desc");
        
    	assertThat(productList).size().isEqualTo(1);
    }
    
    @Test
    void getAll() throws Exception {
        
        Mockito.when(productRepository.findAll()).thenReturn(ProductMother.products);
        
        List<Product> list = service.getAll();
    	
    	assertThat(list).size().isPositive();
    }
    
    @Test
    void create() throws Exception {
        
    	Mockito.when(productRepository.save(Mockito.any())).thenReturn(ProductMother.PRODUCT_3);
        
        ProductDTO product = service.createProduct(ProductMother.PRODUCT_DTO_3);
    	
    	assertThat(product.getName()).isEqualTo(ProductMother.PRODUCT_DTO_3.getName());
    }
    
    @Test
    void update() throws Exception {
        
    	Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(ProductMother.PRODUCT_5);
    	
    	Mockito.when(productRepository.save(Mockito.any())).thenReturn(ProductMother.PRODUCT_1);
        
    	Optional<Product> product = Optional.of(service.updateProduct(5l,ProductMother.PRODUCT_DTO_1));
    	
    	assertThat(product.get().getName()).isEqualTo(ProductMother.PRODUCT_1.getName());
    }
    
    @Test
    void delete() throws Exception {
        
    	Mockito.doNothing().when(productRepository).deleteById(1l);
    	
        service.deleteProduct(1l);
        
        verify(productRepository,times(1)).deleteById(1l);
    }
}
