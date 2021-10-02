package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.model.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>{
	
	Promotion findByPromoCode(String promoCode);

}
