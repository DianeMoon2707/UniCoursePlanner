package com.uni_course_planner.constants.fields.mask;

import com.uni_course_planner.constants.modul.EventTypes;

public enum ModulField implements MaskField
{
	MODULNAME("modulname"),
	LP("lp"),
	VL(EventTypes.getLowerCase(EventTypes.VL)),
	UB(EventTypes.getLowerCase(EventTypes.UB)),
	SEM(EventTypes.getLowerCase(EventTypes.SEM));
	
	private String htmlName;
	
	ModulField(String htmlName)
	{
		this.htmlName = htmlName;
	}

	public String getHtmlName() 
	{
		return htmlName;
	}
}
