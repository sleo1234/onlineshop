package com.onlineshop.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductTests {
	
	@Autowired private ProductRepository prodRepo;
	
	@Test
	
	public void createProducts() {
		Date date = new Date();
		
		//Product product1 = new Product("Telephone","Very Good phone",date,true,true,200.43f);
		//Product product2 = new Product("Telephone2","Very Good phone",date,true,true,300.43f);
		//Product product3 = new Product("Telephon3","Very Good phone",date,true,true,200.43f);
		//Product product4 = new Product("Telephone4","Very Good phone",date,true,true,300.43f);
		Product product5 = new Product("Smart TV LG","Very Good TV",date,true,true,187.43f);
		Product product6 = new Product("Remote control - universal","For all TV's",date,true,true,25.98f);
		
		Product product7 = new Product("Electric scooter","Very good scooter",date,true,true,435.99f);
		Product product8 = new Product("Potato Slicer","From plastic",date,true,true,98.0f);
		List<Product> products = new ArrayList();
		
			products.add(product5);
			products.add(product6);
			products.add(product7);
			products.add(product8);
			
		prodRepo.saveAll(products);
		
	}
	
	@Test
	public void testListProducts() {
		Sort sort = Sort.by("name");
		String sortDir = "asc";
		
		Pageable pageable = PageRequest.of(1, 2, sort);
		Page<Product> prods = prodRepo.findAll(pageable);
		Page<Product> products = prodRepo.findAll("T", pageable);
		products.forEach(Product :: toString);
		System.out.println("============" +products.getContent().toString());
	}

	
	
	@Test
	public void testGetMaximumValueFromFloatList() {
		
		List<Product> products = (List<Product>) prodRepo.findAll();
		
		Float maxPrice = products.stream().max(Comparator.comparing(Product :: getPrice)).get().getPrice();
		
		System.out.println("-----------------" + maxPrice);
	}
}
