package com.onlineshop.cartitem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;
import com.onlineshop.cart.CartItem;
import com.onlineshop.cart.CartItemRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CartItemTests {
	
	@Autowired private CartItemRepository cartRepo;

	@Test
	public void testUpdateQuantity () {
		CartItem cartItem = cartRepo.findById(1).get();
		
		cartRepo.updateQuantity(3, 1, 1);
		
		assertThat(cartItem.getQuantity()).isEqualTo(3);
	}
	
	@Test 
	
	public void testDeleteAllFromCart() {
		cartRepo.deleteByCustomer(1);
		
		
	}
}
