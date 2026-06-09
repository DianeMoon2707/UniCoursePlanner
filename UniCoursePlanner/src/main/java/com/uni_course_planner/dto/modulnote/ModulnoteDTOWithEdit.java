package com.uni_course_planner.dto.modulnote;

import com.uni_course_planner.constants.modulnote.Grades;

public class ModulnoteDTOWithEdit extends ModulnoteDTO 
{
	private Grades gradeNeu;
	
	public ModulnoteDTOWithEdit() {}
	
	public ModulnoteDTOWithEdit(Long modul_id, String modulname, int lp, Grades grade, Grades gradeNeu) 
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
