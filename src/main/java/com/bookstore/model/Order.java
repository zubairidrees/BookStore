package com.bookstore.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order")
public class Order implements Serializable {

	public enum Status{
		DRAFT,SUBMITTED,PAYMENT_PENDING,PAYMENT_COMPLETED,DELIVERED;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 9047210199965740368L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long orderId;
   
    @Column(name = "date")
    private Date orderDate;

    @Enumerated(EnumType.ORDINAL)
    private Status orderStatus;

    @Column(name = "price", precision=10, scale=2)
    private Double orderPrice;
    
    @Column(name = "discounted_price", precision=10, scale=2)
    private Double orderDiscountedPrice;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderBook> orderBooks = new ArrayList<>();

    @Column
    private Long user;

    public Order(Long orderId, Date orderDate, Status orderStatus, Double orderPrice, Double orderDiscountedPrice,
			List<OrderBook> orderBooks, Long user) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.orderPrice = orderPrice;
		this.orderDiscountedPrice = orderDiscountedPrice;
		this.orderBooks = orderBooks;
		this.user = user;
	}

	public Order() {}

	public void addBook(Book book) {
        OrderBook orderBook = new OrderBook(this, book);
        orderBook.setOrignalPrice(book.getBookPrice());
        orderBooks.add(orderBook);
        book.getOrders().add(orderBook);
    }

    public void removeBook(Book book) {
        OrderBook orderBook = new OrderBook(this, book);
        book.getOrders().remove(orderBook);
        orderBooks.remove(orderBook);
        orderBook.setOrder(null);
        orderBook.setBook(null);
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }
    
    public Double getOrderDiscountedPrice() {
		return orderDiscountedPrice;
	}

	public void setOrderDiscountedPrice(Double orderDiscountedPrice) {
		this.orderDiscountedPrice = orderDiscountedPrice;
	}

	public List<OrderBook> getOrderBooks() {
        return orderBooks;
    }

    public void setOrderBooks(List<OrderBook> books) {
        this.orderBooks = books;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Order)) return false;
        Order order = (Order) obj;
        return getOrderId() != null && Objects.equals(getOrderId(), order.getOrderId());
    }
}
