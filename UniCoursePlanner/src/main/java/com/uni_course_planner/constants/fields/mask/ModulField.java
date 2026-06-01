package com.uni_course_planner.constants.fields.mask;

public enum ModulField implements MaskField
{
	MODULNAME("modulname"),
	LP("lp");
	
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
