package com.onlineshop.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.onlineshop.exception.ProductNotFoundException;
import com.onlineshop.product.Product;
import com.onlineshop.product.ProductRepository;
import com.onlineshop.product.ProductService;

@Controller

public class ProductController {
	
	
   @Autowired private ProductRepository prodRepo;
   
   @Autowired private ProductService prodService;
   

   @GetMapping("/")
   
   public String showFirstPage(Model model) throws ProductNotFoundException {
	   System.out.println("==========");
		
	   return listByPage(1, model, "name", "asc", null,0.0f,getMaxPrice(model)); 
   }
	
	@GetMapping("/products/page/{pageNum}")
	public String listByPage(@PathVariable("pageNum") int pageNum, Model model, @Param("sortField") String sortField,
			@Param("sortDir") String sortDir, @Param("keyword") String keyword,
			@Param("minPrice")  Float minPrice, @Param("maxPrice") Float maxPrice) throws ProductNotFoundException {
  
		Page<Product> page = prodService.listByPage(pageNum, sortField, sortDir, keyword);
		
	
		
		List<Product> listProducts = page.getContent();
        List<Product> allProducts = (List<Product>) prodRepo.findAll();
		System.out.println("products:+++++++++++++" + listProducts);
		Float maxValue = allProducts.stream()
			       .max(Comparator.comparing(Product :: getPrice)).get().getPrice();
	  model.addAttribute("maximumPrice",maxValue);

	  List<Product> products = listProducts.stream()
	                         .filter(c -> c.getPrice() >= minPrice && c.getPrice() <= maxPrice)
					         .collect(Collectors.toList());
			
		
		System.out.println("products: -------------" + products);
		long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
		long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		int totalPages = page.getTotalPages();
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("currentPage", pageNum);
	
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", getTotalOfProducts(keyword, minPrice, maxPrice));
		model.addAttribute("products", products);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("moduleURL", "/products");
		model.addAttribute("keyword", keyword);
        System.out.println("kewyord: " + keyword);
		return "product/products";
		
	}
	
	public Float getMaxPrice (Model model) {
		List<Product> products = (List<Product>)prodRepo.findAll();
		
		Float maxValue = products.stream()
			       .max(Comparator.comparing(Product :: getPrice))
			       .get()
			       .getPrice();
	    model.addAttribute("maximumPrice",maxValue);
		return maxValue;
	}
	
	@GetMapping("/product_detail/{productId}")
	public String productDetail (@PathVariable ("productId") Integer productId,Model model, HttpServletRequest request) {
		Product product = prodRepo.findById(productId).get();
		
		
		model.addAttribute("product",product);
	    String url = request.getRequestURL().toString();
	    request.getSession().setAttribute("url_prior_login", url);
		System.out.println("Session-------------- " + request.getSession().getId());
		return "product/product_detail";
	}
	
	
	
	
	public Integer getTotalOfProducts (@Param("keyword") String keyword,
			@Param("minPrice")  Float minPrice, @Param("maxPrice") Float maxPrice) {
		List<Product> products = prodService.listByKeyword(keyword).stream()
                .filter(c -> c.getPrice() >= minPrice && c.getPrice() <= maxPrice)
		         .collect(Collectors.toList());
		Integer numberOfProduct = Integer.valueOf(products.size());
		return numberOfProduct;
	}
	
	
	
}
