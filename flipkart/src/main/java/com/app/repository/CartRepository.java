package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	public List<Cart> findCartByCustomerId(Integer customerId);
	public Cart findCartByCustomerIdAndProductId(Integer customerId, Integer productId);
	
}
