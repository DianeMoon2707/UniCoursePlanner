package com.uni_course_planner.constants.timetable;

public enum Timeslot 
{
	SLOT_08_10(0, "8 - 10"),
	SLOT_10_12(1, "10 - 12"),
	SLOT_12_14(2, "12 - 14"),
	SLOT_14_16(3, "14 - 16"),
	SLOT_16_18(4, "16 - 18");
	
	private int order;
	private String time;
	
	Timeslot(int order, String time)
	{
		this.order = order;
		this.time = time;
	}

	public int getOrder()
	{
		return order;
	}
	
	//Returns the enum value with the specified order
	public static Timeslot getIndex(int index)
	{
		for(Timeslot slot : values())
		{
			if(slot.order == index)
			{
				return slot;
			}
		}
		
		throw new IllegalArgumentException("Kein Timeslot für Order");
	}
	
	public String getTime() 
	{
		return time;
	}
}
