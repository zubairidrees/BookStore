package com.bookstore.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_book")
public class OrderBook implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7857764516775726103L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_book_id")
	private Long orderBookId;
	
	@ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    
    @Column(name = "orignal_price")
    private Double orignalPrice;
    
    @Column(name = "discounted_price")
    private Double discountedPrice;
    
           
    public OrderBook(Long orderBookId, Order order, Book book) {
		this.orderBookId = orderBookId;
		this.order = order;
		this.book = book;
	}

	public OrderBook(Order order, Book book) {
		this.order = order;
		this.book = book;
	}

	public OrderBook() {
    }
	
	
    public Long getOrderBookId() {
		return orderBookId;
	}

	public void setOrderBookId(Long orderBookId) {
		this.orderBookId = orderBookId;
	}

	public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
	public Double getOrignalPrice() {
		return orignalPrice;
	}

	public void setOrignalPrice(Double orignalPrice) {
		this.orignalPrice = orignalPrice;
	}

	public Double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderBook other = (OrderBook) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderBook [orderBookId=" + orderBookId + ", order=" + order + ", book=" + book + "]";
	}

   
}
