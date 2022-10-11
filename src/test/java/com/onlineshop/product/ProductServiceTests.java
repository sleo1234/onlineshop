package com.onlineshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
	
	@InjectMocks
    private ProductService prodService;
	
	@Mock
	private ProductRepository prodRepo;
	@Test
	
	public void testPagination() {
		
		Page<Product> products =  prodService.listByPage(1, "name", "asc", null);
		//products.getContent();
		System.out.println(products);
	}

}
