package com.onlineshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlineshop.customer.Customer;
import com.onlineshop.customer.CustomerRepository;


@Service
public class CustomerDetailsService implements UserDetailsService{

	@Autowired
	private CustomerRepository custRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
			Customer customer = custRepo.findByEmail(email);
			if (customer != null) {
				
				return new CustomerDetails(customer);
			}
		throw new UsernameNotFoundException ("Could not find customer with email: " + email);
	}

	
}

