package com.onlineshop;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.onlineshop.setting.EmailSettingBag;




public class Utility {
  
	public static String getSiteURL (HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		
		return siteURL.replace(request.getServletPath(), "");
		
	}
	
	public static JavaMailSenderImpl prepareMailSender (EmailSettingBag settings) {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost(settings.getHost());
		mailSender.setPort(settings.getPortNumber());
		mailSender.setUsername(settings.getUsername());
		mailSender.setPassword(settings.getPassword());
		
		
		Properties mailProperties = new Properties ();
		
		mailProperties.setProperty("mail.smtp.auth", settings.getSmtpAuth());
		mailProperties.setProperty("mail.smtp.starttls.enable", settings.getSmtpSecured());
		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;
		
	}
	public static String getEmailOfAtuthenticatedCustomer(HttpServletRequest request) {
		 Object principal = request.getUserPrincipal();
		 if (principal == null) {
			 return null;
		 }
		 
		 String customerEmail = null;
		 if (principal instanceof UsernamePasswordAuthenticationToken || principal instanceof RememberMeAuthenticationToken) {
			 customerEmail = request.getUserPrincipal().getName();
		 } 
		 return customerEmail;
	 }
}
