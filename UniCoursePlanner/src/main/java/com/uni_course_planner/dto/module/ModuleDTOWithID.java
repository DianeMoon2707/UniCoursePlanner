package com.uni_course_planner.dto.module;

import java.util.Set;

import com.uni_course_planner.constants.module.EventTypes;

public class ModuleDTOWithID extends ModuleDTO
{
	private Long moduleId;
	
	public ModuleDTOWithID() {}
	
	public ModuleDTOWithID(String modulename, int credits, Set<EventTypes> eventTypes, Long moduleId)
	{
		super(modulename, credits, eventTypes);
		
		this.moduleId = moduleId;
	}

	public Long getModuleId()
	{
		return moduleId;
	}

	public void setModuleId(Long moduleId) 
	{
		this.moduleId = moduleId;
	}
}
