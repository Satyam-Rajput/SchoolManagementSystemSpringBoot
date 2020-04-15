package com.niit.sms.model;

import java.sql.Date;

import javax.persistence.Column;

public class EmployeeDetails {

	

	private Long id;
 
	private String firstName;
  
	private String lastName;
  
   private Date dateOfBirth;
   private String role;

   public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
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

public Date getDateOfBirth() {
	return dateOfBirth;
}

public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}

public Date getDateofJoining() {
	return dateofJoining;
}

public void setDateofJoining(Date dateofJoining) {
	this.dateofJoining = dateofJoining;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public long getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(long phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Long getAddressId() {
	return addressId;
}

public void setAddressId(Long addressId) {
	this.addressId = addressId;
}

public int getHouseNumber() {
	return houseNumber;
}

public void setHouseNumber(int houseNumber) {
	this.houseNumber = houseNumber;
}

public String getStreetName() {
	return streetName;
}

public void setStreetName(String streetName) {
	this.streetName = streetName;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public int getPincode() {
	return pincode;
}

public void setPincode(int pincode) {
	this.pincode = pincode;
}

private Date dateofJoining;
  
   private String email;
  
   private long phoneNumber;

   private String password;
   
	private Long addressId;
		   private int houseNumber;
	
	   private String streetName;
	   
	   private String city;
	 
	   private String state;
	  
	   private String country;
	   
	   private int pincode;
	
}
