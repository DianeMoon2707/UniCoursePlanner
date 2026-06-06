package com.uni_course_planner.dto.modul;

import java.util.Set;

import com.uni_course_planner.constants.modul.EventTypes;

public class ModulDTOWithEdit extends ModulDTOWithID
{
	private String modulnameNeu;
	private int lpNeu;
	
	public ModulDTOWithEdit() {}
	
	public ModulDTOWithEdit(String modulname, int lp, Set<EventTypes> eventTypes, 
			Long modul_id, Long user_id,
			String modulnameNeu, int lpNeu)
	{
		super(modulname, lp, eventTypes, modul_id, user_id);
		
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
