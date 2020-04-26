package com.niit.sms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.niit.sms.controller.StudentController;
import com.niit.sms.model.Marks;
import com.niit.sms.model.Student;
import com.niit.sms.model.StudentAddress;
import com.niit.sms.service.AdminService;
import com.niit.sms.service.EmployeeService;
import com.niit.sms.service.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { StudentController.class })
public class StudentControllerTest {

	@InjectMocks
	StudentController controller;
	@MockBean
	AdminService aservice;
	@MockBean
	EmployeeService eservice;
	@MockBean
	LoginService lService;
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
	public void studentDashboardTest() {

		assertEquals(controller.studentDashboard(), "student-page");
	}

	@Test
	public void viewDetailsTest() {

		Student s = new Student();

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(s);

		assertEquals(controller.viewPersonalDetails(req, model), "viewStudentDetails-student");

	}

	@Test
	public void viewDetailsTestFalse() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(null);

		assertEquals(controller.viewPersonalDetails(req, model), "login-page");

	}

	@Test
	public void viewMarksTestUploaded() {

		Student s = new Student();
		s.setId(2l);
		Marks m = new Marks();
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(s);
		when(eservice.getMarks(s.getId().intValue())).thenReturn(m);

		assertEquals(controller.viewMarks(req, model), "viewMarks-student");

		verify(eservice, times(1)).getMarks(s.getId().intValue());

	}

	@Test
	public void viewMarksTestNotUploaded() {

		Student s = new Student();
		s.setId(2l);

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(s);
		when(eservice.getMarks(s.getId().intValue())).thenReturn(null);
		assertEquals(controller.viewMarks(req, model), "viewMarks-student");

		verify(eservice, times(1)).getMarks(s.getId().intValue());

	}

	@Test
	public void viewMarksTestFalse() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(null);

		assertEquals(controller.viewMarks(req, model), "login-page");

	}

	@Test
	public void updateDetails() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(new Student());

		assertEquals(controller.updateStudent(req, model), "updateDetails-student");
	}

	@Test
	public void updateDetailsFalse() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(null);

		assertEquals(controller.updateStudent(req, model), "login-page");
	}

	@Test
	public void updateStudentDetails() {

		Student s = new Student();
		s.setId(2l);
		s.setDateOfBirth(new java.sql.Date(new Date().getTime()));
		s.setDateofJoining(new java.sql.Date(new Date().getTime()));
		s.setStudentClass("Intermediate");
		s.setFirstName("satyam");
		s.setLastName("Rajput");
		s.setAddress(new StudentAddress());
		s.getAddress().setState("Up");
		s.getAddress().setCountry("India");

		Student s1 = new Student();

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(s);
		when(req.getParameter("streetName")).thenReturn("shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(req.getParameter("pincode")).thenReturn("206001");
		when(aservice.update(s1)).thenReturn(true);

		assertEquals(controller.updatePersonalDetails(req, s1, model), "updateDetails-student");

		verify(aservice, times(1)).update(s1);

	}
	
	@Test
	public void updateStudentDetailsExceptionTest() {

		Student s = new Student();
		s.setId(2l);
		s.setDateOfBirth(new java.sql.Date(new Date().getTime()));
		s.setDateofJoining(new java.sql.Date(new Date().getTime()));
		s.setStudentClass("Intermediate");
		s.setFirstName("satyam");
		s.setLastName("Rajput");
		s.setAddress(new StudentAddress());
		s.getAddress().setState("Up");
		s.getAddress().setCountry("India");

		Student s1 = new Student();

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(s);
		when(req.getParameter("streetName")).thenReturn("shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(req.getParameter("pincode")).thenReturn("206001");
		when(aservice.update(s1)).thenReturn(false);

		assertEquals(controller.updatePersonalDetails(req, s1, model), "updateDetails-student");

		verify(aservice, times(1)).update(s1);

	}
	

	@Test
	public void updateStudentDetailsFalse() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(null);

		assertEquals(controller.updatePersonalDetails(req, new Student(), model), "login-page");

	}

	
	
	
	
	
	
	
	
	
	@Test
	public void updatePasswordPage() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(new Student());

		assertEquals(controller.updatePasswordPage(req, model), "changePassword-student");
	}

	@Test
	public void updatePasswordPageFalse() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(null);

		assertEquals(controller.updatePasswordPage(req, model), "login-page");
	}
	
	
	
	@Test
	public void changePasswordTest()
	{
		Student s=new Student();
		s.setPassword("12345");
		
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(s);
		when(req.getParameter("password")).thenReturn("12345");
		when(req.getParameter("newPassword")).thenReturn("12345");
		when(aservice.update(s)).thenReturn(true);
		assertEquals(controller.changePassword(req, model), "changePassword-student");		
		verify(aservice,times(1)).update(s);
		
	}
	@Test
	public void changePasswordExceptionTest()
	{
		Student s=new Student();
		s.setPassword("12345");
		
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(s);
		when(req.getParameter("password")).thenReturn("12345");
		when(req.getParameter("newPassword")).thenReturn("12345");
		when(aservice.update(s)).thenReturn(false);
		assertEquals(controller.changePassword(req, model), "changePassword-student");		
		verify(aservice,times(1)).update(s);
		
	}
	
	
	@Test
	public void changePasswordFalseInvalidStudent() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(null);

		assertEquals(controller.changePassword(req, model), "login-page");
	}
	@Test
	public void changePasswordFalseIncorrectPassword() {

		Student s=new Student();
		s.setPassword("12345");
		
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("student")).thenReturn(s);
		when(req.getParameter("password")).thenReturn("123456");
		
		assertEquals(controller.changePassword(req, model), "login-page");		
		
	}
	@Test
	public void changePasswordFalse2() {

		when(req.getSession(false)).thenReturn(null);
	

		assertEquals(controller.changePassword(req, model), "login-page");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
