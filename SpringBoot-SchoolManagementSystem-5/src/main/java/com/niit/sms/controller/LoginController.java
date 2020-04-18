package com.niit.sms.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.niit.sms.model.Employee;
import com.niit.sms.model.Student;
import com.niit.sms.model.User;
import com.niit.sms.service.AdminService;
import com.niit.sms.service.LoginService;

@Controller

@RequestMapping("/user")
public class LoginController {

	@Autowired
	private LoginService userService;
	@Autowired
	private AdminService adminService;

	
	@GetMapping("/loginForm")
	public String showLoginForm(Model theModel) {
		User theUser = new User();
		theModel.addAttribute("user", theUser);
		return "login-page";
	}

	@PostMapping("/login")
	public String processLogin(HttpServletRequest req, @ModelAttribute User theUser,ModelMap model) {
 
		
		
		if(userService.isUser(theUser.getEmail()))
		{
		User usr = userService.checkUser(theUser);
		
		if (usr == null) {
			model.addAttribute("error", "Incorrect Password");
			return "login-page";
			
		} else {
			if (usr.getRole().equalsIgnoreCase("A")) {
				HttpSession session=req.getSession();
				session.setMaxInactiveInterval(300);
				session.setAttribute("admin", usr);
				session.setAttribute("user", usr.getEmail().split("@")[0]);
				return "redirect:../admin/adminPage";
	

			} else if (usr.getRole().equalsIgnoreCase("T")) {
				
				Employee theEmployee=adminService.getEmployee(usr.getUserId());
				HttpSession session=req.getSession();
				session.setMaxInactiveInterval(300);
				session.setAttribute("employee", theEmployee);
				session.setAttribute("user", usr.getEmail().split("@")[0]);
				return "redirect:../employee/teacherPage";
				

			} else if (usr.getRole().equalsIgnoreCase("S")) {
				Student theStudent=adminService.getStudent(usr.getUserId());
				
				HttpSession session=req.getSession();
				session.setMaxInactiveInterval(300);
				session.setAttribute("user", usr.getEmail().split("@")[0]);
				session.setAttribute("student", theStudent);
				
				return "redirect:../student/studentPage";

			}

		}
		}
		else
		{
			
			model.addAttribute("error", "User Not Found");
			
			
		}
		return "login-page";
	}
	
	
	@GetMapping("/logout")
	public String userLogout(HttpServletRequest req,Model theModel) {
		HttpSession session=req.getSession(false);
	  if(session!=null)
	  {session.invalidate();}
		
		User theUser = new User();
		theModel.addAttribute("user", theUser);
		return "login-page";
	}

}
