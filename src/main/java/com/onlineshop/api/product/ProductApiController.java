package com.onlineshop.api.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.product.Product;
import com.onlineshop.product.ProductRepository;

@RestController
public class ProductApiController {
     
	@Autowired private ProductRepository prodRepo;
	
	@PostMapping("/api/add_product")
	public Product addProduct (@RequestBody Product product) {
	
		return prodRepo.save(product);
	}
	
	@GetMapping("/api/list_products")
	public List<Product> listAllProducts ( HttpServletRequest request){
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		System.out.println("----------------------------" + token.getToken().toString());
		return (List<Product>) prodRepo.findAll();
	}
	
}
