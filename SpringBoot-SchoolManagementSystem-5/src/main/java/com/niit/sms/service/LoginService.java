package com.niit.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sms.model.User;
import com.niit.sms.repository.UserRepository;

@Service
@Transactional
public class LoginService {

	@Autowired
private UserRepository repo;
	
	public List<User> listAll()
	{return repo.findAll();}
	
	public User checkUser(User user)
	{
		try {
		User temp=repo.findByEmail(user.getEmail());
		 if((user.getEmail()).equals(temp.getEmail())&& 
				(user.getPassword()).equals(temp.getPassword())  )
		{
			return temp;
		}
		else
		{ 
			return null;
		}}
		catch(Exception e) {return null;}
		
	}
	public User get(long id)
	{
		
		
		
		return repo.findById(id).get();
	}
	
	public boolean save(User u)
	{
		try {
		repo.save(u);
		return true;
		}
		catch(Exception e)
		{
			
			return false;
		}
		
	}
	public User get(String email)
	{
		
		
		
		return repo.findByEmail(email);
	}
	public boolean delete(long id)
	{
		try {
			repo.deleteById(id);
			return true;
			}
			catch(Exception e)
			{
				
				return false;
			}
		
		
	
	}
	

public boolean  isUser(String email)
{
	return repo.existsByEmail(email);
}
	
}
