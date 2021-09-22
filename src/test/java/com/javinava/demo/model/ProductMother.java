package com.javinava.demo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.javinava.demo.controller.product.ProductDTO;
import com.javinava.demo.model.product.Product;

public class ProductMother {
	public static Product PRODUCT_1 = new Product(1l, "Pera", "Viene del peral");
	public static Product PRODUCT_2 = new Product(2l, "Piña", "Pincha");
	public static Product PRODUCT_3 = new Product(3l, "Manzana", "Lo mejor");
	public static Product PRODUCT_4 = new Product(3l, "Mandarina", "Una naranja pequeñita");
	
	public static ProductDTO PRODUCT_DTO_1 = new ProductDTO(1l, "Pera", "Viene del peral");
	public static ProductDTO PRODUCT_DTO_2 = new ProductDTO(2l, "Piña", "Pincha");
	public static ProductDTO PRODUCT_DTO_3 = new ProductDTO(3l, "Manzana", "Lo mejor");
	public static ProductDTO PRODUCT_DTO_4 = new ProductDTO(3l, "Mandarina", "Una naranja pequeñita");
	
	public static Optional<Product> PRODUCT_5 = Optional.of(new Product(5l, "Naranja", "Mandarina grande"));
	public static List<Product> products = new ArrayList<>(Arrays.asList(ProductMother.PRODUCT_1, ProductMother.PRODUCT_2, ProductMother.PRODUCT_3, ProductMother.PRODUCT_4));
}
