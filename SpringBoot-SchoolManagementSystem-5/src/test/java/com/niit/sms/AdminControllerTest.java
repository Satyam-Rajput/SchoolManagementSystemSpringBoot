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

import com.niit.sms.controller.AdminController;
import com.niit.sms.model.Employee;
import com.niit.sms.model.Marks;
import com.niit.sms.model.Student;
import com.niit.sms.model.StudentAddress;
import com.niit.sms.model.User;
import com.niit.sms.service.AdminService;
import com.niit.sms.service.EmployeeService;
import com.niit.sms.service.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AdminController.class })
public class AdminControllerTest {

	@InjectMocks
	AdminController controller;

	@MockBean
	AdminService aservice;

	@MockBean
	LoginService lservice;
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
	public void homeTest() {
		assertEquals(controller.home(), "index");
	}

	@Test
	public void getAdminPage() {
		when(req.getSession(false)).thenReturn(ses);

		assertEquals(controller.getAdminPage(req, modelMap), "admin-page");

		verify(aservice, times(1)).countEmployee();
		verify(aservice, times(1)).countStudent();
	}

	@Test
	public void changePasswordPageTest() {
		assertEquals(controller.changePasswordPage(), "changePassword-admin");
	}

	@Test
	public void changePasswordTest() {
		User e = new User();
		e.setPassword("12345");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(e);
		when(req.getParameter("password")).thenReturn("12345");
		when(req.getParameter("newPassword")).thenReturn("12345");
		when(lservice.save(e)).thenReturn(true);
		assertEquals(controller.changePassword(req, model), "admin-page");
		verify(lservice, times(1)).save(e);

	}

	@Test
	public void changePasswordExceptionTest() {
		User e = new User();
		e.setPassword("12345");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(e);
		when(req.getParameter("password")).thenReturn("12345");
		when(req.getParameter("newPassword")).thenReturn("12345");
		when(lservice.save(e)).thenReturn(false);
		assertEquals(controller.changePassword(req, model), "admin-page");
		verify(lservice, times(1)).save(e);

	}

	@Test
	public void changePasswordMismatchedTest() {
		User e = new User();
		e.setPassword("12345");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(e);
		when(req.getParameter("password")).thenReturn("123456");
		assertEquals(controller.changePassword(req, model), "login-page");

	}

	@Test
	public void changePasswordNullObjectTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.changePassword(req, model), "login-page");

	}

	@Test
	public void addTeacherTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		assertEquals(controller.addTeacher(req, model), "addTeacher-admin");

	}

	@Test
	public void addTeacherFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.addTeacher(req, model), "login-page");

	}

	@Test
	public void saveTeacherTest() {

		Employee e1 = new Employee();
		e1.setEmail("satyam@gmail.com");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		when(req.getParameter("streetName")).thenReturn("Shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("state")).thenReturn("UP");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("pincode")).thenReturn("206001");
		when(lservice.isUser("satyam@gmail.com")).thenReturn(false);
		when(aservice.save(e1)).thenReturn(true);
		assertEquals(controller.saveTeacher(req, e1, model), "admin-page");
		verify(aservice, times(1)).save(e1);
		verify(aservice, times(1)).countStudent();
		verify(aservice, times(1)).countEmployee();

	}
	@Test
	public void saveTeacherExceptionTest() {

		Employee e1 = new Employee();
		e1.setEmail("satyam@gmail.com");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		when(req.getParameter("streetName")).thenReturn("Shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("state")).thenReturn("UP");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("pincode")).thenReturn("206001");
		when(lservice.isUser("satyam@gmail.com")).thenReturn(false);
		when(aservice.save(e1)).thenReturn(false);
		assertEquals(controller.saveTeacher(req, e1, model), "admin-page");
		verify(aservice, times(1)).save(e1);
		verify(aservice,times(1)).countStudent();
		verify(aservice, times(1)).countEmployee();

	}

	@Test
	public void saveTeacherEmailAlreadyExistsTest() {

		Employee e1 = new Employee();
		e1.setEmail("satyam@gmail.com");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		when(lservice.isUser("satyam@gmail.com")).thenReturn(true);
		assertEquals(controller.saveTeacher(req, e1, model), "admin-page");
	}

	@Test
	public void saveTeacherInvalidSessionTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.saveTeacher(req, new Employee(), model), "login-page");

	}

	@Test
	public void displayEmployeesTest() {
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee());
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.listAllEmployees()).thenReturn(list);
		assertEquals(controller.displayEmployees(req, modelMap), "displayTeacherDetails-admin");

	}

	@Test
	public void displayEmployeesSizeZeroTest() {
		List<Employee> list = new ArrayList<Employee>();

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.listAllEmployees()).thenReturn(list);
		assertEquals(controller.displayEmployees(req, modelMap), "displayTeacherDetails-admin");

	}

	@Test
	public void displayEmployeesFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.displayEmployees(req, modelMap), "login-page");

	}

	@Test
	public void addStudentTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		assertEquals(controller.addStudent(req, model), "addStudent-admin");

	}

	@Test
	public void addStudentFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.addStudent(req, model), "login-page");

	}

	@Test
	public void saveStudentTest() {

		Student e1 = new Student();
		e1.setEmail("satyam@gmail.com");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		when(req.getParameter("streetName")).thenReturn("Shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("state")).thenReturn("UP");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("pincode")).thenReturn("206001");
		when(lservice.isUser("satyam@gmail.com")).thenReturn(false);
		when(aservice.save(e1)).thenReturn(true);
		assertEquals(controller.saveStudent(req, e1, model), "admin-page");
		verify(aservice, times(1)).save(e1);
		verify(aservice, times(1)).countStudent();
		verify(aservice, times(1)).countEmployee();

	}
	@Test
	public void saveStudentExceptionTest() {

		Student e1 = new Student();
		e1.setEmail("satyam@gmail.com");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		when(req.getParameter("streetName")).thenReturn("Shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("state")).thenReturn("UP");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("pincode")).thenReturn("206001");
		when(lservice.isUser("satyam@gmail.com")).thenReturn(false);
		when(aservice.save(e1)).thenReturn(false);
		assertEquals(controller.saveStudent(req, e1, model), "admin-page");
		verify(aservice, times(1)).save(e1);
		verify(aservice, times(1)).countStudent();
		verify(aservice, times(1)).countEmployee();

	}

	@Test
	public void saveStudentEmailAlreadyExistsTest() {

		Student e1 = new Student();
		e1.setEmail("satyam@gmail.com");
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		when(lservice.isUser("satyam@gmail.com")).thenReturn(true);
		assertEquals(controller.saveStudent(req, e1, model), "admin-page");
	}

	@Test
	public void saveStudentInvalidSessionTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.saveStudent(req, new Student(), model), "login-page");

	}

	@Test
	public void displayStudentsTest() {
		List<Student> list = new ArrayList<>();
		list.add(new Student());
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.listAllStudents()).thenReturn(list);
		assertEquals(controller.displayStudents(req, modelMap), "displayStudentDetails-admin");

	}

	@Test
	public void displayStudentsSizeZeroTest() {
		List<Student> list = new ArrayList<>();

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.listAllStudents()).thenReturn(list);
		assertEquals(controller.displayStudents(req, modelMap), "displayStudentDetails-admin");

	}

	@Test
	public void displayStudentsFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.displayStudents(req, modelMap), "login-page");

	}

	@Test
	public void findStudentTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		assertEquals(controller.findStudent(req, model), "find-student-admin");

	}

	@Test
	public void findStudentFalse() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.findStudent(req, model), "login-page");

	}

	@Test
	public void findTeacherTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		assertEquals(controller.findTeacher(req, model), "find-teacher-admin");

	}

	@Test
	public void findTeacherFalse() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.findTeacher(req, model), "login-page");

	}

	@Test
	public void getStudentTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(req.getParameter("studentId")).thenReturn("2");
		when(aservice.isStudentExists(2)).thenReturn(true);
		when(aservice.getStudent(2)).thenReturn(new Student());

		assertEquals(controller.getStudent(req, model), "find-student-admin");

		verify(aservice, times(1)).isStudentExists(2);
		verify(aservice, times(1)).getStudent(2);

	}

	@Test
	public void getStudentIdNotExistsTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(req.getParameter("studentId")).thenReturn("2");
		when(aservice.isStudentExists(2)).thenReturn(false);

		assertEquals(controller.getStudent(req, model), "find-student-admin");
		verify(aservice, times(1)).isStudentExists(2);
	}

	@Test
	public void getStudentFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.getStudent(req, model), "login-page");

	}

	@Test
	public void getTeacherTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(req.getParameter("teacherId")).thenReturn("2");
		when(aservice.isEmployeeExists(2)).thenReturn(true);
		when(aservice.getEmployee(2)).thenReturn(new Employee());

		assertEquals(controller.getTeacher(req, model), "find-teacher-admin");

		verify(aservice, times(1)).isEmployeeExists(2);
		verify(aservice, times(1)).getEmployee(2);

	}

	@Test
	public void getTeacherIdNotExistsTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(req.getParameter("teacherId")).thenReturn("2");
		when(aservice.isEmployeeExists(2)).thenReturn(false);

		assertEquals(controller.getTeacher(req, model), "find-teacher-admin");

		verify(aservice, times(1)).isEmployeeExists(2);

	}

	@Test
	public void getTeacherFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.getTeacher(req, model), "login-page");

	}

	@Test
	public void filterStudentAll() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(req.getParameter("studentClass")).thenReturn("All");
		assertEquals(controller.filterStudent(req, modelMap), "redirect:../admin/getStudents");

	}

	@Test
	public void filterStudentSpecific() {
		List<Student> list = new ArrayList<>();
		list.add(new Student());

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(req.getParameter("studentClass")).thenReturn("Intermediate");
		when(aservice.filterStudentByClass("Intermediate")).thenReturn(list);
		assertEquals(controller.filterStudent(req, modelMap), "displayStudentDetails-admin");

		verify(aservice, times(1)).filterStudentByClass("Intermediate");

	}

	@Test
	public void filterStudentSpecificNoData() {
		List<Student> list = new ArrayList<>();

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(req.getParameter("studentClass")).thenReturn("Intermediate");
		when(aservice.filterStudentByClass("Intermediate")).thenReturn(list);
		assertEquals(controller.filterStudent(req, modelMap), "displayStudentDetails-admin");

		verify(aservice, times(1)).filterStudentByClass("Intermediate");

	}

	@Test
	public void filterStudentTestFalse() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.filterStudent(req, modelMap), "login-page");

	}

	@Test
	public void updateTeacherPageTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		when(aservice.isEmployeeExists(2)).thenReturn(true);

		when(aservice.getEmployee(2)).thenReturn(new Employee());

		assertEquals(controller.updateTeacherPage(req, 2, model), "update-teacherDetails-admin");
		verify(aservice, times(1)).isEmployeeExists(2);
		verify(aservice,times(1)).getEmployee(2);
	}

	@Test
	public void updateTeacherPageUserNotExistsTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		when(aservice.isEmployeeExists(2)).thenReturn(false);

		assertEquals(controller.updateTeacherPage(req, 2, model), "update-teacherDetails-admin");
		verify(aservice, times(1)).isEmployeeExists(2);

	}

	@Test
	public void updateTeacherPageFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.updateTeacherPage(req, 2, model), "login-page");

	}

	@Test
	public void updateTeacherDetailsTest() {
		Employee e = new Employee();
		e.setDateofJoining(new java.sql.Date(new Date().getTime()));
		Employee e1 = new Employee();
		e1.setId(2l);
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.getEmployee(2)).thenReturn(e);
		when(req.getParameter("streetName")).thenReturn("Shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("state")).thenReturn("UP");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("pincode")).thenReturn("206001");
		when(aservice.update(e1)).thenReturn(true);

		assertEquals(controller.updateTeacher(req, e1, model), "admin-page");
		verify(aservice, times(1)).update(e1);
		;

	}
	
	
	
	
	
	@Test
	public void updateTeacherDetailsExceptionTest() {
		Employee e = new Employee();
		e.setDateofJoining(new java.sql.Date(new Date().getTime()));
		Employee e1 = new Employee();
		e1.setId(2l);
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.getEmployee(2)).thenReturn(e);
		when(req.getParameter("streetName")).thenReturn("Shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("state")).thenReturn("UP");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(req.getParameter("country")).thenReturn("India");
		when(req.getParameter("pincode")).thenReturn("206001");
		when(aservice.update(e1)).thenReturn(false);

		assertEquals(controller.updateTeacher(req, e1, model), "admin-page");
		verify(aservice, times(1)).update(e1);
		;

	}

	@Test
	public void updateTeacherDetailsFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.updateTeacher(req, new Employee(), model), "login-page");

	}

	@Test
	public void deleteTeacherTest() {
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.isEmployeeExists(2)).thenReturn(true);
       when(aservice.deleteEmployee(2)).thenReturn(true);
		assertEquals(controller.deleteTeacher(req, 2, model), "admin-page");

		verify(aservice, times(1)).isEmployeeExists(2);
		verify(aservice, times(1)).deleteEmployee(2);
		verify(aservice, times(1)).countStudent();
		verify(aservice, times(1)).countEmployee();
	}
	
	
	
	@Test
	public void deleteTeacherExceptionTest() {
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.isEmployeeExists(2)).thenReturn(true);
       when(aservice.deleteEmployee(2)).thenReturn(false);
		assertEquals(controller.deleteTeacher(req, 2, model), "admin-page");

		verify(aservice, times(1)).isEmployeeExists(2);
		verify(aservice, times(1)).deleteEmployee(2);
		verify(aservice, times(1)).countStudent();
		verify(aservice, times(1)).countEmployee();
	}
	

	@Test
	public void deleteTeacherNotExistsTest() {
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.isEmployeeExists(2)).thenReturn(false);

		assertEquals(controller.deleteTeacher(req, 2, model), "admin-page");

		verify(aservice, times(1)).isEmployeeExists(2);

	}

	@Test
	public void deleteTeacherInvalidSessionTest() {
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.deleteTeacher(req, 2, model), "login-page");

	}

	@Test
	public void updateStudentPageTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		when(aservice.isStudentExists(2)).thenReturn(true);

		when(aservice.getStudent(2)).thenReturn(new Student());

		assertEquals(controller.updateStudentPage(req, 2, model), "update-studentDetails-admin");
		verify(aservice,times(1)).isStudentExists(2);
		verify(aservice, times(1)).getStudent(2);
	}

	@Test
	public void updateStudentPageUserNotExistsTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());

		when(aservice.isStudentExists(2)).thenReturn(false);

		assertEquals(controller.updateStudentPage(req, 2, model), "update-studentDetails-admin");
		verify(aservice, times(1)).isStudentExists(2);

	}

	@Test
	public void updateStudentPageFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.updateStudentPage(req, 2, model), "login-page");

	}

	@Test
	public void updateStudentDetailsTest() {
		Student e = new Student();
		e.setDateofJoining(new java.sql.Date(new Date().getTime()));
		e.setAddress(new StudentAddress());
		e.getAddress().setCountry("India");
		Student e1 = new Student();
		e1.setId(2l);
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.getStudent(2)).thenReturn(e);
		when(req.getParameter("streetName")).thenReturn("Shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("state")).thenReturn("UP");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(aservice.update(e1)).thenReturn(true);

		when(req.getParameter("pincode")).thenReturn("206001");

		assertEquals(controller.updateStudent(req, e1, model), "admin-page");
		verify(aservice, times(1)).update(e1);
		;

	}
	
	
	

	@Test
	public void updateStudentDetailsExceptionTest() {
		Student e = new Student();
		e.setDateofJoining(new java.sql.Date(new Date().getTime()));
		e.setAddress(new StudentAddress());
		e.getAddress().setCountry("India");
		Student e1 = new Student();
		e1.setId(2l);
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.getStudent(2)).thenReturn(e);
		when(req.getParameter("streetName")).thenReturn("Shiv Nagar");
		when(req.getParameter("city")).thenReturn("Etawah");
		when(req.getParameter("state")).thenReturn("UP");
		when(req.getParameter("houseNumber")).thenReturn("448");
		when(aservice.update(e1)).thenReturn(false);

		when(req.getParameter("pincode")).thenReturn("206001");

		assertEquals(controller.updateStudent(req, e1, model), "admin-page");
		verify(aservice, times(1)).update(e1);
		;

	}
	

	@Test
	public void updateStudentDetailsFalseTest() {

		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.updateStudent(req, new Student(), model), "login-page");

	}

	@Test
	public void deleteStudentTest() {
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.isStudentExists(2)).thenReturn(true);
    when(aservice.deleteStudent(2)).thenReturn(true);
		assertEquals(controller.deleteStudent(req, 2, model), "admin-page");

		verify(aservice, times(1)).isStudentExists(2);
		verify(aservice, times(1)).deleteStudent(2);
		verify(aservice,times(1)).countStudent();
		verify(aservice, times(1)).countEmployee();
	}
	@Test
	public void deleteStudentExceptionTest() {
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.isStudentExists(2)).thenReturn(true);
    when(aservice.deleteStudent(2)).thenReturn(false);
		assertEquals(controller.deleteStudent(req, 2, model), "admin-page");

		verify(aservice, times(1)).isStudentExists(2);
		verify(aservice, times(1)).deleteStudent(2);
		verify(aservice, times(1)).countStudent();
		verify(aservice, times(1)).countEmployee();
	}

	@Test
	public void deleteStudentNotExistsTest() {
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(new User());
		when(aservice.isStudentExists(2)).thenReturn(false);

		assertEquals(controller.deleteStudent(req, 2, model), "admin-page");

		verify(aservice, times(1)).isStudentExists(2);

	}

	@Test
	public void deleteStudentInvalidSessionTest() {
		when(req.getSession(false)).thenReturn(ses);
		when(req.getSession(false).getAttribute("admin")).thenReturn(null);

		assertEquals(controller.deleteStudent(req, 2, model), "login-page");

	}

}
