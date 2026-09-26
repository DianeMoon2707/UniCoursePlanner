package com.uni_course_planner.dto.module;

import java.util.Set;

import com.uni_course_planner.constants.module.EventTypes;

public class ModuleDTOWithEdit extends ModuleDTOWithID
{
	private String modulenameNew;
	private int creditsNew;
	
	public ModuleDTOWithEdit() {}
	
	public ModuleDTOWithEdit(String modulename, int credits, Set<EventTypes> eventTypes, 
			Long moduleId, String modulenameNew, int creditsNew)
	{
		super(modulename, credits, eventTypes, moduleId);
		
		this.modulenameNew = modulenameNew;
		this.creditsNew = creditsNew;
	}

	public String getModulenameNew()
	{
		return modulenameNew;
	}

	public void setModulenameNew(String modulenameNew)
	{
		this.modulenameNew = modulenameNew;
	}

	public int getCreditsNew()
	{
		return creditsNew;
	}

	public void setCreditsNew(int creditsNew) 
	{
		this.creditsNew = creditsNew;
	}
}
