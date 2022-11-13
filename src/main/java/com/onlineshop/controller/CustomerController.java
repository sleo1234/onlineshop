package com.onlineshop.controller;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onlineshop.customer.Customer;
import com.onlineshop.customer.CustomerService;
import com.onlineshop.Utility;
import com.onlineshop.setting.EmailSettingBag;

import com.onlineshop.setting.SettingService;
@Controller
public class CustomerController {

	
	@Autowired private CustomerService custService;
	@Autowired SettingService settingService;
	
	
	@GetMapping("/register")
	public String viewForm (Model  model, Customer customer) {
		
		model.addAttribute("customer", new Customer());
		
		
		return "customer/register_form";
	}
	
	@PostMapping("/create_customer")
		
		public String registerCustomer (Model model,HttpServletRequest request, Customer customer, RedirectAttributes ra) throws UnsupportedEncodingException, MessagingException {
		
		
			sendVerificationEmail(request, customer);
			
			custService.saveCustomer(customer);
			
			ra.addFlashAttribute("message", "You have been susccesfully regstered to the site");
			return "customer/message";
		}
	
	
	  @GetMapping("/update_account")
	  
	  public String updateAccountDetails (Model model, HttpServletRequest request) {
		  
		  String email = Utility.getEmailOfAtuthenticatedCustomer(request);
		  Customer authenticatedCustomer = custService.getByEmail(email);
		  model.addAttribute("customer", authenticatedCustomer);
		  return "/customer/edit_customer";
		  
	  }
	
	private void sendVerificationEmail(HttpServletRequest request, Customer customer) throws UnsupportedEncodingException, MessagingException {
		
		
		EmailSettingBag emailSettings = settingService.getEmailSettings();
		JavaMailSenderImpl mailSender = Utility.prepareMailSender(emailSettings);
		String toAdress = customer.getEmail();
		String subject = emailSettings.getCustomerVerifySubject();
		String content = emailSettings.getCustomerVerifyContent();
		Properties props = mailSender.getJavaMailProperties();
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(emailSettings.getFromAddress(), emailSettings.getSenderName());
	    helper.setTo(toAdress);
	    helper.setSubject(subject);
	    
	    content = content.replace("[[name]]", customer.getFullName());
	    String verifyURL = Utility.getSiteURL(request) + "/verify?code="+customer.getVerificationCode();
	    content = content.replace("[[URL]]", verifyURL);
	    helper.setText(content, true);
	    mailSender.send(message);
		
	   
	}
	
	
	
	
}
