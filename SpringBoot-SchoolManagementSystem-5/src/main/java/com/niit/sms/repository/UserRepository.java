package com.niit.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.sms.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	public User findByEmail(String email);
	public boolean existsByEmail(String email);
	public User findByUserId(Long theId);
	public void deleteByUserId(Long theId);
	public void deleteByEmail(String email);
}
