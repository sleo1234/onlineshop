package com.onlineshop.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{

	public Product findProductByName(String name);
    
	
	@Query("SELECT p from Product p WHERE p.name LIKE %?1%")
 	public Page<Product> findAll(String keyword, Pageable pageable);

	@Query("SELECT p from Product p WHERE p.name LIKE %?1%")
	public List<Product> findByKeyword(String keyword);
	
}
