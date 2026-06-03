package com.uni_course_planner.constants.modul;

public enum EventTypes
{
	VL("Vorlesung"),
	UB("Übung"),
	SEM("Seminar");
	
	private String description;
	
	EventTypes(String description)
	{
		this.description = description;
	}

	public String getDescription() 
	{
		return description;
	}
	
	public static String getLowerCase(EventTypes type)
	{
		return type.toString().toLowerCase();
	}
}
