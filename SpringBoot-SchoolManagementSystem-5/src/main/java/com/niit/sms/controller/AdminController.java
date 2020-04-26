package com.niit.sms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.sms.model.Admin;
import com.niit.sms.model.Employee;
import com.niit.sms.model.EmployeeAddress;
import com.niit.sms.model.Student;
import com.niit.sms.model.StudentAddress;
import com.niit.sms.model.User;
import com.niit.sms.service.AdminService;
import com.niit.sms.service.LoginService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService service;
	@Autowired
	private LoginService loginService;

	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/aboutUs")
	public String aboutUs() {
		return "about-us";
	}
	
	@RequestMapping("/contactUs")
	public String contactus() {
		return "contact-us";
	}
	
	
@GetMapping("/changePasswordPage")

public String changePasswordPage()
{
return "changePassword-admin";	
}

@RequestMapping("/changePassword")
public String changePassword(HttpServletRequest req,Model model)
{
	if(req.getSession(false)!=null&&req.getSession(false).getAttribute("admin")!=null) {
		
	Admin u=(Admin)req.getSession(false).getAttribute("admin");
	
	User n=new User();
	n.setPassword(req.getParameter("password"));
	if(u.getPassword().equals(n.getPassword()))
	{
		u.setPassword(req.getParameter("newPassword"));
	   n.setEmail(u.getEmail());
	   n.setId(u.getId());
	   n.setPassword(req.getParameter("newPassword"));
	   n.setRole("A");
	   n.setUserId(u.getId());
	   loginService.save(n);
	     
	
	if(service.updateAdminInfo(u))
	{
	
	model.addAttribute("usermsg", "Password changed successfully");
	}
	
	else
	{model.addAttribute("error", "failed to change Password");
		
	}
	return "changePassword-admin";
	}
	else
	{
		 model.addAttribute("error", "Incorrect Password");
		
	}
	}
	else
	{
		 model.addAttribute("error", "Please Login Again");
		
		   
		
	}
	return "login-page";
}	

@GetMapping("/adminPage")
public String getAdminPage(HttpServletRequest req,ModelMap model){
	 if(!(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null))
	   {
		 model.addAttribute("error", "Please Login Again");
		   return "login-page";
	   }
	
	
	
return "admin-page";	
}

	@GetMapping("/newTeacher")
	public String addTeacher(HttpServletRequest req,Model model) {
   if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
   {
		Employee theEmp = new Employee();
		model.addAttribute("employee", theEmp);

		return "addTeacher-admin";
   }
   else
   {
	   
	   model.addAttribute("error", "Please Login Again");
	   return "login-page";
	   
   }
   }

	@PostMapping("/saveTeacher")
	public String registerTeacher(HttpServletRequest req, @ModelAttribute("employee") Employee theEmployee, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		
		if (!(loginService.isUser(theEmployee.getEmail()))) {

			EmployeeAddress a = new EmployeeAddress();
			a.setStreetName(req.getParameter("streetName"));
			a.setCity(req.getParameter("city"));
			a.setCountry(req.getParameter("country"));
			a.setState(req.getParameter("state"));
			a.setHouseNumber(Integer.parseInt(req.getParameter("houseNumber")));
			a.setPincode(Integer.parseInt(req.getParameter("pincode")));
			theEmployee.setDateofJoining(new java.sql.Date(new Date().getTime()));
			theEmployee.setAddress(a);
			a.setEmployee(theEmployee);
		

			if(service.save(theEmployee))
			{model.addAttribute("usermsg", "Registered SuccessFully");}
			else
			{model.addAttribute("errormsg", "failed to Register");}

			HttpSession s=req.getSession(false);
			s.setAttribute("studentCount",service.countStudent());
			s.setAttribute("teacherCount",service.countEmployee());
			
		} 
		else {
			model.addAttribute("errormsg", "Email already registered");
		}
		return "addTeacher-admin";
		
		   }
		else
		{
			   model.addAttribute("error", "Please Login Again");
			   return "login-page";
			   			
		}
	}
	
	

	@GetMapping("/getEmployees")
	public String displayTeachers(HttpServletRequest req,ModelMap model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		List<Employee> employees = service.listAllEmployees();
		if(employees.size()==0)
			model.addAttribute("error", "No record found");
		model.addAttribute("employees", employees);
		return "displayTeacherDetails-admin";
		   }
		else
		{
			model.addAttribute("error", "Please Login Again");
			   return "login-page";
		}
	}

	@GetMapping("/newStudent")
	public String addStudent(HttpServletRequest req,Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		Student theStudent = new Student();
		model.addAttribute("student", theStudent);

		return "addStudent-admin";
		   }
		else
		{
			model.addAttribute("error", "Please Login Again");
			   return "login-page";
		}
	}

	@PostMapping("/saveStudent")
	public String registerStudent(HttpServletRequest req, @ModelAttribute("student") Student theStudent, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		
		if (!loginService.isUser(theStudent.getEmail())) {
			StudentAddress a = new StudentAddress();
			a.setStreetName(req.getParameter("streetName"));
			a.setCity(req.getParameter("city"));
			a.setCountry(req.getParameter("country"));
			a.setState(req.getParameter("state"));
			a.setHouseNumber(Integer.parseInt(req.getParameter("houseNumber")));
			a.setPincode(Integer.parseInt(req.getParameter("pincode")));
			theStudent.setDateofJoining(new java.sql.Date(new Date().getTime()));
			theStudent.setAddress(a);
			a.setStudent(theStudent);
			
			if(service.save(theStudent))
			{model.addAttribute("usermsg", "Registered SuccessFully");}
			else
			{model.addAttribute("errormsg", "failed to Register");}
		
			HttpSession s=req.getSession(false);
			s.setAttribute("studentCount",service.countStudent());
			s.setAttribute("teacherCount",service.countEmployee());
			
		}
		
		else
		{
			model.addAttribute("errormsg", "Email already registered");
		}
		return "addStudent-admin";
		   }
		else
		{
			model.addAttribute("error", "Please Login Again");
			   return "login-page";
		}
	}

	@GetMapping("/getStudents")
	public String displayStudents(HttpServletRequest req,ModelMap model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		List<Student> students = service.listAllStudents();
		if(students.size()==0)
			model.addAttribute("error", "No record found");
		model.addAttribute("students", students);
		return "displayStudentDetails-admin";

		   }
				else
				{
					model.addAttribute("error", "Please Login Again");
					   return "login-page";
				}
		   }
	
	@PostMapping("/filterStudent")
	public String filterStudents(HttpServletRequest req,ModelMap model)
	{
		
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
			String studentClass=req.getParameter("studentClass");
			if(studentClass.equalsIgnoreCase("all"))
				return "redirect:../admin/getStudents";
		List<Student> students = service.filterStudentByClass(studentClass);
		if(students.size()==0)
			model.addAttribute("error", "No record found");
		model.addAttribute("students", students);
		return "displayStudentDetails-admin";

		   }
				else
				{
					model.addAttribute("error", "Please Login Again");
					   return "login-page";
				}
		
	}
	

	@GetMapping("/findStudent")
	public String findStudent(HttpServletRequest req, Model model) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {	
		
		return "find-student-admin";
		   }
		else
		{
			model.addAttribute("error", "Please Login Again");
			   return "login-page";
		}}

	@GetMapping("/findTeacher")
	public String findTeacher(HttpServletRequest req, Model model) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {return "find-teacher-admin";
		   }
		else
		{
			model.addAttribute("error", "Please Login Again");
			   return "login-page";
		}}

	
	
	@PostMapping("/getStudent")
	public String searchStudent(HttpServletRequest req, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		int theId = Integer.parseInt(req.getParameter("studentId"));

		if (service.isStudentExists(theId)) {
			Student student = service.getStudent(theId);
			model.addAttribute("student", student);

		} else {
			model.addAttribute("error", " Roll No not found");
		}
		return "find-student-admin";
		   }
				else
				{
					model.addAttribute("error", "Please Login Again");
					   return "login-page";
				}
	}
	@GetMapping("/viewStudent")
	
	public String viewStudentDetails(HttpServletRequest req,@RequestParam("id") int theId, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		

		if (service.isStudentExists(theId)) {
			Student student = service.getStudent(theId);
			model.addAttribute("student", student);

		} else {
			model.addAttribute("error", "Roll No not found");
		}
		return "find-student-admin";
		   }
				else
				{
					model.addAttribute("error", "Please Login Again");
					   return "login-page";
				}
	}

	
	
	
	
	@GetMapping("/viewTeacher")
	public String viewTeacherDetails(HttpServletRequest req,@RequestParam("id") int theId, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		
		if (service.isEmployeeExists(theId)) {
			Employee employee = service.getEmployee(theId);
			model.addAttribute("teacher", employee);

		} else {
			model.addAttribute("error", "Employee ID not found");
		}
		return "find-teacher-admin";

	   }
			else
			{
				model.addAttribute("error", "Please Login Again");
				   return "login-page";
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/getTeacher")
	public String searchTeacher(HttpServletRequest req, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		int theId = Integer.parseInt(req.getParameter("teacherId"));

		if (service.isEmployeeExists(theId)) {
			Employee employee = service.getEmployee(theId);
			model.addAttribute("teacher", employee);

		} else {
			model.addAttribute("error", "Employee ID not found");
		}
		return "find-teacher-admin";

	   }
			else
			{
				model.addAttribute("error", "Please Login Again");
				   return "login-page";
			}
	}

	@GetMapping("/updateTeacher")
	public String updateTeacherPage(HttpServletRequest req, @RequestParam("id") int theId, Model model) {
		
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {if (service.isEmployeeExists(theId)) {
			Employee employee = service.getEmployee(theId);
			model.addAttribute("employee", employee);
		} else {

			model.addAttribute("errormsg", "User Doesn't exists");

		}

		return "update-teacherDetails-admin";

	   }
			else
			{
				model.addAttribute("error", "Please Login Again");
				   return "login-page";
			}}
	
	
	@PostMapping("/updateTeacherDetails")
	public String updateTeacherDetails(HttpServletRequest req, @ModelAttribute("employee") Employee theEmployee, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
			
			try {
		  Employee e=service.getEmployee(theEmployee.getId());
		  
		
			EmployeeAddress a = new EmployeeAddress();
			a.setStreetName(req.getParameter("streetName"));
			a.setCity(req.getParameter("city"));
			a.setCountry(req.getParameter("country"));
			a.setState(req.getParameter("state"));
			a.setHouseNumber(Integer.parseInt(req.getParameter("houseNumber")));
			a.setPincode(Integer.parseInt(req.getParameter("pincode")));
			theEmployee.setDateofJoining(e.getDateofJoining());
			theEmployee.setAddress(a);
			a.setEmployee(theEmployee);
			

			if(service.update(theEmployee))
			{model.addAttribute("usermsg", "Updated SuccessFully");}
			else
			{model.addAttribute("errormsg", "failed to Update");}

		
		return "update-teacherDetails-admin";
		
		   }catch(Exception e) 
			
			
			{model.addAttribute("errormsg", "Error in Update");  return "update-teacherDetails-admin";}
			}
		else
		{
			   model.addAttribute("error", "Please Login Again");
			   return "login-page";
			   			
		}
	}

	

	@GetMapping("/deleteTeacher")
	public String deleteTeacherRecord(HttpServletRequest req, @RequestParam("id") int theId, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {if (service.isEmployeeExists(theId)) {
			if(service.deleteEmployee(theId))
			{model.addAttribute("usermsg", "Deleted SuccessFully");}
			else
			{model.addAttribute("error", "failed to Delete");}
			
		
			HttpSession s=req.getSession(false);
			s.setAttribute("studentCount",service.countStudent());
			s.setAttribute("teacherCount",service.countEmployee());
			
		   }
		   else {

			model.addAttribute("error", "User Doesn't exists");

		}

		return "redirect:../admin/getEmployees";

	   }
			else
			{
				model.addAttribute("error", "'Please Login Again");
				   return "login-page";
			}}

	@GetMapping("/updateStudent")
	public String updateStudentPage(HttpServletRequest req, @RequestParam("id") int theId, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {if (service.isStudentExists(theId)) {
			Student student = service.getStudent(theId);
			model.addAttribute("student", student);
		} else {

			model.addAttribute("errormsg", "User Doesn't exists");

		}
		return "update-studentDetails-admin";
		

	   }
			else
			{
				model.addAttribute("error", "'Please Login Again");
				   return "login-page";
			}}
	
	
	
	

	@PostMapping("/updateStudentDetails")
	public String updateStudentDetails(HttpServletRequest req, @ModelAttribute("student") Student theStudent, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		try {
			 Student s=service.getStudent(theStudent.getId());
	
			StudentAddress a = new StudentAddress();
			a.setStreetName(req.getParameter("streetName"));
			a.setCity(req.getParameter("city"));
			a.setCountry(req.getParameter("country"));
			a.setState(req.getParameter("state"));
			a.setHouseNumber(Integer.parseInt(req.getParameter("houseNumber")));
			a.setPincode(Integer.parseInt(req.getParameter("pincode")));
			theStudent.setDateofJoining(s.getDateofJoining());
			theStudent.setAddress(a);
			a.setStudent(theStudent);
		
			if(service.update(theStudent))
			{model.addAttribute("usermsg", "Updated SuccessFully");}
			else
			{model.addAttribute("errormsg", "failed to Update");}
		
		
		
		return "update-studentDetails-admin";
		   }catch(Exception e) 
		
		
		{model.addAttribute("errormsg", "Error in Update");  return "update-studentDetails-admin";}
		   }
		else
		{
			model.addAttribute("error", "Please Login Again");
			   return "login-page";
		}
	}

	
	
	
	

	@GetMapping("/deleteStudent")
	public String deleteStudentRecord(HttpServletRequest req, @RequestParam("id") int theId, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {if (service.isStudentExists(theId)) {
			if(service.deleteStudent(theId))
			{model.addAttribute("usermsg", "Deleted SuccessFully");}
			else
			{model.addAttribute("error", "failed to Delete");}
			
		
			HttpSession s=req.getSession(false);
			s.setAttribute("studentCount",service.countStudent());
			s.setAttribute("teacherCount",service.countEmployee());
			
		   } else {


			model.addAttribute("error", "User Doesn't exists");

		}

		return "redirect:../admin/getStudents";

	   }
			else
			{
				model.addAttribute("error", "Please Login Again");
				   return "login-page";
			}}

}
