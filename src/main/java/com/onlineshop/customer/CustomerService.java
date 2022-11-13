package com.onlineshop.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlineshop.exception.CustomerNotFoundException;


import net.bytebuddy.utility.RandomString;



@Service
public class CustomerService {

	
	@Autowired CustomerRepository custRepo;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public boolean checkUniqueEmail(String email) {
		 
		Customer customer = custRepo.findByEmail(email);
		
		
		if (customer != null && customer.getId() != null) {
			return true;
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


	public String updateResetPasswordToken(String email) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
				Customer customer = custRepo.findByEmail(email);
				if (customer != null) {
					String token = RandomString.make(30);
					customer.setResetPasswordToken(token);
					custRepo.save(customer);
					return token;
				} else { 
					throw new CustomerNotFoundException("Could not find customer with email: " + email); 
					
				}
			}
			
			
			public Customer getByResetPasswordToken(String token) {
				
				
				return custRepo.findByResetPasswordToken(token);
				
	}


			public void updatePassword(String token, String newPassword) throws CustomerNotFoundException {
				Customer customer = custRepo.findByResetPasswordToken(token);
				System.out.println("----------------ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
				if (customer == null) {
					throw new CustomerNotFoundException("No customer found. Invalid token");
				}
				
				customer.setPassword(newPassword);
				
			 encodePassword(customer);
				custRepo.save(customer);
				
			}
}
