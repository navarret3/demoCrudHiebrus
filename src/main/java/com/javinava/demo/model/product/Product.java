package com.javinava.demo.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "products")
@SequenceGenerator(name = "seq_prod", sequenceName = "seq_prod", allocationSize = 1)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prod")
	@Column(name = "prod_id")
	private Long id;
	 
	@Column(name = "prod_name")
    private String name;
	
	@Column(name = "prod_description")
    private String description;
	
}
