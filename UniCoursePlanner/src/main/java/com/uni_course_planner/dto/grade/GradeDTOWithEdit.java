package com.uni_course_planner.dto.grade;

import com.uni_course_planner.constants.grade.Grades;

public class GradeDTOWithEdit extends GradeDTO 
{
	private Grades gradeNew;
	
	public GradeDTOWithEdit() {}
	
	public GradeDTOWithEdit(Long moduleId, String modulename, int credits, Grades grade, Grades gradeNew) 
	{
		super(moduleId, modulename, credits, grade);
		this.gradeNew = gradeNew;
	}

	public Grades getGradeNew() 
	{
		return gradeNew;
	}

	public void setGradeNew(Grades gradeNew)
	{
		this.gradeNew = gradeNew;
	}
	
}
