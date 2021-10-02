package com.bookstore.resource;


import com.bookstore.model.Order;
import com.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * REST controller for managing Order.
 */
@RestController
public class OrderResource {

    private final OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }
    
   /**
     * GET /orders : get all the orders.
     *
     * @return the ResponseEntity with status 200 (OK) and the list ob orders in body,
     * or with status 204 (NO CONTENT) if there no orders in repository
     */
    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * GET /orders/:id : get the "id" order.
     *
     * @param id the id of the order to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the order,
     * or with status 404 (NOT FOUND)
     */
    @RequestMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Optional<Order> order = orderService.findOrderById(id);
        if (order.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    /**
     * DELETE /orders/:id : delete the "id" order.
     *
     * @param id the id of the order to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        Optional<Order> order = orderService.findOrderById(id);
        if (order.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        orderService.deleteOrder(order.get());
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    /**
     * PATCH /orders : Updates an existing order by applying promoCode.
     *
     * @param order the order to update
     * @return the ResponseEntity with state 200 (OK) and with body the updated order,
     * or with status 400 (Bad Request) if the order is not valid
     */
    @PatchMapping("/order/applyPromoCode{orderId}/{promoCode}")
    public ResponseEntity<Order> applyPromoCode(@PathVariable Long orderId,@PathVariable String promoCode) {
    	if (orderId != null && promoCode != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
                
        Order resultOrder = orderService.applyPromoCode(orderId, promoCode);
        return new ResponseEntity<>(resultOrder, HttpStatus.OK);
    }
    
    /**
     * PUT /orders : Updates an existing order.
     *
     * @param order the order to update
     * @return the ResponseEntity with state 200 (OK) and with body the updated order,
     * or with status 400 (Bad Request) if the order is not valid
     */
    @PutMapping("/order/checkOut/{id}")
    public ResponseEntity<Order> checkOut(@PathVariable Long orderId) {
    	if (orderId != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
                
        Order resultOrder = orderService.checkOut(orderId);
        return new ResponseEntity<>(resultOrder, HttpStatus.OK);
    }
  

}