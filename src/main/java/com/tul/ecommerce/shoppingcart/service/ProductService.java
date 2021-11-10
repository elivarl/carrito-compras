package com.tul.ecommerce.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tul.ecommerce.shoppingcart.dto.ProductRequest;
import com.tul.ecommerce.shoppingcart.dto.ProductResponse;
import com.tul.ecommerce.shoppingcart.entity.Product;
import com.tul.ecommerce.shoppingcart.entity.ProductType;
import com.tul.ecommerce.shoppingcart.exception.CustomAllException;
import com.tul.ecommerce.shoppingcart.mapper.ProductMapper;
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
	
	//crear un producto/ actualizar
	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = productMapper.productRequestToProduct(productRequest);
		//validación tipo de producto(simple o descuento)
		if(productRequest.getProductType().equals(ProductType.DISCOUNT.toString())) {
			log.info("Tipo: {}", product.getProductType());
			product.setPrice(productRequest.getPrice()/2);
			productRepository.save(product);
		}
		productRepository.save(product);
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
				()-> new CustomAllException("Product id: "+id+" not found")
				);
		 return productMapper.productToProductResponse(product);
	}
	
	//eliminar un producto
	public ProductResponse deleteProductById(Long id) {
		Product product=productRepository.findById(id).orElseThrow(
				()-> new CustomAllException("Product id: "+id+" not found by delete")
				);		
		productRepository.deleteById(id);
		return productMapper.productToProductResponse(product);
	}
	
	//buscar un producto por id para usar en el detail
		public Product findProductByIdForDetail(Long id) {
			 Product product=productRepository.findById(id).orElseThrow(
					()-> new CustomAllException("Product id: "+id+" not found")
					);
			 return product;
		}
	
}
