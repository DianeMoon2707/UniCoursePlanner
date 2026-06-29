package com.uni_course_planner.constants.timetable;

public enum Weekday 
{
	MO("Montag"),
	DI("Dienstag"),
	MI("Mittwoch"),
	DO("Donnerstag"),
	FR("Freitag");
	
	private String description;
	
	Weekday(String description)
	{
		this.description = description;
	}

	public String getDescription() 
	{
		return description;
	}
}
