package com.niit.sms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.niit.sms.controller.EmployeeController;
import com.niit.sms.model.Employee;
import com.niit.sms.model.Marks;
import com.niit.sms.model.Student;
import com.niit.sms.service.AdminService;
import com.niit.sms.service.EmployeeService;
import com.niit.sms.service.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EmployeeController.class })
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController controller;

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
	public void teacherDashboardTest() {

		assertEquals(controller.teacherDashboard(), "teacher-page");
	}

	@Test
	public void displayStudentsTest() {
		List<Student> list = new ArrayList<>();
		list.add(new Student());
		Employee e = new Employee();
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(e);
		when(aservice.listAllStudents()).thenReturn(list);
		assertEquals(controller.displayStudents(req, modelMap), "listStudents-teacher");

		verify(aservice, times(1)).listAllStudents();

	}

	@Test
	public void displayStudentsTestNoStudents() {
		List<Student> list = new ArrayList<>();

		Employee e = new Employee();
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(e);
		when(aservice.listAllStudents()).thenReturn(list);
		assertEquals(controller.displayStudents(req, modelMap), "listStudents-teacher");

		verify(aservice, times(1)).listAllStudents();

	}

	@Test
	public void displayStudentsTestFalse() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.displayStudents(req, modelMap), "login-page");

	}

	@Test
	public void filterStudentAll() {

		Employee e = new Employee();
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(e);
		when(req.getParameter("studentClass")).thenReturn("All");
		assertEquals(controller.filterStudents(req, modelMap), "redirect:../employee/getStudents");

	}

	@Test
	public void filterStudentSpecific() {
		List<Student> list = new ArrayList<>();
		list.add(new Student());
		Employee e = new Employee();
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(e);
		when(req.getParameter("studentClass")).thenReturn("Intermediate");
		when(aservice.filterStudentByClass("Intermediate")).thenReturn(list);
		assertEquals(controller.filterStudents(req, modelMap), "listStudents-teacher");

		verify(aservice, times(1)).filterStudentByClass("Intermediate");

	}

	@Test
	public void filterStudentSpecificNoData() {
		List<Student> list = new ArrayList<>();

		Employee e = new Employee();
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(e);
		when(req.getParameter("studentClass")).thenReturn("Intermediate");
		when(aservice.filterStudentByClass("Intermediate")).thenReturn(list);
		assertEquals(controller.filterStudents(req, modelMap), "listStudents-teacher");

		verify(aservice, times(1)).filterStudentByClass("Intermediate");

	}

	@Test
	public void filterStudentTestFalse() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.filterStudents(req, modelMap), "login-page");

	}

	@Test
	public void findStudent() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());

		assertEquals(controller.findStudent(req, model), "getStudent-teacher");

	}

	@Test
	public void findStudentFalse() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.findStudent(req, model), "login-page");

	}

	@Test
	public void getStudentTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());
		when(req.getParameter("studentId")).thenReturn("2");
		when(aservice.isStudentExists(2)).thenReturn(true);
		when(aservice.getStudent(2)).thenReturn(new Student());
		when(eservice.getMarks(2)).thenReturn(new Marks());
		assertEquals(controller.searchStudent(req, model), "getStudent-teacher");

		verify(aservice, times(1)).isStudentExists(2);
		verify(aservice, times(1)).getStudent(2);
		verify(eservice, times(1)).getMarks(2);

	}

	@Test
	public void getStudentIdNotExistsTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());
		when(req.getParameter("studentId")).thenReturn("2");
		when(aservice.isStudentExists(2)).thenReturn(false);

		assertEquals(controller.searchStudent(req, model), "getStudent-teacher");
		verify(aservice, times(1)).isStudentExists(2);
	}

	@Test
	public void getStudentFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.searchStudent(req, model), "login-page");

	}

	
	
	
	
	@Test
	public void viewStudentTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());
		
		when(aservice.isStudentExists(2)).thenReturn(true);
		when(aservice.getStudent(2)).thenReturn(new Student());
		when(eservice.getMarks(2)).thenReturn(new Marks());
		assertEquals(controller.viewStudentDetails(req,2, model), "getStudent-teacher");

		verify(aservice, times(1)).isStudentExists(2);
		verify(aservice, times(1)).getStudent(2);
		verify(eservice, times(1)).getMarks(2);

	}

	@Test
	public void viewStudentIdNotExistsTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());
		
		when(aservice.isStudentExists(2)).thenReturn(false);

		assertEquals(controller.viewStudentDetails(req,2, model), "getStudent-teacher");
		verify(aservice, times(1)).isStudentExists(2);
	}

	@Test
	public void viewStudentFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.viewStudentDetails(req, 2,model), "login-page");

	}

	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void viewStudentMarksTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());

		assertEquals(controller.viewStudentMarks(req, model), "studentMarks-teacher");

	}

	@Test
	public void viewStudentMarksFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.viewStudentMarks(req, model), "login-page");

	}

	@Test
	public void getStudentMarksFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.getStudentMarks(req, model), "login-page");

	}

	@Test
	public void getStudentMarksTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());
		when(req.getParameter("studentId")).thenReturn("2");
		when(aservice.isStudentExists(2)).thenReturn(true);
		when(aservice.getStudent(2)).thenReturn(new Student());
		when(eservice.getMarks(2)).thenReturn(new Marks());

		assertEquals(controller.getStudentMarks(req, model), "studentMarks-teacher");

		verify(aservice, times(1)).isStudentExists(2);
		verify(aservice, times(1)).getStudent(2);
		verify(eservice, times(1)).getMarks(2);

	}

	@Test
	public void getStudentMarksNotUploadedTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());
		when(req.getParameter("studentId")).thenReturn("2");
		when(aservice.isStudentExists(2)).thenReturn(true);
		when(aservice.getStudent(2)).thenReturn(new Student());
		when(eservice.getMarks(2)).thenReturn(null);

		assertEquals(controller.getStudentMarks(req, model), "studentMarks-teacher");

		verify(aservice, times(1)).isStudentExists(2);
		verify(aservice, times(1)).getStudent(2);
		verify(eservice, times(1)).getMarks(2);

	}

	@Test
	public void getStudentMarksIdNotFoundTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());
		when(req.getParameter("studentId")).thenReturn("2");
		when(aservice.isStudentExists(2)).thenReturn(false);

		assertEquals(controller.getStudentMarks(req, model), "studentMarks-teacher");

		verify(aservice, times(1)).isStudentExists(2);

	}

	@Test
	public void updateTeacherTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());

		assertEquals(controller.updateTeacher(req, model), "updateTeacherInformation-teacher");

	}
	@Test
	public void updateTeacherFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.updateTeacher(req, model), "login-page");

	}

	@Test
	public void updateTeacherDetailsTest() {
		Employee e = new Employee();
		e.setDateofJoining(new java.sql.Date(new Date().getTime()));
		Employee e1 = new Employee();
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());

		when(req.getParameter("streetName")).thenReturn("Shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("state")).thenReturn("UP");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("pincode")).thenReturn("206001");
       when(aservice.update(e1)).thenReturn(true);
		assertEquals(controller.updatePersonalDetails(req, e1, model), "updateTeacherInformation-teacher");
		verify(aservice, times(1)).update(e1);
		;

	}
	@Test
	public void updateTeacherDetailsExceptionTest() {
		Employee e = new Employee();
		e.setDateofJoining(new java.sql.Date(new Date().getTime()));
		Employee e1 = new Employee();
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());

		when(req.getParameter("streetName")).thenReturn("Shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("state")).thenReturn("UP");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("pincode")).thenReturn("206001");
       when(aservice.update(e1)).thenReturn(false);
		assertEquals(controller.updatePersonalDetails(req, e1, model), "updateTeacherInformation-teacher");
		verify(aservice, times(1)).update(e1);
		;

	}

	@Test
	public void updateTeacherDetailsFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.updatePersonalDetails(req, new Employee(), model), "login-page");

	}

	@Test
	public void viewDetailsTest() {
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());

		assertEquals(controller.viewPersonalDetails(req, model), "viewTeacherDetails-teacher");

	}

	@Test
	public void viewDetailsFalseTest() {
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.viewPersonalDetails(req, model), "login-page");

	}

	@Test
	public void uploadMarksPageTest()
	{
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());

		assertEquals(controller.uploadMarksPage(req, model), "uploadMarks-teacher");

		
	}
	@Test
	public void uploadMarksPageFalseTest()
	{
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.uploadMarksPage(req, model), "login-page");

		
	}
	
	
	
	
	
	
	
	
	@Test
	public void uploadMarksTest()
	{
		Marks m=new Marks();
		m.setStudentId(2);
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());
		when(aservice.isStudentExists(2)).thenReturn(true);
		
		when(eservice.getMarks(2)).thenReturn(new Marks());
		
		when(eservice.uploadMarks(m)).thenReturn(true);
		
		assertEquals(controller.uploadMarks(req, model, m), "uploadMarks-teacher");

		verify(eservice, times(1)).getMarks(2);
		verify(eservice, times(1)).delete(2);
		verify(eservice, times(1)).uploadMarks(m);
	}
	
	
	@Test
	public void uploadMarksExceptionTest()
	{
		Marks m=new Marks();
		m.setStudentId(2);
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());
		when(aservice.isStudentExists(2)).thenReturn(true);
		
		when(eservice.getMarks(2)).thenReturn(new Marks());
		
		when(eservice.uploadMarks(m)).thenReturn(false);
		
		assertEquals(controller.uploadMarks(req, model, m), "uploadMarks-teacher");

		verify(eservice, times(1)).getMarks(2);
		verify(eservice, times(1)).delete(2);
		verify(eservice, times(1)).uploadMarks(m);
	}
	@Test
	public void uploadMarksStudentNotAvailableTest()
	{
		Marks m=new Marks();
		m.setStudentId(2);
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());
		
		
		
		when(aservice.isStudentExists(2)).thenReturn(false);
		
		
		assertEquals(controller.uploadMarks(req, model, m), "uploadMarks-teacher");

		
	}
	
	@Test
	public void uploadMarksFalseTest()
	{
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.uploadMarks(req, model, new Marks()), "login-page");

		
	}
	
	
	@Test
	public void updatePasswordPageTest()
	{
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(new Employee());

		assertEquals(controller.updatePasswordPage(req, model), "changePassword-teacher");

		
	}
	
	
	@Test
	public void updatePasswordPageFalseTest()
	{
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);

		assertEquals(controller.updatePasswordPage(req, model), "login-page");

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void updatePasswordTest()
	{
		Employee e=new Employee();
		e.setPassword("12345");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(e);
		when(req.getParameter("password")).thenReturn("12345");
		when(req.getParameter("newPassword")).thenReturn("12345");
		when(aservice.update(e)).thenReturn(true);
		assertEquals(controller.changePassword(req, model), "changePassword-teacher");		
		verify(aservice,times(1)).update(e);
		
	}
	@Test
	public void updatePasswordExceptionTest()
	{
		Employee e=new Employee();
		e.setPassword("12345");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(e);
		when(req.getParameter("password")).thenReturn("12345");
		when(req.getParameter("newPassword")).thenReturn("12345");
		when(aservice.update(e)).thenReturn(false);
		assertEquals(controller.changePassword(req, model), "changePassword-teacher");		
		verify(aservice,times(1)).update(e);
		
	}
	@Test
	public void updatePasswordPasswordMismatchedTest()
	{
		Employee e=new Employee();
		e.setPassword("12345");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(e);
		when(req.getParameter("password")).thenReturn("123456");
		assertEquals(controller.changePassword(req, model), "login-page");		
		
		
	}
	@Test
	public void updatePasswordNullObjectTest()
	{
		Employee e=new Employee();
		e.setPassword("12345");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("employee")).thenReturn(null);
		
		assertEquals(controller.changePassword(req, model), "login-page");		
		
		
	}
	@Test
	public void updatePasswordNullSesssionTest()
	{
	
		when(req.getSession(false)).thenReturn(null);
		
		
		assertEquals(controller.changePassword(req, model), "login-page");		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
