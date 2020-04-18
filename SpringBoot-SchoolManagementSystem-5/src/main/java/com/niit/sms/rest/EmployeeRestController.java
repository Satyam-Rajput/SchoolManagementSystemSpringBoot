package com.niit.sms.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.sms.model.Employee;
import com.niit.sms.model.EmployeeAddress;
import com.niit.sms.model.EmployeeDetails;
import com.niit.sms.model.Marks;
import com.niit.sms.model.Student;
import com.niit.sms.model.StudentDetails;
import com.niit.sms.service.AdminService;
import com.niit.sms.service.EmployeeService;
import com.niit.sms.service.LoginService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/employee")
public class EmployeeRestController {
/*
	@Autowired
	private AdminService service;
	@Autowired
	private LoginService loginService;

	@Autowired
	private EmployeeService empService;

	
	
	
	
	

	public List<StudentDetails> getStudentDetailList(List<Student> list) {
		List<StudentDetails> sl = new ArrayList<StudentDetails>();
		for (Student s : list) {
			StudentDetails sd = new StudentDetails();
			sd.setAddressId(s.getAddress().getAddressId());
			sd.setCity(s.getAddress().getCity());
			sd.setCountry(s.getAddress().getCountry());
			sd.setDateOfBirth(s.getDateOfBirth());
			sd.setDateofJoining(s.getDateofJoining());
			sd.setEmail(s.getEmail());
			sd.setFatherName(s.getFatherName());
			sd.setFirstName(s.getFirstName());
			sd.setHouseNumber(s.getAddress().getHouseNumber());
			sd.setId(s.getId());
			sd.setLastName(s.getLastName());
			sd.setPassword(s.getPassword());
			sd.setPhoneNumber(s.getPhoneNumber());
			sd.setPincode(s.getAddress().getPincode());
			sd.setState(s.getAddress().getState());
			sd.setStreetName(s.getAddress().getStreetName());
			sd.setStudentClass(s.getStudentClass());
			sl.add(sd);

		}

		return sl;

	}

	@GetMapping("/getStudents")
	public List<StudentDetails> getStudents() {
		List<Student> list = service.listAllStudents();

		return getStudentDetailList(list);
	}

	public StudentDetails getStudentDetails(long theId) {
		Student s = service.getStudent(theId);
		StudentDetails sd = new StudentDetails();
		sd.setAddressId(s.getAddress().getAddressId());
		sd.setCity(s.getAddress().getCity());
		sd.setCountry(s.getAddress().getCountry());
		sd.setDateOfBirth(s.getDateOfBirth());
		sd.setDateofJoining(s.getDateofJoining());
		sd.setEmail(s.getEmail());
		sd.setFatherName(s.getFatherName());
		sd.setFirstName(s.getFirstName());
		sd.setHouseNumber(s.getAddress().getHouseNumber());
		sd.setId(s.getId());
		sd.setLastName(s.getLastName());
		sd.setPassword(s.getPassword());
		sd.setPhoneNumber(s.getPhoneNumber());
		sd.setPincode(s.getAddress().getPincode());
		sd.setState(s.getAddress().getState());
		sd.setStreetName(s.getAddress().getStreetName());
		sd.setStudentClass(s.getStudentClass());

		return sd;

	}
	
	
	public Employee convertEmployee(EmployeeDetails theEmployee)
	{
		
		Employee e = new Employee();
		EmployeeAddress a = new EmployeeAddress();

		e.setDateOfBirth(theEmployee.getDateOfBirth());
		e.setDateofJoining(theEmployee.getDateofJoining());
		e.setEmail(theEmployee.getEmail());
		e.setFirstName(theEmployee.getEmail());
		e.setFirstName(theEmployee.getFirstName());
		e.setId(theEmployee.getId());
		e.setLastName(theEmployee.getLastName());
		e.setPassword(theEmployee.getPassword());
		e.setPhoneNumber(theEmployee.getPhoneNumber());
		a.setAddressId(theEmployee.getAddressId());
		a.setCity(theEmployee.getCity());
		a.setCountry(theEmployee.getCountry());
		a.setHouseNumber(theEmployee.getHouseNumber());
		a.setPincode(theEmployee.getPincode());
		a.setState(theEmployee.getState());
		a.setStreetName(theEmployee.getStreetName());
		e.setAddress(a);
		a.setEmployee(e);
	return e;
	}

	
	@PostMapping("/changePassword")
	public boolean changePassword(@Valid @RequestBody EmployeeDetails employee) {
	
		Employee e=convertEmployee(employee);
		
		
		
		return service.update(e);
	}
	
	@GetMapping("/products/{studentClass}")
	public List<StudentDetails> filterStudents(@PathVariable(value = "studentClass") String studentClass) {
		if (studentClass.equalsIgnoreCase("all")) {
			List list = getStudents();
			return list;

		}

		List<Student> list = service.filterStudentByClass(studentClass);

		return getStudentDetailList(list);

	}

	@GetMapping("/getStudent/{studentId}")
	public StudentDetails getStudent(@PathVariable(value = "studentId") Long theId) {
		if (service.isStudentExists(theId)) {

			return getStudentDetails(theId);
		}
		return null;
	}

	@GetMapping("/getMarks/{studentId}")
	public Marks getMarks(@PathVariable(value = "studentId") Long theId) {
		if (service.isStudentExists(theId)) {

			return empService.getMarks(theId.intValue());
		}
		return null;
	}

	@PutMapping("/updateTeacher")
	public boolean updateTeacher(@Valid @RequestBody EmployeeDetails theEmployee) {

		Employee e = convertEmployee(theEmployee);

		return service.update(e);
	}

	@PostMapping("/uploadMarks/{id}")
	public boolean uploadMarks(@PathVariable(value = "id") Long theId, @Valid @RequestBody Marks marks) {

		if (service.isStudentExists(marks.getStudentId())) {

			if (empService.getMarks(marks.getStudentId()) != null)
				empService.delete(marks.getStudentId());
			return empService.uploadMarks(marks);
		}

		return false;
	}*/

}
