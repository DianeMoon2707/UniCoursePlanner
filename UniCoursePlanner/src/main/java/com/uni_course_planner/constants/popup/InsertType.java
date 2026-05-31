package com.uni_course_planner.constants.popup;

public enum InsertType
{
	MODUL("Neues Modul anlegen"),
	KALENDER("Neuen Kalendereintrag anlegen");
	
	private final String headline;
	
	InsertType(String headline)
	{
		this.headline = headline;
	}
	
	public String getHeadline()
	{
		return headline;
	}
}
