package com.onlineshop.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.onlineshop.Utility;
import com.onlineshop.cart.CartItem;
import com.onlineshop.cart.CartItemService;
import com.onlineshop.customer.Customer;
import com.onlineshop.customer.CustomerService;

@Controller
public class ShoppingCartController {

	@Autowired CartItemService cartService;
	@Autowired CustomerService custService;
	
	@GetMapping("/cart")
	public String viewCart(Model model, HttpServletRequest request) {
		
		Customer authenticatedCustomer = getAuthenticatedCustomer(request);
		
		List<CartItem> cartItems = cartService.getCartItems(authenticatedCustomer);
		model.addAttribute("cartItems",cartItems);
		
		//total price of the items
		float total = (cartItems
				.stream()
				.map(c ->c.getSubtotal())
				.collect(Collectors.toList()))
				.stream()
				.reduce(0.0f, (c,d)-> c+d );
	
		
		System.out.println("--------------" + total);
		model.addAttribute("total", total);
		
		
	
		return "cart/shopping_cart";
	}

		private Customer getAuthenticatedCustomer (HttpServletRequest request)  {
			String email = Utility.getEmailOfAtuthenticatedCustomer(request);
			
			return custService.getByEmail(email);
		}
}
