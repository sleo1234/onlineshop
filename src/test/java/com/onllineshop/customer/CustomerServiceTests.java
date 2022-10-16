package com.onllineshop.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.onlineshop.customer.Customer;
import com.onlineshop.customer.CustomerRepository;
import com.onlineshop.customer.CustomerService;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {
	
	@InjectMocks
	private CustomerService custService;
	
	//@MockBean
	//private CustomerRepository custRepo;
	
	@Test
	public void testEncodePassword () {
		Customer customer= new Customer("safta.leonard@yahoo.com", "12345678", "Safta", "Leonard", true);
		custService.saveCustomer(customer);
		
		System.out.println("---------------------------: " + customer.toString());
		
	}

}
