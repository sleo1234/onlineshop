package com.onlineshop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineshop.exception.ProductNotFoundException;
import com.onlineshop.product.Product;
import com.onlineshop.product.ProductRepository;
import com.onlineshop.product.ProductService;

@Controller

public class MainController {
	


   @GetMapping("/shop")
   
   public String showFirstPage(Model model) throws ProductNotFoundException {
	   System.out.println("==========");
	   return "index";
   }
	
   @GetMapping("/login")
   
   public String showLoginpage(HttpServletRequest request, Model model) {
	   
	  
	   return "/login";
   }
   


	
}
