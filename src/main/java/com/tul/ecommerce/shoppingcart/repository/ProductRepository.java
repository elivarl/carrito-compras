package com.tul.ecommerce.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tul.ecommerce.shoppingcart.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
