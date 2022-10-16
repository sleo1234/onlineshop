package com.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.customer.CustomerService;

@RestController
public class CustomerRestController {

	@Autowired CustomerService custService;
	
	@PostMapping("/check_unique")
	
	public String checkEmailUnique(@Param("email") String email) {
		
		return custService.checkUniqueEmail(email) ? "Ok" : "Duplicated";
		
	}
}
