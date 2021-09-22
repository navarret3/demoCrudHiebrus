package com.javinava.demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.javinava.demo.controller.product.ProductDTO;
import com.javinava.demo.exceptions.ProductNotFoundException;
import com.javinava.demo.model.product.Product;

public final class ProductUtils {

	private static ModelMapper modelMapper = new ModelMapper();

	private ProductUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static ProductDTO entityToDto(Product product) {
		if(product != null) {
			return modelMapper.map(product, ProductDTO.class);
		}
		
		throw new ProductNotFoundException();
	}

	public static List<ProductDTO> entitiesToDtos(List<Product> productList) {
		List<ProductDTO> dtoList = new ArrayList<>();

		for (Product p : productList) {
			dtoList.add(modelMapper.map(p, ProductDTO.class));
		}

		return dtoList;
	}
}
