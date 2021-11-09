package com.tul.ecommerce.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tul.ecommerce.shoppingcart.entity.Cart;

@Service
public interface CartRepository extends JpaRepository<Cart, Long> {

}
