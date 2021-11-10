package com.tul.ecommerce.shoppingcart.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tul.ecommerce.shoppingcart.dto.AddCartRequest;
import com.tul.ecommerce.shoppingcart.entity.Order;
import com.tul.ecommerce.shoppingcart.entity.OrderDetail;
import com.tul.ecommerce.shoppingcart.entity.OrderState;
import com.tul.ecommerce.shoppingcart.entity.Product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ShoppingCartService {
	
	private final OrderService orderService;
	private final OrderDetailService orderDetailService;
	
	private final ProductService productService;
	
	
	@Transactional
	public void addProductCart(AddCartRequest addCartRequest) {
		Order order = new Order(); 
		
		
		Optional<Order>  orderOptionalUserAndState= orderService.findByIdUserAndByState(addCartRequest.getIdUser(), OrderState.PENDING);
				
		if (!orderOptionalUserAndState.isPresent()) {
			order.setIdUser(addCartRequest.getIdUser());
			order.setState(OrderState.PENDING);
			order.setTotal(0);
			//creamos la orden
			order= orderService.createOrder(order);
			savedDetail(addCartRequest, order);
			
			
		}else {
			//solo creamos el detail
			order= orderOptionalUserAndState.get();
			savedDetail(addCartRequest, order);
			
		}
		
		
	}
	
	//guarda el detail
	public void savedDetail(AddCartRequest addCartRequest, Order order) {
		Product product = new Product();
		OrderDetail detail = new OrderDetail();
		
		product= productService.findProductByIdForDetail(addCartRequest.getIdProduct());
		
		//creamos el detail
		detail.setCart(order);
		detail.setDescription(product.getDescription());
		detail.setProduct(product);
		detail.setUnits(addCartRequest.getUnits());
		detail.setTotal(detail.getUnits()*product.getPrice());	
		
		//guardamos el detail
		orderDetailService.createOrderDetail(detail);
		
	}

}
