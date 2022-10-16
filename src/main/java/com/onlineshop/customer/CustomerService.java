package com.onlineshop.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class CustomerService {

	
	@Autowired CustomerRepository custRepo;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public boolean checkUniqueEmail(String email) {
		 
		Customer customer = custRepo.findByEmail(email);
		
		
		if (customer != null && customer.getId() != null) {
			return false;
		}
		return customer == null;
	}
	
	
	public void saveCustomer (Customer customer) {
		
		encodePassword(customer);
		custRepo.save(customer);
	}
	
	
	
	private void encodePassword(Customer customer) {
		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
	}


	public Customer getByEmail(String email) {
		
		return custRepo.findByEmail(email);
		
	}
}
