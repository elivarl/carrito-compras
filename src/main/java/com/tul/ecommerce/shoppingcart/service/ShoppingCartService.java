package com.tul.ecommerce.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tul.ecommerce.shoppingcart.dto.AddProductCartRequest;
import com.tul.ecommerce.shoppingcart.dto.CartResponse;
import com.tul.ecommerce.shoppingcart.dto.ProductCartResponse;
import com.tul.ecommerce.shoppingcart.entity.Order;
import com.tul.ecommerce.shoppingcart.entity.OrderDetail;
import com.tul.ecommerce.shoppingcart.entity.OrderState;
import com.tul.ecommerce.shoppingcart.entity.Product;
import com.tul.ecommerce.shoppingcart.mapper.DetailMapper;
import static java.util.stream.Collectors.toList;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ShoppingCartService {

	private final OrderService orderService;
	private final OrderDetailService orderDetailService;

	private final ProductService productService;
	private final DetailMapper detailMapper;

	@Transactional
	public ProductCartResponse addProductCart(AddProductCartRequest addCartRequest) {
		Order order = new Order();

		Optional<Order> orderOptionalUserAndState = orderService.findByIdUserAndByState(addCartRequest.getIdUser(),
				OrderState.PENDING);

		if (!orderOptionalUserAndState.isPresent()) {
			order.setIdUser(addCartRequest.getIdUser());
			order.setState(OrderState.PENDING);
			order.setTotal(0);
			// creamos la orden
			order = orderService.createOrder(order);
			return savedDetail(addCartRequest, order);

		} else {
			// solo creamos el detail
			order = orderOptionalUserAndState.get();
			return savedDetail(addCartRequest, order);
		}

	}

	// mostrar los productos del carrito
	public List<ProductCartResponse> findCartByIdUser(Long idUser) {
		Optional<Order> orderOptional = orderService.findByIdUserAndByState(idUser, OrderState.PENDING);
		if (orderOptional.isPresent()) {
			return orderDetailService.findAllByOrder(orderOptional.get()).stream()
					.map(detailMapper::orderDetailToProductCartResponse).collect(toList());
		}
		return null;
	}

	// elimina un producto del carrito por el id order y el id product
	@Transactional
	public void deleteProductCart(Long idUser, Long idProducto) {
		Optional<Order> orderOptional = orderService.findByIdUserAndByState(idUser, OrderState.PENDING);
		if (orderOptional.isPresent()) {
			log.info("Order existe");
			log.info("Id {}", idProducto);
			orderDetailService.deleteByCartAndProduct(orderOptional.get().getId(), idProducto);
		}
	}

	// ckeckout
	@Transactional
	public CartResponse checkout(Long idUser) {
		Optional<Order> orderOptional = orderService.findByIdUserAndByState(idUser, OrderState.PENDING);

		// buscar los detail de esa order
		List<OrderDetail> details = orderDetailService.findAllByOrder(orderOptional.get());
		double total = details.stream().mapToDouble(o -> o.getTotal()).sum();
		orderOptional.get().setTotal(total);
		orderOptional.get().setState(OrderState.COMPLETE);

		// actualizamos la order a completada
		Order o = orderService.createOrder(orderOptional.get());
		return new CartResponse(total, o.getState().toString());
	}
	
	public ProductCartResponse updateProductCart(AddProductCartRequest addProductCartRequest) {
		Optional<Order> orderOptionalUserAndState = orderService.findByIdUserAndByState(addProductCartRequest.getIdUser(),
				OrderState.PENDING);

		if (orderOptionalUserAndState.isPresent()) {
			return savedDetail(addProductCartRequest, orderOptionalUserAndState.get());
		}
		return new ProductCartResponse();
	}

	// guarda el detail
	public ProductCartResponse savedDetail(AddProductCartRequest addCartRequest, Order order) {
		Product product = new Product();
		OrderDetail detail = new OrderDetail();

		product = productService.findProductByIdForDetail(addCartRequest.getIdProduct());

		// creamos el detail
		if(addCartRequest.getId()!=null) {// si es actualización del producto en el carrito
			detail.setId(addCartRequest.getId());
		}
		detail.setOrder(order);
		detail.setDescription(product.getDescription());
		detail.setProduct(product);
		detail.setUnits(addCartRequest.getUnits());
		detail.setTotal(detail.getUnits() * product.getPrice());

		// guardamos el detail
		return  detailMapper.orderDetailToProductCartResponse( orderDetailService.createOrderDetail(detail));

	}

}
