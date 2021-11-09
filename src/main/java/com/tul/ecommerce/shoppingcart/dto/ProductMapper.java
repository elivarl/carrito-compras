package com.tul.ecommerce.shoppingcart.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tul.ecommerce.shoppingcart.entity.Product;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", source = "productRequest.name")
	@Mapping(target = "description", source = "productRequest.description")
	@Mapping(target = "price", source = "productRequest.price")
	@Mapping(target = "productType", source = "productRequest.productType")
	
	public abstract Product productRequestToProduct(ProductRequest productRequest);
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "price", source = "price")
	@Mapping(target = "productType", source = "productType")
	
	public abstract ProductResponse productToProductResponse (Product product);

}
