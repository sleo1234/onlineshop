package com.onlineshop.security;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.onlineshop.customer.Customer;

@Transactional
public class CustomerDetails implements UserDetails{ 

	private Customer customer;
	private final Set<GrantedAuthority> authorities = new HashSet<>();

	public CustomerDetails (Customer customer) {
		this.customer = customer;
	}
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
	authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return customer.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return customer.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getFullName () {
		
		return customer.getFirstName() +" " + customer.getLastName();
	}

	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return this.customer;
	}
	
	
}
