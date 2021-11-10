package com.tul.ecommerce.shoppingcart.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tul.ecommerce.shoppingcart.dto.AddCartRequest;
import com.tul.ecommerce.shoppingcart.service.ShoppingCartService;

import lombok.AllArgsConstructor;

import static com.tul.ecommerce.shoppingcart.utility.Constants.URL_API_SHOPPING_CART;

@RestController
@RequestMapping(URL_API_SHOPPING_CART)
@AllArgsConstructor
public class ShoppingCartController {
	private final ShoppingCartService cartService;
	
	@PostMapping
	public void addProductCart(@RequestBody AddCartRequest addCartRequest ) {
		cartService.addProductCart(addCartRequest);
	}
	
	

}
