package com.niit.sms.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.sms.model.Marks;
import com.niit.sms.model.Student;
import com.niit.sms.model.StudentAddress;
import com.niit.sms.model.StudentDetails;
import com.niit.sms.service.AdminService;
import com.niit.sms.service.EmployeeService;
import com.niit.sms.service.LoginService;

@RestController  
@CrossOrigin(origins="*")  
@RequestMapping(value="/api/student") 
public class StudentRestController {

	@Autowired
	private AdminService service;
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private EmployeeService eservice;	
	
public Student convertStudentToUpdate(StudentDetails theStudent)
{
	Student s=new Student();
	StudentAddress a=new StudentAddress();
	s.setDateOfBirth(theStudent.getDateOfBirth());
	s.setDateofJoining(theStudent.getDateofJoining());
	s.setEmail(theStudent.getEmail());
	s.setFatherName(theStudent.getFatherName());
	s.setFirstName(theStudent.getFirstName());
	s.setId(theStudent.getId());
	s.setLastName(theStudent.getLastName());
	s.setPassword(theStudent.getPassword());
	s.setPhoneNumber(theStudent.getPhoneNumber());
	s.setStudentClass(theStudent.getStudentClass());
	a.setAddressId(theStudent.getAddressId());
	a.setCity(theStudent.getCity());
	a.setCountry(theStudent.getCountry());
	a.setHouseNumber(theStudent.getHouseNumber());
	a.setPincode(theStudent.getPincode());
	a.setState(theStudent.getState());
	a.setStreetName(theStudent.getStreetName());
   s.setAddress(a);
	a.setStudent(s);
	return s;
	
}
public StudentDetails convertToStudentDetails(long theId)
{
	Student student= service.getStudent(theId);
	StudentDetails s=new StudentDetails();
	s.setAddressId(student.getAddress().getAddressId());
    s.setCity(student.getAddress().getCity());
	s.setCountry(student.getAddress().getCountry());
	s.setDateOfBirth(student.getDateOfBirth());
	s.setDateofJoining(student.getDateofJoining());
	s.setEmail(student.getEmail());
	s.setFatherName(student.getEmail());
	s.setFirstName(student.getFirstName());
	s.setHouseNumber(student.getAddress().getHouseNumber());
	s.setId(student.getId());
	s.setLastName(student.getLastName());
	s.setPassword(student.getPassword());
    s.setPhoneNumber(student.getPhoneNumber());
    s.setPincode(student.getAddress().getPincode());
    s.setState(student.getAddress().getState());
    s.setStreetName(student.getAddress().getStreetName());
    s.setStudentClass(student.getStudentClass());
    return s;
}
	
	@GetMapping("/getDetails/{id}")
	public StudentDetails getStudentDetails(@PathVariable(value = "id") Long theId)
	{
		
		
		
		
		
	    
      return convertToStudentDetails(theId);
		
	}
	
	@GetMapping("/getMarks/{id}")
	public Marks getMarks(@PathVariable(value = "id") Long theId)
	{
		
		return eservice.getMarks(theId.intValue());
	}
	
	@PostMapping("/updateDetails")
	public boolean updateStudentDetails(@Valid @RequestBody StudentDetails theStudent)
	{
		Student s=convertStudentToUpdate(theStudent);
		return service.update(s);
	}
	@PostMapping("/changePassword")
	public boolean changePassword(@Valid @RequestBody StudentDetails theStudent)
	{
		Student s=convertStudentToUpdate(theStudent);
		
		return service.update(s);
	}
	
}
