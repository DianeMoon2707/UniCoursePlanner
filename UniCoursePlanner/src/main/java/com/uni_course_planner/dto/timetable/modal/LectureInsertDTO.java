package com.uni_course_planner.dto.timetable.modal;

import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.dto.FieldDTO;

public class LectureInsertDTO extends FieldDTO
{
	private Timeslot time;
	private Weekday day;
	
	private String modul;
	private EventTypes event;
	private String room;
	
	public LectureInsertDTO() {}
	
	public LectureInsertDTO(Timeslot time, Weekday day, String modul, EventTypes event, String room) 
	{
		this.time = time;
		this.day = day;
		this.modul = modul;
		this.event = event;
		this.room = room;
	}

	public Timeslot getTime()
	{
		return time;
	}

	public void setTime(Timeslot time)
	{
		this.time = time;
	}

	public Weekday getDay() 
	{
		return day;
	}

	public void setDay(Weekday day)
	{
		this.day = day;
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
