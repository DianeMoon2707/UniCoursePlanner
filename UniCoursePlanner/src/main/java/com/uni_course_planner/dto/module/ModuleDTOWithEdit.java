package com.uni_course_planner.dto.module;

import java.util.Set;

import com.uni_course_planner.constants.module.EventTypes;

public class ModuleDTOWithEdit extends ModuleDTOWithID
{
	private String modulnameNeu;
	private int lpNeu;
	
	public ModuleDTOWithEdit() {}
	
	public ModuleDTOWithEdit(String modulname, int lp, Set<EventTypes> eventTypes, 
			Long modul_id, String modulnameNeu, int lpNeu)
	{
		super(modulname, lp, eventTypes, modul_id);
		
		this.modulnameNeu = modulnameNeu;
		this.lpNeu = lpNeu;
	}

	public String getModulnameNeu()
	{
		return modulnameNeu;
	}

	public void setModulnameNeu(String modulnameNeu)
	{
		this.modulnameNeu = modulnameNeu;
	}

	public int getLpNeu()
	{
		return lpNeu;
	}

	public void setLpNeu(int lpNeu) 
	{
		this.lpNeu = lpNeu;
	}
}
