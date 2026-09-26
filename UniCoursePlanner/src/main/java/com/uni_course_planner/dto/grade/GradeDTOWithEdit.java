package com.uni_course_planner.dto.grade;

import com.uni_course_planner.constants.grade.Grades;

public class GradeDTOWithEdit extends GradeDTO 
{
	private Grades gradeNeu;
	
	public GradeDTOWithEdit() {}
	
	public GradeDTOWithEdit(Long modul_id, String modulname, int lp, Grades grade, Grades gradeNeu) 
	{
		super(modul_id, modulname, lp, grade);
		this.gradeNeu = gradeNeu;
	}

	public Grades getGradeNeu() 
	{
		return gradeNeu;
	}

	public void setGradeNeu(Grades gradeNeu)
	{
		this.gradeNeu = gradeNeu;
	}
	
}
