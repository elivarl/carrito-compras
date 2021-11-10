package com.tul.ecommerce.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tul.ecommerce.shoppingcart.entity.Order;
import com.tul.ecommerce.shoppingcart.entity.OrderDetail;

@Service
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	List<OrderDetail> findByCart(Order cart );

}
