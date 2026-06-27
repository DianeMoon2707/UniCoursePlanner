package com.uni_course_planner.constants.timetable;

public enum Timeslot 
{
	SLOT_08_10("8 - 10"),
	SLOT_10_12("10 - 12"),
	SLOT_12_14("12 - 14"),
	SLOT_14_16("14 - 16"),
	SLOT_16_18("16 - 18");
	
	private String time;
	
	Timeslot(String time)
	{
		this.time = time;
	}

	public String getTime() 
	{
		return time;
	}
}
