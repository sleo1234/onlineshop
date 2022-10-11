package com.onlineshop.product;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service

public class ProductService {
    @Autowired ProductRepository prodRepo;
    
    public static final int PRODUCTS_PER_PAGE = 2;
    
    
	public List<Product> listAllProducts(){
		
		return (List<Product>)prodRepo.findAll();
	}
	
	
	
	public Page<Product> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE, sort);
        System.out.println("-----------------------" + pageable);
		if (keyword != null) {
			System.out.println("**********" + pageable);
			return prodRepo.findAll(keyword, pageable);
		}
		return prodRepo.findAll(pageable);
		
	}



	public List<Product> listByKeyword(String keyword) {
		if (keyword == null) {
			return (List<Product>) prodRepo.findAll();
		}
		return prodRepo.findByKeyword(keyword);
	}
	
	
}
