package com.niit.sms.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="marks")
public class Marks {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="marks_in_english")
	private double marksInEnglish;
	@Column(name="marks_in_hindi")
	private double marksInHindi;
	public double getAttendence() {
		return attendence;
	}


	public void setAttendence(double attendence) {
		this.attendence = attendence;
	}




	@Column(name="marks_in_science")
	private double marksInScience;
	@Column(name="marks_in_math")
	private double marksInMath;
	@Column(name="marks_in_social_science")
	private double marksInSocialScience;
	@Column(name="attendence")
	private double attendence;
	@Transient
	private double percentage;
	
	public double getPercentage() {
		return ((this.marksInEnglish+this.marksInHindi+this.marksInMath+this.marksInScience+this.marksInSocialScience)*100)/500;
	}




	@Column(name="student_id")
	private int studentId; 
	
	
	public Marks() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getMarksInEnglish() {
		return marksInEnglish;
	}


	public void setMarksInEnglish(double marksInEnglish) {
		this.marksInEnglish = marksInEnglish;
	}


	public double getMarksInHindi() {
		return marksInHindi;
	}


	public void setMarksInHindi(double marksInHindi) {
		this.marksInHindi = marksInHindi;
	}


	public double getMarksInScience() {
		return marksInScience;
	}


	public void setMarksInScience(double marksInScience) {
		this.marksInScience = marksInScience;
	}


	public double getMarksInMath() {
		return marksInMath;
	}


	public void setMarksInMath(double marksInMath) {
		this.marksInMath = marksInMath;
	}


	public double getMarksInSocialScience() {
		return marksInSocialScience;
	}


	public void setMarksInSocialScience(double marksInSocialScience) {
		this.marksInSocialScience = marksInSocialScience;
	}


	public int getStudentId() {
		return studentId;
	}


	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
  
	
}
