package com.tul.ecommerce.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tul.ecommerce.shoppingcart.ProductException;
import com.tul.ecommerce.shoppingcart.dto.ProductMapper;
import com.tul.ecommerce.shoppingcart.dto.ProductRequest;
import com.tul.ecommerce.shoppingcart.dto.ProductResponse;
import com.tul.ecommerce.shoppingcart.entity.Product;
import com.tul.ecommerce.shoppingcart.repository.ProductRepository;
import static java.util.stream.Collectors.toList;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService  {
	
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	
	//crear un producto
	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = productRepository.save(productMapper.productRequestToProduct(productRequest));		
		return productMapper.productToProductResponse(product);		
	}
	
	//buscar todos producto los productos
	public List<ProductResponse>  findAllProducts(){
		return productRepository.findAll().stream().map(
				productMapper::productToProductResponse
				).collect(toList());
	}
	
	//buscar un producto por id
	public ProductResponse findProductById(Long id) {
		 Product product=productRepository.findById(id).orElseThrow(
				()-> new ProductException("Product id: "+id+" not found")
				);
		 return productMapper.productToProductResponse(product);
	}
	
	//eliminar un producto
	public ProductResponse deleteProductById(Long id) {
		Product product=productRepository.findById(id).orElseThrow(
				()-> new ProductException("Product id: "+id+" not found by delete")
				);		
		productRepository.deleteById(id);
		return productMapper.productToProductResponse(product);
	}
	
}
