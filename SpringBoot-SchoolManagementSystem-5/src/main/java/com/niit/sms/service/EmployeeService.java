package com.niit.sms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.sms.model.Marks;
import com.niit.sms.model.User;
import com.niit.sms.repository.EmployeeRepository;
import com.niit.sms.repository.MarksRepository;

@Service
@Transactional
public class EmployeeService {
   @Autowired
	private MarksRepository markRepo;
   @Autowired
   private EmployeeRepository repo;
	
   public Marks getMarks(int studentId)
   {
	   return markRepo.findByStudentId(studentId);	   
   }
   
   public boolean uploadMarks(Marks marks)
   {
		try {
	   markRepo.save(marks);
	   return true;
		}catch(Exception e)
		{
		return false;	
			
		}}
   public boolean delete(int studentId)
   {
	   try {
		   markRepo.deleteByStudentId(studentId);
		   return true;
			}catch(Exception e)
			{
			return false;	
				
			}
	   
	 
	   
   }

  
}
