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

import com.tul.ecommerce.shoppingcart.dto.ProductRequest;
import com.tul.ecommerce.shoppingcart.dto.ProductResponse;
import com.tul.ecommerce.shoppingcart.service.ProductService;

import lombok.AllArgsConstructor;

import static com.tul.ecommerce.shoppingcart.utility.Constants.URL_API_PRODUCTS;

import java.util.List;

@RestController
@RequestMapping(URL_API_PRODUCTS)
@AllArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductResponse> createProduct( @RequestBody ProductRequest productRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> findAllProducts(){
		return ResponseEntity.status(HttpStatus.OK).body(productService.findAllProducts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(productService.findProductById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductResponse> deleteProductById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.deleteProductById(id));
	}
	

}
