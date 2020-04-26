package com.niit.sms;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.niit.sms.controller.LoginController;
import com.niit.sms.model.Admin;
import com.niit.sms.model.Employee;
import com.niit.sms.model.Student;
import com.niit.sms.model.User;
import com.niit.sms.service.AdminService;
import com.niit.sms.service.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LoginController.class })
public class LoginControllerTest {

	@InjectMocks
	LoginController loginController;

	@MockBean
	LoginService lservice;
	@MockBean
	AdminService adminService;

	@Spy
	Model model;
	@Spy
	ModelMap modelMap;
	@Spy
	HttpSession ses;
	@Spy
	HttpServletRequest req;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void showLoginFormTest() {
		when(req.getSession(false)).thenReturn(ses);

		assertEquals(loginController.showLoginForm(model), "login-page");
	}

	@Test
	public void userLogoutTest() {
		
		when(req.getSession(false)).thenReturn(ses);

		assertEquals(loginController.userLogout(req, model), "login-page");

	}

	@Test
	public void processLoginAdminTest() {
		User user = new User();
		user.setEmail("satyam@gmail.com");
		user.setPassword("12345");
		User theUser = new User();
	  Admin admin=new Admin();
	  admin.setFirstName("Satyam");
		theUser.setRole("A");
		theUser.setEmail("satyam@gmail.com");
		theUser.setId(1);
		when(lservice.isUser("satyam@gmail.com")).thenReturn(true);

		when(req.getSession()).thenReturn(ses);
		when(lservice.checkUser(user)).thenReturn(theUser);
        when(adminService.getAdmin(1)).thenReturn(admin);
		assertEquals(loginController.processLogin(req, user, modelMap), "redirect:../admin/adminPage");

		verify(lservice, times(1)).isUser("satyam@gmail.com");
		verify(lservice, times(1)).checkUser(user);

	}

	@Test
	public void processLoginEmployeeTest() {

		Employee e = new Employee();
		User user = new User();
		user.setEmail("satyam1@gmail.com");
		user.setPassword("12345");
		User theUser = new User();
		theUser.setRole("T");
		theUser.setEmail("satyam1@gmail.com");
		theUser.setUserId(2l);
		when(lservice.isUser("satyam1@gmail.com")).thenReturn(true);

		when(req.getSession()).thenReturn(ses);
		when(lservice.checkUser(user)).thenReturn(theUser);
		when(adminService.getEmployee(2l)).thenReturn(e);

		assertEquals(loginController.processLogin(req, user, modelMap), "redirect:../employee/teacherPage");

		verify(lservice, times(1)).isUser("satyam1@gmail.com");
		verify(lservice, times(1)).checkUser(user);
		verify(adminService, times(1)).getEmployee(2l);

	}

	@Test
	public void processLoginStudentTest() {

		Student s = new Student();
		User user = new User();
		user.setEmail("satyam1@gmail.com");
		user.setPassword("12345");
		User theUser = new User();
		theUser.setRole("S");
		theUser.setEmail("satyam1@gmail.com");
		theUser.setUserId(2l);
		when(lservice.isUser("satyam1@gmail.com")).thenReturn(true);

		when(req.getSession()).thenReturn(ses);
		when(lservice.checkUser(user)).thenReturn(theUser);
		when(adminService.getStudent(2l)).thenReturn(s);

		assertEquals(loginController.processLogin(req, user, modelMap), "redirect:../student/studentPage");

		verify(lservice, times(1)).isUser("satyam1@gmail.com");
		verify(lservice, times(1)).checkUser(user);
		verify(adminService, times(1)).getStudent(2l);

	}
	
	@Test
	public void processLoginTest1()
	{
	
		User user = new User();
		user.setEmail("satyam1@gmail.com");
		
		when(lservice.isUser("satyam1@gmail.com")).thenReturn(false);

		

		assertEquals(loginController.processLogin(req, user, modelMap), "login-page");

		verify(lservice, times(1)).isUser("satyam1@gmail.com");
		
		
	}
	
	
	
	@Test
	public void processLoginTest2()
	{
	
		User user = new User();
		user.setEmail("satyam1@gmail.com");
		
		when(lservice.isUser("satyam1@gmail.com")).thenReturn(true);

		when(lservice.checkUser(user)).thenReturn(null);
	

		assertEquals(loginController.processLogin(req, user, modelMap), "login-page");

		verify(lservice, times(1)).isUser("satyam1@gmail.com");
		verify(lservice, times(1)).checkUser(user);
		
		
		
	}

}
