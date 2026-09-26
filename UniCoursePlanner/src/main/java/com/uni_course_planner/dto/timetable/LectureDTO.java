package com.uni_course_planner.dto.timetable;

import com.uni_course_planner.constants.module.EventTypes;

public class LectureDTO
{
	private String module;
	private EventTypes event;
	private String room;

	public LectureDTO(String module, EventTypes event, String room)
	{
		this.module = module;
		this.event = event;		
		this.room = room;
	}

	public String getModule() 
	{
		return module;
	}

	public void setModule(String module) 
	{
		this.module = module;
	}

	public EventTypes getEvent() 
	{
		return event;
	}

	public void setEvent(EventTypes event) 
	{
		this.event = event;
	}

	public String getRoom() 
	{
		return room;
	}

	public void setRoom(String room) 
	{
		this.room = room;
	}
}
