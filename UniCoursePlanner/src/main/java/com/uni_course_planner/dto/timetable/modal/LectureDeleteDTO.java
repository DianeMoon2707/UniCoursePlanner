package com.uni_course_planner.dto.timetable.modal;

import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.dto.FieldDTO;

public class LectureDeleteDTO extends FieldDTO
{
	private String modulname;
	private Weekday weekday;
	private Timeslot time;
	private String room;
	
	public LectureDeleteDTO() {}
	
	public LectureDeleteDTO(String modulname, Weekday weekday, Timeslot time, String room) 
	{
		this.modulname = modulname;
		this.weekday = weekday;
		this.time = time;
		this.room = room;
	}

	public String getModulname() 
	{
		return modulname;
	}

	public void setModulname(String modulname) 
	{
		this.modulname = modulname;
	}

	public Weekday getWeekday() 
	{
		return weekday;
	}

	public void setWeekday(Weekday weekday) 
	{
		this.weekday = weekday;
	}

	public Timeslot getTime() 
	{
		return time;
	}

	public void setTime(Timeslot time) 
	{
		this.time = time;
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
