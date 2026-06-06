package com.uni_course_planner.dto.modul;

import java.util.Set;

import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.dto.FieldDTO;

public class ModulDTO extends FieldDTO
{	
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
