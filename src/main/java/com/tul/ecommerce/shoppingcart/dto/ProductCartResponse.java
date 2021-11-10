package com.tul.ecommerce.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductCartResponse {	
	private double units;
	private String product;
	private double price;
	private double total;
	
	
	

}
