package com.niit.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.niit.sms.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public boolean existsByEmail(String email);
	
}
