package com.niit.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.sms.model.EmployeeAddress;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long> {

	public void deleteByEmployeeId(Long id);
}
