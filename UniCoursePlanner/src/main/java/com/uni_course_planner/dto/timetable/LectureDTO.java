package com.uni_course_planner.dto.timetable;

import com.uni_course_planner.constants.module.EventTypes;

public class LectureDTO
{
	private String modul;
	private EventTypes event;
	private String room;

	public LectureDTO(String modul, EventTypes event, String room)
	{
		this.modul = modul;
		this.event = event;		
		this.room = room;
	}

	public String getModul() 
	{
		return modul;
	}

	public void setModul(String modul) 
	{
		this.modul = modul;
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
