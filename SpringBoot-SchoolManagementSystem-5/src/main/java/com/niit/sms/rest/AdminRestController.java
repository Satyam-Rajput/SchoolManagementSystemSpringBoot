package com.niit.sms.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.niit.sms.model.Student;
import com.niit.sms.model.StudentAddress;
import com.niit.sms.model.StudentDetails;
import com.niit.sms.model.User;
import com.niit.sms.service.AdminService;
import com.niit.sms.service.LoginService;

@RestController  
@CrossOrigin(origins="*")  
@RequestMapping(value="/api/admin") 
public class AdminRestController {

	@Autowired
	
	private AdminService service;
	@Autowired
	private LoginService loginService;


	
	public Employee convertEmployeeToSave(EmployeeDetails theEmployee)
	{
		
		Employee e = new Employee();
		EmployeeAddress a = new EmployeeAddress();

		e.setDateOfBirth(theEmployee.getDateOfBirth());
		e.setDateofJoining(new java.sql.Date(new Date().getTime()));
		e.setEmail(theEmployee.getEmail());
		e.setFirstName(theEmployee.getEmail());
		e.setFirstName(theEmployee.getFirstName());
		
		e.setLastName(theEmployee.getLastName());
		e.setPassword(theEmployee.getPassword());
		e.setPhoneNumber(theEmployee.getPhoneNumber());
		
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
	public Student convertStudentToSave(StudentDetails theStudent)
	{
		Student s=new Student();
		StudentAddress a=new StudentAddress();
		s.setDateOfBirth(theStudent.getDateOfBirth());
		s.setDateofJoining(new java.sql.Date(new Date().getTime()));
		s.setEmail(theStudent.getEmail());
		s.setFatherName(theStudent.getFatherName());
		s.setFirstName(theStudent.getFirstName());
		
		s.setLastName(theStudent.getLastName());
		s.setPassword(theStudent.getPassword());
		s.setPhoneNumber(theStudent.getPhoneNumber());
		s.setStudentClass(theStudent.getStudentClass());
	
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
		
		public List<EmployeeDetails> getEmployeeDetailList(List<Employee> list) {
			List<EmployeeDetails> sl = new ArrayList<EmployeeDetails>();
			for (Employee s : list) {
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
				
				sl.add(sd);

			}
		

		return sl;

	}


	
	public EmployeeDetails getEmployeeDetails(long theId) {
		Employee s = service.getEmployee(theId);
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
		

		return sd;

	}
	public Employee convertEmployeeToUpdate(EmployeeDetails theEmployee)
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

	@PostMapping("/changePassword/{password}")
public boolean changePassword(@PathVariable(value = "password") String password,@Valid @RequestBody User user)
{
		
		User u=loginService.get(user.getEmail());
		User n=new User();
		n.setPassword(password);
		if(u.getPassword().equals(n.getPassword()))
		{	user.setId(u.getId());
			user.setUserId(u.getUserId());
			user.setRole(u.getRole());
		return loginService.save(user);
		}
		else
			return false;
		
}
	



@PostMapping("/saveTeacher")
public boolean saveTeacher(@Valid @RequestBody EmployeeDetails theEmployee)
{
if(!loginService.isUser(theEmployee.getEmail()))
{
	Employee e=convertEmployeeToSave(theEmployee);
	return service.save(e);
	}

return false;
}



@PostMapping("/saveStudent")
public boolean saveStudent(@Valid @RequestBody StudentDetails theStudent)
{
	if(!(loginService.isUser(theStudent.getEmail())))
	{
		
Student s=convertStudentToSave(theStudent);
		return service.save(s);
			}
	return false;
}

@GetMapping("/getEmployees")
public List<EmployeeDetails> getEmployees()
{
	return getEmployeeDetailList(service.listAllEmployees());
}

@GetMapping("/getStudents")
public List<StudentDetails> getStudents()
{
	List list=service.listAllStudents();
	return getStudentDetailList(list);
}


@GetMapping("/filterStudents/{studentClass}")
public List<StudentDetails> filterStudents(@PathVariable(value = "studentClass") String studentClass)
{
	if(studentClass.equalsIgnoreCase("all"))
	{
		List list=getStudents();
		return list;
		
	}
	List<Student> list = service.filterStudentByClass(studentClass);

	return getStudentDetailList(list);
	
	}

@GetMapping("/getStudent/{studentId}")
public StudentDetails getStudent(@PathVariable(value = "studentId") Long theId)
{
	if(service.isStudentExists(theId))
	{
		return convertToStudentDetails(theId);
	}
	return null;
}
@GetMapping("/getTeacher/{teacherId}")
public EmployeeDetails getTeacher(@PathVariable(value = "teacherId") Long theId)
{
	if(service.isEmployeeExists(theId))
	{
		return getEmployeeDetails(theId);
	}
	return null;
}

@GetMapping("/getEmployeesCount")
public long getEmployeeCount()
{
	return service.countEmployee();
	}

@GetMapping("/getStudentsCount")
public long getStudentCount()
{
	return service.countStudent();
	}
@PutMapping("/updateTeacher/{teacherId}")
public boolean updateTeacher(@PathVariable(value = "teacherId") Long theId,@Valid @RequestBody EmployeeDetails theEmployee)
{
	Employee e=service.getEmployee(theId);
	Employee e1=convertEmployeeToUpdate(theEmployee);
	e1.setId(theId);
	e1.getAddress().setAddressId(e.getAddress().getAddressId());
	e1.setDateofJoining(e.getDateofJoining());
	return service.update(e1);
}

@PutMapping("/updateStudent/{studentId}")
public boolean updateStudent(@PathVariable(value = "studentId") Long theId,@Valid @RequestBody StudentDetails theStudent)
{
	Student s=service.getStudent(theId);
	Student update=convertStudentToUpdate(theStudent);
	update.getAddress().setAddressId(s.getAddress().getAddressId());
	update.setId(theId);
	update.setDateofJoining(s.getDateofJoining());
	return service.update(update);
}

@DeleteMapping("/deleteTeacher/{teacherId}")
public boolean deleteTeacher(@PathVariable(value = "teacherId") Long theId) 
{if (service.isEmployeeExists(theId))
{
  return  service.deleteEmployee(theId);
           }
return false;
}

@DeleteMapping("/deleteStudent/{studentId}")
public boolean deleteStudent(@PathVariable(value = "studentId") Long theId) 
{if (service.isStudentExists(theId))
{
  return  service.deleteStudent(theId);
           }
return false;
}
	
}





















