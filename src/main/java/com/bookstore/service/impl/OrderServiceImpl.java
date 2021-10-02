package com.bookstore.service.impl;

import com.bookstore.model.Book;
import com.bookstore.model.Order;
import com.bookstore.model.OrderBook;
import com.bookstore.model.Promotion;
import com.bookstore.repository.OrderRepository;
import com.bookstore.repository.PromotionRepository;
import com.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
	private PromotionRepository promotionRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,PromotionRepository promotionRepository) {
        this.orderRepository = orderRepository;
		this.promotionRepository = promotionRepository;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }


    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
    
    @Override
    public Order checkOut(Long orderId) {
    	Optional<Order> optionalOrder = orderRepository.findById(orderId);
		Order order = optionalOrder.get();
		if(optionalOrder.isPresent()) {
			List<OrderBook> orderBooks = order.getOrderBooks();
			order.setOrderPrice(sumTotalOriginalAmount(orderBooks));
			order.setOrderStatus(Order.Status.SUBMITTED);
		}
		return order;
    }

	@Override
	public Order applyPromoCode(Long orderId,String promoCode) {
		Promotion promotion = promotionRepository.findByPromoCode(promoCode);
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		Order order = optionalOrder.get();
		Boolean discountApplied = false;
		if(optionalOrder.isPresent()) {
			List<OrderBook> orderBooks = order.getOrderBooks();
			for (OrderBook orderBook : orderBooks) {
				   Book book = 	orderBook.getBook();
			       if(book.getBookType().getPromotions() != null 
			    		   && book.getBookType().getPromotions().contains(promotion)) {
			    	   applyDiscount(book,orderBook,promotion);
			    	   discountApplied = true;
			       }	
			}
			
			if(discountApplied) {
				order.setOrderPrice(sumTotalOriginalAmount(orderBooks));
				order.setOrderDiscountedPrice(sumTotalDiscountedAmount(orderBooks));
			}
			
		}
		orderRepository.save(order);
		return order;
	}
	
	private void applyDiscount(Book book, OrderBook orderBook, Promotion promotion) {
		Double discountedPrice = calculateDiscountedPrice(promotion.getDicountPercentage(),book.getBookPrice());
 	   orderBook.setDiscountedPrice(discountedPrice);
 	   orderBook.setOrignalPrice(book.getBookPrice());
	}
	
	private Double calculateDiscountedPrice(Long dicountPercentage, Double originalPrice) {
		Double discountedPrice = ((originalPrice / 100) * dicountPercentage);
		return originalPrice - discountedPrice;
	}

	private Double sumTotalOriginalAmount(List<OrderBook> orderBooks) {
		Double sum = orderBooks.stream()
				  .map(x -> x.getDiscountedPrice())
				  .reduce(0d, (a, b) -> a + b);
		return sum;
	}
	
	private Double sumTotalDiscountedAmount(List<OrderBook> orderBooks) {
		Double sum = orderBooks.stream()
				  .map(x -> x.getOrignalPrice())
				  .reduce(0d, (a, b) -> a + b);
		return sum;
	}
   
}
