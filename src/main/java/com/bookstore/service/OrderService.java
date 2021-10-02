package com.bookstore.service;

import com.bookstore.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAllOrders();
    Optional<Order> findOrderById(Long orderId);
    void deleteOrder(Order order);
	Order checkOut(Long orderId);
	Order applyPromoCode(Long orderId, String promoCode);

}
