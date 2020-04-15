package com.niit.sms.model;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "employee")
public class Employee {

	
	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name = "id")
		private Long id;
	   @Column(name = "first_name")
		private String firstName;
	   @Column(name = "last_Name")
		private String lastName;
	   @Column(name = "date_of_birth")
	   private Date dateOfBirth;
	   @Column(name = "date_of_joining")
	   private Date dateofJoining;
	   @Column(name = "email_Id")
	   private String email;
	   @Column(name = "phone_number")
	   private long phoneNumber;
	   @Column(name = "password")
	   private String password;
	   
	  @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
private EmployeeAddress address;
	public Employee() {
		// TODO Auto-generated constructor stub
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
		Base64.Encoder encoder = Base64.getEncoder();
		String normalString = password;
		String encodedString = encoder.encodeToString(
	    normalString.getBytes(StandardCharsets.UTF_8) );
		this.password = encodedString;
	}
	public EmployeeAddress getAddress() {
		return address;
	}
	public void setAddress(EmployeeAddress address) {
		this.address = address;
	}

}
