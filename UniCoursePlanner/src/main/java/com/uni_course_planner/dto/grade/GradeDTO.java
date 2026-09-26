package com.uni_course_planner.dto.grade;

import com.uni_course_planner.constants.grade.Grades;
import com.uni_course_planner.dto.FieldDTO;

public class GradeDTO extends FieldDTO
{
	private Long moduleId;
	private String modulename;
	private int credits;
	private Grades grade;
	
	public GradeDTO() {}
	
	public GradeDTO(Long moduleId, String modulename, int credits, Grades grade)
	{
		this.moduleId = moduleId;
		this.modulename = modulename;
		this.credits = credits;
		this.grade = grade;
	}

	public Long getModuleId() 
	{
		return moduleId;
	}

	public void setModuleId(Long moduleId)
	{
		this.moduleId = moduleId;
	}

	public String getModulename()
	{
		return modulename;
	}

	public void setModulename(String modulename) 
	{
		this.modulename = modulename;
	}

	public int getCredits() 
	{
		return credits;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
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
