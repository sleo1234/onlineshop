package com.onlineshop.api.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.exception.ProductNotFoundException;
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
	
	@DeleteMapping("/api/delete/{id}")
	public void deleteProduct (@PathVariable("id") Integer id) throws ProductNotFoundException {
		
		prodRepo.deleteById(id);
	}
	
	@PatchMapping("/api/update/{id}")
	public Product updateProductById (@PathVariable("id") Integer id, @RequestBody Product product) throws ProductNotFoundException {
		
		Product productToBeUpdated = prodRepo.findById(id).get();
		productToBeUpdated.setName(product.getName());
		productToBeUpdated.setDiscountPercent(product.getDiscountPercent());
		
		return prodRepo.save(productToBeUpdated);		
	}
	
	@PatchMapping("/api/update_by_name/{name}")
	public Product updateProductByName (@PathVariable("name") String name, @RequestBody Product product) throws ProductNotFoundException {
		
		Product productToBeUpdated = prodRepo.findProductByName(name);
		productToBeUpdated.setName(product.getName());
		productToBeUpdated.setDiscountPercent(product.getDiscountPercent());
		
		return prodRepo.save(productToBeUpdated);		
	}
	
}
