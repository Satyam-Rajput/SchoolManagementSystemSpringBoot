package com.niit.sms.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.sms.model.Employee;
import com.niit.sms.model.Marks;
import com.niit.sms.model.Student;
import com.niit.sms.model.StudentAddress;
import com.niit.sms.model.User;
import com.niit.sms.repository.EmployeeRepository;
import com.niit.sms.repository.StudentRepository;
import com.niit.sms.service.*;
@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private AdminService service;
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private EmployeeService eservice;
	
@GetMapping("/studentPage")
	
	public String studentDashboard()
	{
	return "student-page";	
		
	}
	
	@GetMapping("/viewDetails")
	public String viewDetails(HttpServletRequest req, Model model) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("student")!=null)
		   {	
		
		Student student=(Student)(req.getSession(false).getAttribute("student"));
			
		model.addAttribute("student",student);
		return "viewStudentDetails-student";
		   }
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}}
	
	@GetMapping("/viewMarks")
	public String viewMarks(HttpServletRequest req, Model model) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("student")!=null)
		   {	
		
		Student student=(Student)(req.getSession(false).getAttribute("student"));
		int id=student.getId().intValue();	
		Marks marks=eservice.getMarks(id);
		model.addAttribute("marks",marks);
		model.addAttribute("student",student);
		
		if(marks==null)
		{
			model.addAttribute("error", "<script>alert('Marks Not Uploaded yet')</script>");
			return "student-page";
		}
		return "viewMarks-student";
		   }
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}}
	
	
	
	
	
	
	
	@GetMapping("/updateDetails")
	public String updateStudent(HttpServletRequest req,Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("student")!=null)
		   {
			
			Student student = (Student)req.getSession(false).getAttribute("student");
			model.addAttribute("student", student);
		
			return "updateDetails-student";

	   }
			else
			{
				model.addAttribute("error", "<script>alert('Please Login Again')</script>");
				   return "login-page";
			}}
	
	
	
	

	@PostMapping("/updateStudentDetails")
	public String updateStudentDetails(HttpServletRequest req, @ModelAttribute("student") Student theStudent, Model model) {
		if(req.getSession(false)!=null && req.getSession(false).getAttribute("student")!=null)
		   {
		 Student s=(Student) req.getSession(false).getAttribute("student");
	
			StudentAddress a = new StudentAddress();
			a.setStreetName(req.getParameter("streetName"));
			a.setCity(req.getParameter("city"));
			a.setCountry(s.getAddress().getCountry());
			a.setState(s.getAddress().getState());
			a.setHouseNumber(Integer.parseInt(req.getParameter("houseNumber")));
			a.setPincode(Integer.parseInt(req.getParameter("pincode")));
			theStudent.setDateOfBirth(s.getDateOfBirth());
			theStudent.setStudentClass(s.getStudentClass());
			theStudent.setDateofJoining(s.getDateofJoining());
			theStudent.setAddress(a);
			a.setStudent(theStudent);
			model.addAttribute("usermsg", "<script>alert('Updated SuccessFully')</script>");
			if(service.update(theStudent))
	{model.addAttribute("usermsg", "<script>alert('Updated SuccessFully')</script>");
			
		
			
			}
			else
			{model.addAttribute("usermsg", "<script>alert('failed to Update')</script>");}
		
			req.getSession(false).setAttribute("student",theStudent);
		
		
		return "student-page";
		   }
		else
		{
			model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			   return "login-page";
		}
	}

	
	


	@GetMapping("/changePasswordPage")
	public String updatePasswordPage(HttpServletRequest req, Model model) {

		if(req.getSession(false)!=null && req.getSession(false).getAttribute("student")!=null)
		   {	
		return "changePassword-student";
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
			
		Student u=(Student)req.getSession(false).getAttribute("student");
		if(u!=null) 
		
		{
		User n=new User();
		n.setPassword(req.getParameter("password"));
		if(u.getPassword().equals(n.getPassword()))
		{
			
		
		
		u.setPassword(req.getParameter("newPassword"));
		if(service.update(u))
		{model.addAttribute("usermsg", "<script>alert('Password Changed SuccessFully')</script>");
		
		
		
		}
		else
		{model.addAttribute("usermsg", "<script>alert('failed to change Password')</script>");}
		HttpSession s=req.getSession(false);
		s.setAttribute("student", u);
		
		
		model.addAttribute("usermsg", "<script>alert('Password changed successfully')</script>");
		return "student-page";
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
		
		}
		else
		{
			 model.addAttribute("error", "<script>alert('Please Login Again')</script>");
			
			   
			
		}
		return "login-page";
	}	
	
	
	
	

}
