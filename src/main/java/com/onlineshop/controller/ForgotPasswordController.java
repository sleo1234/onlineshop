package com.onlineshop.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlineshop.Utility;
import com.onlineshop.customer.Customer;
import com.onlineshop.customer.CustomerService;
import com.onlineshop.exception.CustomerNotFoundException;
import com.onlineshop.setting.EmailSettingBag;
import com.onlineshop.setting.SettingService;



@Controller
public class ForgotPasswordController {
	
	@Autowired private CustomerService custService;
	@Autowired private SettingService settingService;
	
	@GetMapping("/forgot_password")
	public String showRequestForm() {

		
		return "customer/forgot_password_form";
	}
	
	@PostMapping("/forgot_password")
	public String processRequestForm(HttpServletRequest request, Model model) {
		
		String email = request.getParameter("email");
		try {
			String token = custService.updateResetPasswordToken(email);
			String link = Utility.getSiteURL(request) +"/reset_password?token=" + token;
			sendEmail(link, email);
			
			
			model.addAttribute("message", "We have sent a reset password link to your email. Please check your email ");
			
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			model.addAttribute("error", e.getMessage());
		}
		catch(UnsupportedEncodingException  | MessagingException e) {
			model.addAttribute("error", "Could not send email");
		}
		
		return "customer/forgot_password_form";
	}
	
	private void sendEmail(String link, String email) throws UnsupportedEncodingException, MessagingException{
		
		EmailSettingBag emailSettings = settingService.getEmailSettings();
		JavaMailSenderImpl mailSender = Utility.prepareMailSender(emailSettings);
		
		String toAdress = email;
		String subject = "Here's your link to reset your password";
		
		String content = "<p>Hello</p>"
				+ "<p>You have requested to reset your password</p>"
				+ "<p>Clike the linke bellow to change your password</p>"
				+ "<p> <a href=\"" + link +"\"> Change my password</p>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(emailSettings.getFromAddress(), emailSettings.getSenderName());
	    helper.setTo(toAdress);
	    helper.setSubject(subject);
	    helper.setText(content, true);
	    mailSender.send(message);
	    
	}
	
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param("token") String token, Model model, HttpServletRequest request) {
		
		Customer customer = custService.getByResetPasswordToken(token);
	
		String password = request.getParameter("password");
	
		if (customer != null) {
			
			model.addAttribute("token", token);
			
		}else {
			model.addAttribute("pageTitle", "Invalid token");
			model.addAttribute("message", "Invalid token");
			return "message";
		}
		return "customer/reset_password_form";
	}
	
	@PostMapping("/reset_password")
	public String processResetForm( HttpServletRequest request, Model model) throws CustomerNotFoundException {
		String password = request.getParameter("password");
		
		String token = request.getParameter("token");
		
		custService.updatePassword(token, password);
		model.addAttribute("message", "Reset you password");
		model.addAttribute("message","You have succesfully changed the password");
		return "message";
		
	}
	

}
