package com.uni_course_planner.dto.module;

import java.util.Set;

import com.uni_course_planner.constants.module.EventTypes;
import com.uni_course_planner.dto.FieldDTO;

public class ModuleDTO extends FieldDTO
{	
	private String modulename;
	private int credits;
	
	private Set<EventTypes> eventTypes;

	public ModuleDTO() {}
	
	public ModuleDTO(String modulename, int credits, Set<EventTypes> eventTypes) 
	{		
		this.modulename = modulename;
		this.credits = credits;
		this.eventTypes = eventTypes;
	}

	public String getModulename() 
	{
		return modulename;
	}

	public void setModulename(String modulename) 
	{
		this.modulename = modulename;
	}

	public int getCredits() 
	{
		return credits;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
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
