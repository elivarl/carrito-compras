package com.tul.ecommerce.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tul.ecommerce.shoppingcart.entity.Order;
import com.tul.ecommerce.shoppingcart.entity.OrderDetail;
import com.tul.ecommerce.shoppingcart.exception.CustomAllException;
import com.tul.ecommerce.shoppingcart.repository.OrderDetailRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class OrderDetailService {
	
	private final OrderDetailRepository orderDetailRepository;
	
	public OrderDetail createOrderDetail(OrderDetail orderDetail) {
		return orderDetailRepository.save(orderDetail);
	}
	
	public OrderDetail findOrderDetailById(Long id) {
		return orderDetailRepository.findById(id).orElseThrow(
				()-> new CustomAllException("Order Detail id: "+id+" not found")
				);
	}
	
	public List<OrderDetail> findAllByOrder(Order order){
		return orderDetailRepository.findByCart(order);
	}
	
	public void deleteOrderDetailById(Long id) {
		OrderDetail orderDetail= orderDetailRepository.findById(id).orElseThrow( ()-> new  CustomAllException("Order Detail id: "+id+" not found by delete"));
		orderDetailRepository.deleteById(id);
		
	}

}
