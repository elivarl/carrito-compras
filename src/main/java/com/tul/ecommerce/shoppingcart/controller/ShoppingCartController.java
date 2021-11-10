package com.tul.ecommerce.shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tul.ecommerce.shoppingcart.dto.AddProductCartRequest;
import com.tul.ecommerce.shoppingcart.dto.CartResponse;
import com.tul.ecommerce.shoppingcart.dto.ProductCartResponse;
import com.tul.ecommerce.shoppingcart.service.ShoppingCartService;

import lombok.AllArgsConstructor;

import static com.tul.ecommerce.shoppingcart.utility.Constants.URL_API_SHOPPING_CART;

import java.util.List;

@RestController
@RequestMapping(URL_API_SHOPPING_CART)
@AllArgsConstructor
public class ShoppingCartController {
	private final ShoppingCartService cartService;

	@PostMapping
	public ResponseEntity<ProductCartResponse> addProductCart(
			@RequestBody AddProductCartRequest addProductCartRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addProductCart(addProductCartRequest));

	}

	@GetMapping("/{id}")
	public ResponseEntity<List<ProductCartResponse>> findCartByIdUser(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(cartService.findCartByIdUser(id));
	}

	@DeleteMapping("/{iduser}/{idproduct}")
	public void deleteProductCart(@PathVariable Long iduser, @PathVariable Long idproduct) {
		cartService.deleteProductCart(iduser, idproduct);
	}

	@GetMapping("/checkout/{iduser}")
	public ResponseEntity<CartResponse> checkoutByIdUser(@PathVariable Long iduser) {
		return ResponseEntity.status(HttpStatus.OK).body(cartService.checkout(iduser));
	}

}
