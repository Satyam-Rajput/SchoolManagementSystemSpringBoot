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
	
@GetMapping("/changePasswordPage")

public String changePasswordPage()
{
return "changePassword-admin";	
}

@RequestMapping("/changePassword")
public String changePassword(HttpServletRequest req,Model model)
{
	if(req.getSession(false)!=null&&req.getSession(false).getAttribute("admin")!=null) {
		
	User u=(User)req.getSession(false).getAttribute("admin");
	
	User n=new User();
	n.setPassword(req.getParameter("password"));
	if(u.getPassword().equals(n.getPassword()))
	{
		u.setPassword(req.getParameter("newPassword"));
	
	
	
	if(loginService.save(u))
	{
	
	model.addAttribute("usermsg", "<script>alert('Password changed successfully')</script>");
	}
	
	else
	{model.addAttribute("error", "<script>alert('fail to change Password')</script>");
		
	}
	return "admin-page";
	}
	else
	{
		 model.addAttribute("error", "<script>alert('Incorrect Password')</script>");
		
	}
	}
	else
	{
		 model.addAttribute("error", "<script>alert('Please Login Again')</script>");
		
		   
		
	}
	return "login-page";
}	

@GetMapping("/adminPage")
public String getAdminPage(HttpServletRequest req,ModelMap model){
	 if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
	   {
	HttpSession s=req.getSession(false);
	s.setAttribute("studentCount",service.countStudent());
	s.setAttribute("teacherCount",service.countEmployee());
	   }
	 else
	 {
		 model.addAttribute("error", "<script>alert('Please Login Again')</script>");
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
	   
	   model.addAttribute("error", "<script>alert('Please Login Again')</script>");
	   return "login-page";
	   
   }
   }

	@PostMapping("/saveTeacher")
	public String saveTeacher(HttpServletRequest req, @ModelAttribute("employee") Employee theEmployee, Model model) {
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
			{model.addAttribute("usermsg", "<script>alert('Registered SuccessFully')</script>");}
			else
			{model.addAttribute("usermsg", "<script>alert('failed to Register')</script>");}

			HttpSession s=req.getSession(false);
			s.setAttribute("studentCount",service.countStudent());
			s.setAttribute("teacherCount",service.countEmployee());
			
		} 
		else {
			model.addAttribute("error", "<script>alert('Email already registered')</script>");
		}
		return "admin-page";
		
		   }
		else
		{
			   model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
			   			
		}
	}
	
	

	@GetMapping("/getEmployees")
	public String displayEmployees(HttpServletRequest req,ModelMap model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		List<Employee> employees = service.listAllEmployees();
		if(employees.size()==0)
			model.addAttribute("error", "<script>alert('No record found')</script>");
		model.addAttribute("employees", employees);
		return "displayTeacherDetails-admin";
		   }
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
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
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}
	}

	@PostMapping("/saveStudent")
	public String saveStudent(HttpServletRequest req, @ModelAttribute("student") Student theStudent, Model model) {
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
			model.addAttribute("usermsg", "<script>alert('Registered SuccessFully')</script>");
			if(service.save(theStudent))
			{model.addAttribute("usermsg", "<script>alert('Registered SuccessFully')</script>");}
			else
			{model.addAttribute("usermsg", "<script>alert('failed to Register')</script>");}
		
			HttpSession s=req.getSession(false);
			s.setAttribute("studentCount",service.countStudent());
			s.setAttribute("teacherCount",service.countEmployee());
			
		}
		
		else
		{
			model.addAttribute("error", "<script>alert('Email already registered')</script>");
		}
		return "admin-page";
		   }
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}
	}

	@GetMapping("/getStudents")
	public String displayStudents(HttpServletRequest req,ModelMap model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		List<Student> students = service.listAllStudents();
		if(students.size()==0)
			model.addAttribute("error", "<script>alert('No record found')</script>");
		model.addAttribute("students", students);
		return "displayStudentDetails-admin";

		   }
				else
				{
					model.addAttribute("error", "<script>alert('Please Login Again')</script>");
					   return "login-page";
				}
		   }
	
	@PostMapping("/filterStudent")
	public String filterStudent(HttpServletRequest req,ModelMap model)
	{
		
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
			String studentClass=req.getParameter("studentClass");
			if(studentClass.equalsIgnoreCase("all"))
				return "redirect:../admin/getStudents";
		List<Student> students = service.filterStudentByClass(studentClass);
		if(students.size()==0)
			model.addAttribute("error", "<script>alert('No record found')</script>");
		model.addAttribute("students", students);
		return "displayStudentDetails-admin";

		   }
				else
				{
					model.addAttribute("error", "<script>alert('Please Login Again')</script>");
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
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}}

	@GetMapping("/findTeacher")
	public String findTeacher(HttpServletRequest req, Model model) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {return "find-teacher-admin";
		   }
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}}

	
	
	@PostMapping("/getStudent")
	public String getStudent(HttpServletRequest req, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		int theId = Integer.parseInt(req.getParameter("studentId"));

		if (service.isStudentExists(theId)) {
			Student student = service.getStudent(theId);
			model.addAttribute("student", student);

		} else {
			model.addAttribute("usermsg", "<script>alert('Student id not found')</script>");
		}
		return "find-student-admin";
		   }
				else
				{
					model.addAttribute("error", "<script>alert('Please Login Again')</script>");
					   return "login-page";
				}
	}
	@GetMapping("/viewStudent")
	
	public String viewStudent(HttpServletRequest req,@RequestParam("id") int theId, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		

		if (service.isStudentExists(theId)) {
			Student student = service.getStudent(theId);
			model.addAttribute("student", student);

		} else {
			model.addAttribute("usermsg", "<script>alert('Student id not found')</script>");
		}
		return "find-student-admin";
		   }
				else
				{
					model.addAttribute("error", "<script>alert('Please Login Again')</script>");
					   return "login-page";
				}
	}

	
	
	
	
	@GetMapping("/viewTeacher")
	public String viewTeacher(HttpServletRequest req,@RequestParam("id") int theId, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		
		if (service.isEmployeeExists(theId)) {
			Employee employee = service.getEmployee(theId);
			model.addAttribute("teacher", employee);

		} else {
			model.addAttribute("usermsg", "<script>alert('Teacher id not found')</script>");
		}
		return "find-teacher-admin";

	   }
			else
			{
				model.addAttribute("error", "<script>alert('Please Login Again')</script>");
				   return "login-page";
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/getTeacher")
	public String getTeacher(HttpServletRequest req, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
		int theId = Integer.parseInt(req.getParameter("teacherId"));

		if (service.isEmployeeExists(theId)) {
			Employee employee = service.getEmployee(theId);
			model.addAttribute("teacher", employee);

		} else {
			model.addAttribute("usermsg", "<script>alert('Teacher id not found')</script>");
		}
		return "find-teacher-admin";

	   }
			else
			{
				model.addAttribute("error", "<script>alert('Please Login Again')</script>");
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

			model.addAttribute("error", "<script>alert('User Doesn't exists')</script>");

		}

		return "update-teacherDetails-admin";

	   }
			else
			{
				model.addAttribute("error", "<script>alert('Please Login Again')</script>");
				   return "login-page";
			}}
	
	
	@PostMapping("/updateTeacherDetails")
	public String updateTeacher(HttpServletRequest req, @ModelAttribute("employee") Employee theEmployee, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
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
			model.addAttribute("usermsg", "<script>alert('Updated SuccessFully')</script>");

			if(service.update(theEmployee))
			{model.addAttribute("usermsg", "<script>alert('Updated SuccessFully')</script>");}
			else
			{model.addAttribute("usermsg", "<script>alert('failed to Update')</script>");}

		
		return "admin-page";
		
		   }
		else
		{
			   model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
			   			
		}
	}

	

	@GetMapping("/deleteTeacher")
	public String deleteTeacher(HttpServletRequest req, @RequestParam("id") int theId, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {if (service.isEmployeeExists(theId)) {
			if(service.deleteEmployee(theId))
			{model.addAttribute("usermsg", "<script>alert('Deleted SuccessFully')</script>");}
			else
			{model.addAttribute("usermsg", "<script>alert('failed to Delete')</script>");}
			
		
			HttpSession s=req.getSession(false);
			s.setAttribute("studentCount",service.countStudent());
			s.setAttribute("teacherCount",service.countEmployee());
			
		   }
		   else {

			model.addAttribute("error", "<script>alert('User Doesn't exists')</script>");

		}

		return "admin-page";

	   }
			else
			{
				model.addAttribute("error", "<script>alert('Please Login Again')</script>");
				   return "login-page";
			}}

	@GetMapping("/updateStudent")
	public String updateStudentPage(HttpServletRequest req, @RequestParam("id") int theId, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {if (service.isStudentExists(theId)) {
			Student student = service.getStudent(theId);
			model.addAttribute("student", student);
		} else {

			model.addAttribute("error", "<script>alert('User Doesn't exists')</script>");

		}
		return "update-studentDetails-admin";
		

	   }
			else
			{
				model.addAttribute("error", "<script>alert('Please Login Again')</script>");
				   return "login-page";
			}}
	
	
	
	

	@PostMapping("/updateStudentDetails")
	public String updateStudent(HttpServletRequest req, @ModelAttribute("student") Student theStudent, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {
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
			{model.addAttribute("usermsg", "<script>alert('Updated SuccessFully')</script>");}
			else
			{model.addAttribute("usermsg", "<script>alert('failed to Update')</script>");}
		
		
		
		return "admin-page";
		   }
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}
	}

	
	
	
	

	@GetMapping("/deleteStudent")
	public String deleteStudent(HttpServletRequest req, @RequestParam("id") int theId, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("admin")!=null)
		   {if (service.isStudentExists(theId)) {
			if(service.deleteStudent(theId))
			{model.addAttribute("usermsg", "<script>alert('Deleted SuccessFully')</script>");}
			else
			{model.addAttribute("usermsg", "<script>alert('failed to Delete')</script>");}
			
		
			HttpSession s=req.getSession(false);
			s.setAttribute("studentCount",service.countStudent());
			s.setAttribute("teacherCount",service.countEmployee());
			
		   } else {


			model.addAttribute("error", "<script>alert('User Doesn't exists')</script>");

		}

		return "admin-page";

	   }
			else
			{
				model.addAttribute("error", "<script>alert('Please Login Again')</script>");
				   return "login-page";
			}}

}
