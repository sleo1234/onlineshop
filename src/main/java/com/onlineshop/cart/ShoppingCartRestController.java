package com.onlineshop.cart;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.Utility;
import com.onlineshop.customer.Customer;
import com.onlineshop.customer.CustomerService;
import com.onlineshop.exception.CustomerNotFoundException;
import com.onlineshop.exception.ShoppingCartException;

@RestController
public class ShoppingCartRestController {
	
	@Autowired CartItemService cartService;
	@Autowired CustomerService custService;
	
	@PostMapping("/cart/add/{productId}/{quantity}")
	
	public String addProductToCart (@PathVariable("productId") Integer productId, @PathVariable("quantity") Integer quantity, 
			HttpServletRequest request)  {
		
		try {
		Customer customer = getAuthenticatedCustomer(request);
	
		Integer updatedQuantity = cartService.addProduct(productId, quantity, customer);
		return updatedQuantity + " item(s) were added in the cart";
		}
		catch(CustomerNotFoundException e) {
			return "You must login to add a product to the cart";
		}
		
		catch (ShoppingCartException e) {
			return e.getMessage();
		}
	}

	
  
	public Customer getAuthenticatedCustomer (HttpServletRequest request) throws CustomerNotFoundException{
		
		String email = Utility.getEmailOfAtuthenticatedCustomer(request);
		
		if (email == null) {
			throw new CustomerNotFoundException("No authenticated user.");
		}
		
		return custService.getByEmail(email);
	}
}
