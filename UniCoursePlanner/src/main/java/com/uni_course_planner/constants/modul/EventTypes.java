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
	
	public static EventTypes fromDescriptionToEnum(String description)
	{
		String normalized = description.trim();
		
		for(EventTypes type : EventTypes.values())
		{
			if(type.getDescription().equals(normalized))
			{
				return type;
			}
		}
		throw new IllegalArgumentException("Unbekannte Beschreibung: " + description);
	}
}
