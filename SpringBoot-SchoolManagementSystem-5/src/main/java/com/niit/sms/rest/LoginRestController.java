package com.niit.sms.rest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.sms.model.Employee;
import com.niit.sms.model.EmployeeDetails;
import com.niit.sms.model.Student;
import com.niit.sms.model.StudentDetails;
import com.niit.sms.model.User;
import com.niit.sms.service.AdminService;
import com.niit.sms.service.LoginService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/api/user")
public class LoginRestController {

	@Autowired
	private LoginService userService;
	@Autowired
	private AdminService adminService;

	
	
	public StudentDetails convertToStudentDetails(long theId)
	{
		Student student= adminService.getStudent(theId);
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
	   s.setRole("S");
	    
	    return s;
	}
	public EmployeeDetails getEmployeeDetails(long theId) {
		Employee s = adminService.getEmployee(theId);
		EmployeeDetails sd = new EmployeeDetails();
		sd.setAddressId(s.getAddress().getAddressId());
		sd.setCity(s.getAddress().getCity());
		sd.setCountry(s.getAddress().getCountry());
		sd.setDateOfBirth(s.getDateOfBirth());
		sd.setDateofJoining(s.getDateofJoining());
		sd.setEmail(s.getEmail());
		
		sd.setFirstName(s.getFirstName());
		sd.setHouseNumber(s.getAddress().getHouseNumber());
		sd.setId(s.getId());
		sd.setLastName(s.getLastName());
		sd.setPassword(s.getPassword());
		sd.setPhoneNumber(s.getPhoneNumber());
		sd.setPincode(s.getAddress().getPincode());
		sd.setState(s.getAddress().getState());
		sd.setStreetName(s.getAddress().getStreetName());
		
   sd.setRole("T");
		return sd;

	}
	
@PostMapping("/login")
public Object login(@Valid @RequestBody User theUser)

{
	if(userService.isUser(theUser.getEmail()))
	{
	User usr = userService.checkUser(theUser);
	
	if (usr == null) {
		
		return null;
		
	} else {
		if (usr.getRole().equalsIgnoreCase("A")) {
			
			return usr;


		} else if (usr.getRole().equalsIgnoreCase("T")) {
			
			EmployeeDetails theEmployee=getEmployeeDetails(usr.getUserId());
			
			return theEmployee;
			

		} else if (usr.getRole().equalsIgnoreCase("S")) {
			StudentDetails theStudent=convertToStudentDetails(usr.getUserId());
			
			
			
			return theStudent;

		}

	}
	}
	else
	{
		return null;
		
	}
	return null;
	
}
}
