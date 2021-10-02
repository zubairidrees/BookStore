package com.bookstore.service;

import com.bookstore.dto.OrderBookDTO;
import com.bookstore.model.Order;

public interface OrderBookService {

	Order addBook(OrderBookDTO bookDTO);
	Order removeBook(OrderBookDTO bookDTO);
}
