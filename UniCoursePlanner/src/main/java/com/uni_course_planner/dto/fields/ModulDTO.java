package com.uni_course_planner.dto.fields;

import java.util.Set;

import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.dto.FieldDTO;

public class ModulDTO extends FieldDTO
{
	private Long modul_id;
	private Long user_id;
	
	private String modulname;
	private int lp;
	
	private Set<EventTypes> eventTypes;

	public ModulDTO() {}
	
	public ModulDTO(String modulname, int lp, Set<EventTypes> eventTypes) 
	{		
		this.modulname = modulname;
		this.lp = lp;
		this.eventTypes = eventTypes;
	}
	
	public ModulDTO(Long modul_id, Long user_id, String modulname, int lp, Set<EventTypes> eventTypes)
	{
		this(modulname, lp, eventTypes);
		
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

	public Set<EventTypes> getEventTypes()
	{
		return eventTypes;
	}

	public void setEventTypes(Set<EventTypes> eventTypes) 
	{
		this.eventTypes = eventTypes;
	}
}
