package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("from Customer where id=:id")
	public Customer getCustomerById(Integer id);
}
