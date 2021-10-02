package com.bookstore.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.dto.OrderBookDTO;
import com.bookstore.model.Book;
import com.bookstore.model.Order;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.repository.PromotionRepository;
import com.bookstore.service.OrderBookService;

public class OrderBookServiceImpl implements OrderBookService{

	private OrderRepository orderRepository;
	private BookRepository bookRepository;
	
	
	@Autowired
	public OrderBookServiceImpl(OrderRepository orderRepository,BookRepository bookRepository) {
		this.orderRepository = orderRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public Order addBook(OrderBookDTO orderBookDTO) {
		Order order = null;
		Long orderId = orderBookDTO.getOrderId();
		
		if(orderId != null) {
			order = orderRepository.findById(orderId).get();
		}else {
			order = new Order();
			order.setOrderStatus(Order.Status.DRAFT);
		}
			
		Book book = bookRepository.findById(orderBookDTO.getBookId()).get();
		
		order.addBook(book);
		order = orderRepository.save(order);
		return order;
	}

	@Override
	public Order removeBook(OrderBookDTO orderBookDTO) {
		Order order = null;
		Long orderId = orderBookDTO.getOrderId();
		
		if(orderId != null) {
			order = orderRepository.findById(orderId).get();
			Book book = bookRepository.findById(orderBookDTO.getBookId()).get();
			order.removeBook(book);
			order = orderRepository.save(order);
		}
		return order;
	}

}
