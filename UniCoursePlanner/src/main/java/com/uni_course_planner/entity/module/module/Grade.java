package com.uni_course_planner.entity.module.module;

import com.uni_course_planner.constants.grade.Grades;

import jakarta.persistence.*;

@Embeddable
public class Grade 
{
	@Enumerated(EnumType.STRING)
	private Grades grade;

	public Grade() {}
	
	public Grade(Grades grade) 
	{
		this.grade = grade;
	}

	public Grades getGrade() 
	{
		return grade;
	}

	public void setGrade(Grades grade) 
	{
		this.grade = grade;
	}
}
