package com.niit.sms.model;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	 public User(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "id")
		private long id;
		@Column(name="user_id")
	    private Long userId;

		@Column(name = "email_id")
		private String email;
		@Column(name = "password")
		private String password;
		@Column(name = "role")
	 private String role;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
		
		
		Base64.Encoder encoder = Base64.getEncoder();
		String normalString = password;
		String encodedString = encoder.encodeToString(
	    normalString.getBytes(StandardCharsets.UTF_8) );
		this.password = encodedString;
		}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
