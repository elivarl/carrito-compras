package com.tul.ecommerce.shoppingcart.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.tul.ecommerce.shoppingcart.entity.Order;
import com.tul.ecommerce.shoppingcart.entity.OrderDetail;

@Service
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	List<OrderDetail> findByOrder(Order order);

	@Transactional
	@Modifying
	@Query("DELETE FROM OrderDetail od WHERE od.order.id=:cart and od.product.id=:product")
	void deleteByOrderAndProduct(@Param("cart") Long cart, @Param("product") Long product);

}
