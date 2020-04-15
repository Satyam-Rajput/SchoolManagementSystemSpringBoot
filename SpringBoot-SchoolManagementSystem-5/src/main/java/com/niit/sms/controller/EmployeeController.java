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
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.sms.model.Employee;
import com.niit.sms.model.EmployeeAddress;
import com.niit.sms.model.Marks;
import com.niit.sms.model.Student;
import com.niit.sms.model.User;
import com.niit.sms.service.AdminService;
import com.niit.sms.service.EmployeeService;
import com.niit.sms.service.LoginService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private AdminService service;
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private EmployeeService empService;
	@GetMapping("/teacherPage")
	
	public String teacherDashboard()
	{
	return "teacher-page";	
		
	}
	
	@GetMapping("/getStudents")
	public String displayStudents(HttpServletRequest req,ModelMap model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {
		List<Student> students = service.listAllStudents();
		model.addAttribute("students", students);
		if(students.size()==0)
			model.addAttribute("error", "<script>alert('No record found')</script>");
		return "listStudents-teacher";

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
		
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {
			String studentClass=req.getParameter("studentClass");
			if(studentClass.equalsIgnoreCase("all"))
				return "redirect:../employee/getStudents";
		List<Student> students = service.filterStudentByClass(studentClass);
		if(students.size()==0)
			model.addAttribute("error", "<script>alert('No record found')</script>");
		model.addAttribute("students", students);
		return "listStudents-teacher";

		   }
				else
				{
					model.addAttribute("error", "<script>alert('Please Login Again')</script>");
					   return "login-page";
				}
		
	}

	
	@GetMapping("/findStudent")
	public String findStudent(HttpServletRequest req, Model model) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {	
		
		return "getStudent-teacher";
		   }
		
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}}

	@PostMapping("/getStudent")
	public String getStudent(HttpServletRequest req, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {
		int theId = Integer.parseInt(req.getParameter("studentId"));

		if (service.isStudentExists(theId)) {
			Student student = service.getStudent(theId);
			Marks marks=empService.getMarks(theId);
			model.addAttribute("student", student);
			

		} else {
			model.addAttribute("usermsg", "<script>alert('Student id not found')</script>");
		}
		return "getStudent-teacher";
		   }
				else
				{
					model.addAttribute("error", "<script>alert('Please Login Again')</script>");
					   return "login-page";
				}
	}
	
	
	
	@GetMapping("/viewMarksPage")
	public String viewStudentMarks(HttpServletRequest req, Model model) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {	
		
		return "studentMarks-teacher";
		   }
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}}
	
	
	
	
	
	@PostMapping("/getStudentMarks")
	public String getStudentMarks(HttpServletRequest req, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {
		int theId = Integer.parseInt(req.getParameter("studentId"));

		if (service.isStudentExists(theId)) {
			Student student = service.getStudent(theId);
			Marks marks=empService.getMarks(theId);
			model.addAttribute("student", student);
			model.addAttribute("marks", marks);
			
			
	if(marks==null)
	{
		model.addAttribute("error", "<script>alert('Marks Not Uploaded yet')</script>");
		return "teacher-page";
	}

		} else {
			model.addAttribute("usermsg", "<script>alert('Student id not found')</script>");
			return "teacher-page";
		}
		return "studentMarks-teacher";
		   }
				else
				{
					model.addAttribute("error", "<script>alert('Please Login Again')</script>");
					   return "login-page";
				}
	}
	
	
	
	
	
	@GetMapping("/updateDetails")
	public String updateTeacher(HttpServletRequest req, Model model) {
		
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {
			
			
			Employee employee = (Employee) req.getSession(false).getAttribute("employee");
			model.addAttribute("employee", employee);
		

		return "updateTeacherInformation-teacher";

	   }
			else
			{
				model.addAttribute("error", "<script>alert('Please Login Again')</script>");
				   return "login-page";
			}}
	
	@PostMapping("/updateInformation")
	public String updateTeacherDetails(HttpServletRequest req, @ModelAttribute("employee") Employee theEmployee, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {
		
		Employee e=(Employee)req.getSession(false).getAttribute("employee");

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
			model.addAttribute("usermsg", "<script>alert('Details Updated SuccessFully')</script>");

			if(service.update(theEmployee))
			{model.addAttribute("usermsg", "<script>alert('Updated SuccessFully')</script>");
			
			HttpSession s=req.getSession(false);
			s.setAttribute("employee", theEmployee);
			
			}
			else
			{model.addAttribute("usermsg", "<script>alert('failed to Updated')</script>");}
			
		
		return "teacher-page";
		
		   }
		else
		{
			   model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
			   			
		}
	}
	
	
	@GetMapping("/viewDetails")
	public String viewDetails(HttpServletRequest req, Model model) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {	
		
		Employee employee=(Employee)(req.getSession(false).getAttribute("employee"));
			
		model.addAttribute("teacher",employee);
		return "viewTeacherDetails-teacher";
		   }
		
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}}
	
	@GetMapping("/uploadMarksPage")
	public String uploadMarksPage(HttpServletRequest req, Model model) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {	
		
		Marks marks=new Marks();
		
			
		model.addAttribute("marks",marks);
		return "uploadMarks-teacher";
		   }
		
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}}
	
	
	
	@PostMapping("/uploadMarks")
	public String uploadMarks(HttpServletRequest req, Model model,@ModelAttribute("marks") Marks marks) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {	
			model.addAttribute("usermsg", "<script>alert('Upload Successful')</script>");
		
			if(service.isStudentExists(marks.getStudentId())) {
			
		if(empService.getMarks(marks.getStudentId())!=null)
			empService.delete(marks.getStudentId());
		if(empService.uploadMarks(marks))
		{model.addAttribute("usermsg", "<script>alert('Uploaded SuccessFully')</script>");
		

		}
		else
		{model.addAttribute("usermsg", "<script>alert('failed to Upload')</script>");}}else
		{
			
			model.addAttribute("usermsg", "<script>alert('Student doesn't Exists')</script>");	
		}
			
		
		return "teacher-page";
		   }
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}
			
		   
		   }
	
	
	

	@GetMapping("/changePasswordPage")
	public String updatePasswordPage(HttpServletRequest req, Model model) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("employee")!=null)
		   {	
		return "changePassword-teacher";
		   }
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}}
	
	
	@RequestMapping("/changePassword")
	public String changePassword(HttpServletRequest req,Model model)
	{
		if(req.getSession(false)!=null) {
			
		Employee u=(Employee)req.getSession(false).getAttribute("employee");
		if(u!=null) {
		User n=new User();
		n.setPassword(req.getParameter("password"));
		if(u.getPassword().equals(n.getPassword()))
		{
			
		
		
		u.setPassword(req.getParameter("newPassword"));
		if(service.update(u))
			
		{model.addAttribute("usermsg", "<script>alert('Changed SuccessFully')</script>");
		
		
		
		}
		else
		{model.addAttribute("usermsg", "<script>alert('failed to Change Password')</script>");}
		HttpSession s=req.getSession(false);
		s.setAttribute("employee", u);
		
		
		model.addAttribute("usermsg", "<script>alert('Password changed successfully')</script>");
		return "teacher-page";
		}
		else
		{
			 model.addAttribute("error", "<script>alert('Incorrect Password')</script>");
			
		}
		}else
		{
			 model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			
			   
			
		}
		
		}
		else
		{
			 model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			
			   
			
		}
		return "login-page";
	}	
	
}