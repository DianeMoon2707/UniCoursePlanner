package com.uni_course_planner.dto.timetable.modal;

import com.uni_course_planner.constants.timetable.Timeslot;
import com.uni_course_planner.constants.timetable.Weekday;

public class LectureEditDTO extends LectureDeleteDTO
{
	private String roomNew;

	public LectureEditDTO() {}
	
	public LectureEditDTO(String modulename, Weekday weekday, Timeslot time, String room, String roomNew) 
	{
		super(modulename, weekday, time, room);
		this.roomNew = roomNew;
	}

	public String getRoomNew() 
	{
		return roomNew;
	}

	public void setRoomNew(String roomNew) 
	{
		this.roomNew = roomNew;
	}
}
