package com.onlineshop.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;





@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
 
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService () {
		return new CustomerDetailsService();
	}
	

	public DaoAuthenticationProvider authenticationProvider () {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		 
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().authorizeRequests()
		.antMatchers("/cart/").authenticated()
		.and()
		.formLogin()
		.successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
		    @Override
		    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		            Authentication authentication) throws IOException, ServletException {
		    	 HttpSession session = request.getSession();
		    	
		    	 if (session != null) {
		             String redirectUrl = (String) session.getAttribute("url_prior_login");
		             
		             if (redirectUrl != null) {
		                 
		                 session.removeAttribute("url_prior_login");
		                 
		                 getRedirectStrategy().sendRedirect(request, response, redirectUrl);
		             } else {
		                 super.onAuthenticationSuccess(request, response, authentication);
		             }
		         } else {
		         
		        super.onAuthenticationSuccess(request, response, authentication);
		         }
		    }                      
		})
		.loginPage("/login")
		.usernameParameter("email")
		.permitAll()
		.and()
		.rememberMe()
		.key("12121")
		.tokenValiditySeconds(7*3600);
		
		
	}
	
	public void configure (WebSecurity web) {
		web.ignoring().antMatchers("/shop/","/images/**","/js/**","/webjars/**");
		
	}
	
	
	
}
