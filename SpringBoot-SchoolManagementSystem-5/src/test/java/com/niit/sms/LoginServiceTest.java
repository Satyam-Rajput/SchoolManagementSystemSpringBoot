package com.niit.sms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.niit.sms.model.User;
import com.niit.sms.repository.UserRepository;
import com.niit.sms.service.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoginService.class})
public class LoginServiceTest {

	@InjectMocks
	LoginService service;

	@MockBean
	UserRepository repo;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void listAllTest()
	{
		List< User> u=new ArrayList<User>();
		
		when(repo.findAll()).thenReturn(u);
		
		assertEquals(service.listAll(), u);
	}
	

@Test	
public void checkUserTest()
{
	User u=new User();
	u.setEmail("satyam@gmail.com");
	u.setPassword("12345");
	User u1=new User();
	u1.setEmail("satyam@gmail.com");
	u1.setPassword("12345");
	when(repo.findByEmail("satyam@gmail.com")).thenReturn(u1);
	
assertEquals(service.checkUser(u), u1);
}
@Test	
public void checkUserFalseTest()
{
	User u=new User();
	u.setEmail("satyam@gmail.com");
	u.setPassword("12345");
	User u1=new User();
	u1.setEmail("satyam@gmail.com");
	u1.setPassword("123456");
	when(repo.findByEmail("satyam@gmail.com")).thenReturn(u1);
	
assertEquals(service.checkUser(u), null);
}
@Test	
public void checkUserNullTest()
{
	User u=new User();
	u.setEmail("satyam@gmail.com");
	u.setPassword("12345");
	User u1=new User();
	u1.setEmail("satyam@gmail.com");
	u1.setPassword("123456");
	when(repo.findByEmail("satyam@gmail.com")).thenReturn(null);
	
assertEquals(service.checkUser(u), null);
}

/*
@Test
public void getTest()
{
	User u=new User();

when(repo.findById(2l).get()).thenReturn(u);

assertEquals(service.get(2l), u);
}*/

@Test
public void getTest()
{
	User u=new User();

when(repo.findByEmail("satyam@gmail.com")).thenReturn(u);

assertEquals(service.get("satyam@gmail.com"), u);
}
@Test
public void saveTest()
{
User u=new User();
service.save(u);
verify(repo,times(1)).save(u);
}

@Test
public void isUserTest()
{
	when(repo.existsByEmail("satyam@gmail.com")).thenReturn(false);
assertEquals(service.isUser("satyam@gmail.com"),false);	
}

@Test
public void deleteTest()
{
assertEquals(service.delete(2l), true);
verify(repo,times(1)).deleteById(2l);
	
	}
}
