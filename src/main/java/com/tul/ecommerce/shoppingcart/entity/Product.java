package com.tul.ecommerce.shoppingcart.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "products")
public class Product {
	@Id
	private Long id;
	@NotBlank(message = "El campo nombre es requerido" )
	private String name;
	@NotBlank(message = "El campo descripción es requerido")
	private String description;
	@NotBlank(message = "El campo precio es requerido")
	private double price;
	@Enumerated(value = EnumType.STRING)
	private ProductType productType;
	

}
