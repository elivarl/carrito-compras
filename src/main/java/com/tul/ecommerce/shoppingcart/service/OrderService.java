package com.tul.ecommerce.shoppingcart.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tul.ecommerce.shoppingcart.entity.Order;
import com.tul.ecommerce.shoppingcart.entity.OrderState;
import com.tul.ecommerce.shoppingcart.exception.CustomAllException;
import com.tul.ecommerce.shoppingcart.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	
	public Order createOrder(Order order) {
		return orderRepository.save(order);		
	}
	
	public Order findOrderById(Long id) {
		return orderRepository.findById(id).orElseThrow(
				()-> new CustomAllException("Order id: "+id+" not found")
				);
	}
	
	public Optional<Order> findByIdUserAndByState (Long id, OrderState state) {
		return orderRepository.findByIdUserAndByState(id, state);
	}	
	
}
