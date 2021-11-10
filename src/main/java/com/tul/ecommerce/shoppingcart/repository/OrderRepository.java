package com.tul.ecommerce.shoppingcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.tul.ecommerce.shoppingcart.entity.Order;
import com.tul.ecommerce.shoppingcart.entity.OrderState;

@Service
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT o FROM Order o WHERE o.idUser = ?1 and o.state = ?2")
	Optional<Order> findByIdUserAndByState(Long id, OrderState state);
	

}
