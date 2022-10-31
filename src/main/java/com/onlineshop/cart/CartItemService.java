package com.onlineshop.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineshop.customer.Customer;
import com.onlineshop.customer.CustomerRepository;
import com.onlineshop.exception.ShoppingCartException;
import com.onlineshop.product.Product;
import com.onlineshop.product.ProductRepository;


@Service
@Transactional
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

	public List<CartItem> getCartItems(Customer customer){
    	return cartRepo.findByCustomer(customer);
    }
	
	public void deleteProductFromCart(Integer productId, Customer customer) {
   	 
   	 cartRepo.deleteByCustomerAndProduct(customer.getId(), productId);
   	 
    }
	
	public float updateQuantity(Integer productId, Integer quantity, Customer customer) {
   	 cartRepo.updateQuantity(quantity, customer.getId(), productId);
   	 Product product = prodRepo.findById(productId).get();
   	 float subtotal = product.getDiscountPrice() * quantity;
   	 return subtotal;
   	 
    }
	
	public void deleteAllFromCart(Integer customerId) {
   	 cartRepo.deleteByCustomer(customerId);
    }
	
	

}
