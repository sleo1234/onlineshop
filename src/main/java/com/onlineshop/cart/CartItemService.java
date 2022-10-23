package com.onlineshop.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.customer.Customer;
import com.onlineshop.customer.CustomerRepository;
import com.onlineshop.exception.ShoppingCartException;
import com.onlineshop.product.Product;
import com.onlineshop.product.ProductRepository;

@Service
public class CartItemService {

	@Autowired
	private CartItemRepository cartRepo;

	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private CustomerRepository custRepo;

	public Integer addProduct(Integer productId, Integer quantity, Customer customer) throws ShoppingCartException {
		Integer updatedQuantity = quantity;
		Product product = prodRepo.findById(productId).get();
		
		CartItem cartItem = cartRepo.findByCustomerAndProduct(customer, product);
		if (cartItem != null) {
			updatedQuantity = cartItem.getQuantity()+quantity;
			if (updatedQuantity > 5) {
				throw new ShoppingCartException("Could not add more " + quantity + " item(s) "
						+ " because there's already "+ cartItem.getQuantity()+ " item(s) in you shopping cart. Maximum is 5 items");
			}
			//cartItem.setQuantity(quantity);
		} else {
			cartItem = new CartItem();
			cartItem.setCustomer(customer);
			cartItem.setProduct(product);
			
		}
		
		cartItem.setQuantity(updatedQuantity);
		cartRepo.save(cartItem);
		
		return updatedQuantity;
	}

}
