package com.uni_course_planner.dto.module;

import java.util.Set;

import com.uni_course_planner.constants.module.EventTypes;

public class ModuleDTOWithID extends ModuleDTO
{
	private Long modul_id;
	
	public ModuleDTOWithID()	{}
	
	public ModuleDTOWithID(String modulname, int lp, Set<EventTypes> eventTypes, Long modul_id)
	{
		super(modulname, lp, eventTypes);
		
		this.modul_id = modul_id;
	}

	public Long getModul_id()
	{
		return modul_id;
	}

	public void setModul_id(Long modul_id) 
	{
		this.modul_id = modul_id;
	}
}
