package com.onlineshop.cart;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	@Autowired CartItemRepository cartRepo;
	
	@PostMapping("/cart/add/{productId}/{quantity}")
	
	public String addProductToCart (@PathVariable("productId") Integer productId, @PathVariable("quantity") Integer quantity, 
			HttpServletRequest request)  {
		
		try {
		Customer customer = getAuthenticatedCustomer(request);
	
		Integer updatedQuantity = cartService.addProduct(productId, quantity, customer);
		return updatedQuantity + " item(s) were added in the cart";
		}
		catch(CustomerNotFoundException e) {
			return "You must login to add a product to the cart. Log in?";
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
	
	@DeleteMapping("/cart/delete/{productId}")
 	
 	public String deleteProduct(@PathVariable("productId") Integer productId, HttpServletRequest request) {
 		
 		try {
 			Customer authCustomer = getAuthenticatedCustomer(request);
 	   cartService.deleteProductFromCart(productId, authCustomer);
 		return "deleted";
 		
 		} catch (CustomerNotFoundException e) {
 			// TODO Auto-generated catch block
 			return "product not found";
 		
 	}
 	
 	}

		@PostMapping("/cart/update/{productId}/{quantity}")
		public String updateQuantity(@PathVariable("productId") Integer productId, @PathVariable("quantity") Integer quantity,
				HttpServletRequest request)  {
			try {
				Customer authCustomer = getAuthenticatedCustomer(request);
			float subtotal = cartService.updateQuantity(productId, quantity, authCustomer);
			return String.valueOf(subtotal);
			
			} catch (CustomerNotFoundException e) {
				// TODO Auto-generated catch block
				return "You must login to change quantity of product. Log in?";
			
		}
		
		}
		
		
		@DeleteMapping("cart/empty_cart")
		
		public String emptyCart(HttpServletRequest request) throws CustomerNotFoundException {
			
			Customer authCustomer = getAuthenticatedCustomer(request);
			cartService.deleteAllFromCart(authCustomer.getId());
			return "all_deleted";
		}
		
		
		@GetMapping("cart/items")
		
		public String getItems(HttpServletRequest request) throws CustomerNotFoundException {
			Customer authCustomer = getAuthenticatedCustomer(request);
			
			List<CartItem> items = cartRepo.findByCustomer(authCustomer);
			if (items.size() > 0) {
				return "not_empty";
			}
			return "empty";
		}
		
}
