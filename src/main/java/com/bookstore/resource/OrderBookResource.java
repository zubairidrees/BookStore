package com.bookstore.resource;


import com.bookstore.dto.OrderBookDTO;
import com.bookstore.model.Order;
import com.bookstore.service.OrderBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Order.
 */
@RestController
public class OrderBookResource {

    private final OrderBookService orderBookService;
    @Autowired
    public OrderBookResource(OrderBookService orderBookService) {
        this.orderBookService = orderBookService;
    }

   
    @PostMapping("/orderbook")
    public ResponseEntity<Order> addBook(@RequestBody OrderBookDTO orderBookDTO) {
        if (orderBookDTO.getBookId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
                
        Order resultOrder = orderBookService.addBook(orderBookDTO);
        return new ResponseEntity<>(resultOrder, HttpStatus.CREATED);
    }
    
    /**
     * PUT /orders : Updates an existing order.
     *
     * @param order the order to update
     * @return the ResponseEntity with state 200 (OK) and with body the updated order,
     * or with status 400 (Bad Request) if the order is not valid
     */
    @PutMapping("/orderbook")
    public ResponseEntity<Order> removeBook(@RequestBody OrderBookDTO orderBookDTO) {
    	if (orderBookDTO.getBookId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
                
        Order resultOrder = orderBookService.removeBook(orderBookDTO);
        return new ResponseEntity<>(resultOrder, HttpStatus.OK);
    }

    
}