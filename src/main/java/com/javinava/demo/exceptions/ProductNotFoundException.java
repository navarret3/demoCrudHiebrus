package com.javinava.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found")
public class ProductNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 599040180950292420L;

	public ProductNotFoundException() {
        super();
    }
	
	public ProductNotFoundException(String message) {
        super(message);
    }
}
