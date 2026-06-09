package com.uni_course_planner.dto.modulnote;

import com.uni_course_planner.constants.modulnote.Grades;
import com.uni_course_planner.dto.FieldDTO;

public class ModulnoteDTO extends FieldDTO
{
	private Long modul_id;
	private String modulname;
	private int lp;
	private Grades grade;
	
	public ModulnoteDTO() {}
	
	public ModulnoteDTO(Long modul_id, String modulname, int lp, Grades grade)
	{
		this.modul_id = modul_id;
		this.modulname = modulname;
		this.lp = lp;
		this.grade = grade;
	}

	public Long getModul_id() 
	{
		return modul_id;
	}

	public void setModul_id(Long modul_id)
	{
		this.modul_id = modul_id;
	}

	public String getModulname()
	{
		return modulname;
	}

	public void setModulname(String modulname) 
	{
		this.modulname = modulname;
	}

	public int getLp() 
	{
		return lp;
	}

	public void setLp(int lp)
	{
		this.lp = lp;
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
