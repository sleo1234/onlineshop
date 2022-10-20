package com.onlineshop.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.onlineshop.customer.Customer;
import com.onlineshop.product.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	public List<CartItem> findByCustomer(Customer customer); 
	
	public CartItem findByCustomerAndProduct(Customer customer, Product product);
	
	@Modifying
	@Query("UPDATE CartItem c SET c.quantity= ?1 WHERE c.customer.id =?2 AND c.product.id = ?3")
	public void updateQuantity(Integer quantity, Integer customerId, Integer productId);
}
