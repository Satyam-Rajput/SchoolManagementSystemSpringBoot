package com.niit.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sms.model.Employee;
import com.niit.sms.model.Student;
import com.niit.sms.model.User;
import com.niit.sms.repository.EmployeeAddressRepository;
import com.niit.sms.repository.EmployeeRepository;
import com.niit.sms.repository.StudentAddressRepository;
import com.niit.sms.repository.StudentRepository;
import com.niit.sms.repository.UserRepository;

@Service
@Transactional
public class AdminService {

	@Autowired
	private EmployeeRepository erepo;
	@Autowired
	private StudentRepository srepo;
	@Autowired
	private UserRepository urepo;
	@Autowired
	private EmployeeAddressRepository eARepo;
	@Autowired
	private StudentAddressRepository sarepo;

	public List<Employee> listAllEmployees() {
		return erepo.findAll();
	}

	public boolean save(Employee employee) {

		try {
			erepo.save(employee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Employee getEmployee(long id) {
		return erepo.findById(id).get();
	}

	public boolean deleteEmployee(long id) {

		try {
			urepo.deleteByEmail((erepo.findById(id).get()).getEmail());
			erepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public long countEmployee() {
		return erepo.count();
	}

	public long countStudent() {
		return srepo.count();
	}

	public List filterStudentByClass(String studentClass) {
		return srepo.findAllByStudentClass(studentClass);

	}

	public boolean update(Employee employee) {
		try {
			eARepo.deleteByEmployeeId(employee.getId());
			erepo.save(employee);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	

	}

	public boolean update(Student student) {
		
		
		try {
			
			sarepo.deleteByStudentId(student.getId());
			srepo.save(student);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	
	}

	public List<Student> listAllStudents() {
		return srepo.findAll();
	}

	public boolean save(Student student) {

		
try {
			
	srepo.save(student);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Student getStudent(long id) {
		return srepo.findById(id).get();
	}

	public boolean deleteStudent(long id) {
try {
	
			
	urepo.deleteByEmail((srepo.findById(id).get()).getEmail());
	srepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	
	}

	public boolean isStudentExists(long id) {
		return srepo.existsById(id);
	}

	public boolean isEmployeeExists(long id) {
		return erepo.existsById(id);
		
	}
	
	
	
	
	
	
	
	
	

}
