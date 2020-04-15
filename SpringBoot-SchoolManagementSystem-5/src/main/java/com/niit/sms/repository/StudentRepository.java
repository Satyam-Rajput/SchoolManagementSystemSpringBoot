package com.niit.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.sms.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	public List findAllByStudentClass(String studentClass);
}
