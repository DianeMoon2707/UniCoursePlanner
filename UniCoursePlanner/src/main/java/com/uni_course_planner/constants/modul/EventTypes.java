package com.uni_course_planner.constants.modul;

public enum EventTypes
{
	VL("Vorlesung", "#e6f0ff"),
	UB("Übung", "#e6f7ea"),
	SEM("Seminar", "#fff2e6");
	
	private String description;
	private String hexColor;
	
	EventTypes(String description, String hexColor)
	{
		this.description = description;
		this.hexColor = hexColor;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public String getHexColor() 
	{
		return hexColor;
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
