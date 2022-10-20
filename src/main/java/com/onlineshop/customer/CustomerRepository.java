package com.onlineshop.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByEmail(String email);

	public Customer findByResetPasswordToken(String token);
	
}
