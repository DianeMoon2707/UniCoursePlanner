package com.uni_course_planner.constants.timetable;

public enum Weekday 
{
	MO(0, "Montag"),
	DI(1, "Dienstag"),
	MI(2, "Mittwoch"),
	DO(3, "Donnerstag"),
	FR(4, "Freitag");
	
	private int order;
	private String description;
	
	Weekday(int order, String description)
	{
		this.order = order;
		this.description = description;
	}

	public int getOrder()
	{
		return order;
	}
	
	//Returns the enum value with the specified order
	public static Weekday getIndex(int index)
	{
		for(Weekday slot : values())
		{
			if(slot.order == index)
			{
				return slot;
			}
		}
		
		throw new IllegalArgumentException("Kein Timeslot für Order");
	}
	
	public String getDescription() 
	{
		return description;
	}
}
