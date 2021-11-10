package com.tul.ecommerce.shoppingcart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tul.ecommerce.shoppingcart.dto.ProductCartResponse;
import com.tul.ecommerce.shoppingcart.entity.OrderDetail;


@Mapper(componentModel = "spring")
public abstract class DetailMapper {
	
	
	@Mapping(target = "units", source = "detail.units")
	@Mapping(target = "product", source = "detail.product.name")
	@Mapping(target = "price", source = "detail.product.price")
	@Mapping(target = "total", source = "detail.total")
	
	public abstract ProductCartResponse orderDetailToProductCartResponse(OrderDetail  detail);

}
