package com.uni_course_planner.dto.timetable.modal;

import com.uni_course_planner.constants.timetable.Timeslot;
import com.uni_course_planner.constants.timetable.Weekday;

public class LectureEditDTO extends LectureDeleteDTO
{
	private String roomNeu;

	public LectureEditDTO() {}
	
	public LectureEditDTO(String modulname, Weekday weekday, Timeslot time, String room, String roomNeu) 
	{
		super(modulname, weekday, time, room);
		this.roomNeu = roomNeu;
	}

	public String getRoomNeu() 
	{
		return roomNeu;
	}

	public void setRoomNeu(String roomNeu) 
	{
		this.roomNeu = roomNeu;
	}
}
