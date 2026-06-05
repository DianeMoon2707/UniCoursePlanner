package com.uni_course_planner.dto.modul;

import java.util.Set;

import com.uni_course_planner.constants.modul.EventTypes;

public class ModulDTOWithID extends ModulDTO
{
	private Long modul_id;
	private Long user_id;
	
	public ModulDTOWithID()	{}
	
	public ModulDTOWithID(String modulname, int lp, Set<EventTypes> eventTypes, Long modul_id, Long user_id)
	{
		super(modulname, lp, eventTypes);
		
		this.modul_id = modul_id;
		this.user_id = user_id;
	}

	public Long getModul_id()
	{
		return modul_id;
	}

	public void setModul_id(Long modul_id) 
	{
		this.modul_id = modul_id;
	}

	public Long getUser_id() 
	{
		return user_id;
	}

	public void setUser_id(Long user_id) 
	{
		this.user_id = user_id;
	}
}
