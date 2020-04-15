package com.niit.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.sms.model.Marks;

public interface MarksRepository extends JpaRepository<Marks,Integer> {

	public Marks findByStudentId(int studentId);
	public void deleteByStudentId(int studentId);
	
}
