package com.onlineshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onlineshop.customer.Customer;
import com.onlineshop.customer.CustomerService;

@Controller
public class CustomerController {

	
	@Autowired private CustomerService custService;
	
	@GetMapping("/register")
	public String viewForm (Model  model, Customer customer) {
		
		model.addAttribute("customer", new Customer());
		
		
		return "customer/register_form";
	}
	
	@PostMapping("/create_customer")
		
		public String registerCustomer (Model model, Customer customer, RedirectAttributes ra) {
		
			custService.saveCustomer(customer);
			
			ra.addFlashAttribute("message", "You have been susccesfully regstered to the site");
			return "customer/message";
		}
}
