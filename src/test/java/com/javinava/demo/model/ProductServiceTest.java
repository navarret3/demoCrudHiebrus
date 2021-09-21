package com.javinava.demo.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.javinava.demo.model.product.Product;
import com.javinava.demo.model.product.ProductService;
import com.javinava.demo.repository.product.ProductRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Autowired
	ProductService service;
	
    @MockBean
    ProductRepository productRepository;
    
    Product PRODUCT_1 = new Product(1l, "Pera", "Viene del peral");
    Product PRODUCT_2 = new Product(2l, "Piña", "Pincha");
    Product PRODUCT_3 = new Product(3l, "Manzana", "Lo mejor");
    Product PRODUCT_4 = new Product(3l, "Mandarina", "Una naranja pequeñita");
    Optional<Product> PRODUCT_5 = Optional.of(new Product(5l, "Naranja", "Mandarina grande"));
    List<Product> productList = new ArrayList<>(Arrays.asList(PRODUCT_1, PRODUCT_2, PRODUCT_3, PRODUCT_4));
    
    @Test
    public void getById() throws Exception {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(PRODUCT_5);
        
        Optional<Product> product = Optional.of(service.getById(1l));
        
    	assertThat(product).isEqualTo(PRODUCT_5);
    }
    
    @Test
    public void getByName() throws Exception {
    	List<Product> products = new ArrayList<>(Arrays.asList(PRODUCT_1));
    	
        Mockito.when(productRepository.findByName(Mockito.anyString())).thenReturn(products);
        
        List<Product> productList = service.getByName("Pera");
        
    	assertThat(productList).size().isEqualTo(1);
    }
    
    @Test
    public void getByDescription() throws Exception {
    	List<Product> products = new ArrayList<>(Arrays.asList(PRODUCT_2));
    	
        Mockito.when(productRepository.findByDescription(Mockito.anyString())).thenReturn(products);
        
        List<Product> productList = service.getByDescription("Pincha");
        
    	assertThat(productList).size().isEqualTo(1);
    }
    
    @Test
    public void getAll() throws Exception {
    	List<Product> products = new ArrayList<>(Arrays.asList(PRODUCT_1, PRODUCT_2, PRODUCT_3, PRODUCT_4));
        
        Mockito.when(productRepository.findAll()).thenReturn(products);
        
        List<Product> list = service.getAll();
    	
    	assertThat(list).size().isGreaterThan(0);
    }
    
    @Test
    public void create() throws Exception {
        
    	Mockito.when(productRepository.save(Mockito.any())).thenReturn(PRODUCT_3);
        
        Product product = service.createProduct(PRODUCT_3);
    	
    	assertThat(product).isEqualTo(PRODUCT_3);
    }
    
    @Test
    public void update() throws Exception {
        
    	Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(PRODUCT_5);
    	
    	Mockito.when(productRepository.save(Mockito.any())).thenReturn(PRODUCT_1);
        
    	Optional<Product> product = Optional.of(service.updateProduct(5l,PRODUCT_1));
    	
    	assertThat(product.get().getName()).isEqualTo(PRODUCT_1.getName());
    }
    
    @Test
    public void delete() throws Exception {
        
    	Mockito.doNothing().when(productRepository).deleteById(1l);
    	
        service.deleteProduct(1l);
        
        verify(productRepository,times(1)).deleteById(1l);
    }
}
