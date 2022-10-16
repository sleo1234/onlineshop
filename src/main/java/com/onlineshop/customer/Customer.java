package com.onlineshop.customer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


	@Entity
	@Table
	public class Customer {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
	    
		@Column(length=45, unique = true, nullable = false)
		private String email;
		
		@Column(length=64, unique = true, nullable = false)
		private String password;
		
		@Column(name = "first_name", length = 45, nullable = false)
		private String firstName;
		
		@Column(name = "last_name", length = 45, nullable = false)
		private String lastName;
		
		@Column(name = "phone_number", length = 15, nullable = false)
		private String phoneNumber;
		
		@Column(name = "address_line", length = 64, nullable = false)
		private String addressLine;
		

		@Column(name = "created_time", nullable = true)
		private Date createdTime;
		
		@Column(name = "enabled")
		private boolean enabled;
		
		@Column(name = "verification_Code", length = 64)
		private String verificationCode;
        
		@Column(name = "reset_password_token", length=30)
	    private String resetPasswordToken;

		
		public Customer () {
			
		}
		public Customer(String email, String password, String firstName, String lastName, boolean enabled) {
			this.email = email;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.enabled = enabled;
		}

	
		public Customer(String email, String password, String firstName, String lastName, String phoneNumber,
				String addressLine, Date createdTime, boolean enabled) {
			super();
			this.email = email;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNumber = phoneNumber;
			this.addressLine = addressLine;
			this.createdTime = createdTime;
			this.enabled = enabled;
		}
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getAddressLine() {
			return addressLine;
		}

		public void setAddressLine(String addressLine) {
			this.addressLine = addressLine;
		}

		public Date getCreatedTime() {
			return createdTime;
		}

		public void setCreatedTime(Date createdTime) {
			this.createdTime = createdTime;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public String getVerificationCode() {
			return verificationCode;
		}

		public void setVerificationCode(String verificationCode) {
			this.verificationCode = verificationCode;
		}

		public String getResetPasswordToken() {
			return resetPasswordToken;
		}

		public void setResetPasswordToken(String resetPasswordToken) {
			this.resetPasswordToken = resetPasswordToken;
		} 
		
		@Transient
		public String getFullName() {
			// TODO Auto-generated method stub
			return firstName + " " + lastName;
		}


		@Override
		public String toString() {
			return "Customer [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName="
					+ lastName + ", phoneNumber=" + phoneNumber + ", addressLine=" + addressLine + ", createdTime="
					+ createdTime + ", enabled=" + enabled + ", verificationCode=" + verificationCode
					+ ", resetPasswordToken=" + resetPasswordToken + "]";
		}
		
		
		
		
}
