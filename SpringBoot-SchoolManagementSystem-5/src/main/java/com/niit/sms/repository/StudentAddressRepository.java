package com.niit.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.sms.model.StudentAddress;

public interface StudentAddressRepository extends JpaRepository<StudentAddress, Long> {

	public void deleteByStudentId(Long id);
}
