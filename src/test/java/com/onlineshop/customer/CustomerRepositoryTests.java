package com.onlineshop.customer;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTests {

	
	@Autowired CustomerRepository custRepo;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Test
	
	public void testEncodePassword () {
		Date date ;
		Customer customer= new Customer("safta.leonard@yahoo.com", "12345678", "Safta", "Leonard", "076544343","Bucuresti, M6",
				new Date(),true);
		
		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		
		customer.setPassword(encodedPassword);
		
		custRepo.save(customer);
		
		System.out.println("---------------------------: " + customer.toString());
		
	}
}
