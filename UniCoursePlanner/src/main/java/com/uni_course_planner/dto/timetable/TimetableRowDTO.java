package com.uni_course_planner.dto.timetable;

import java.util.*;

import com.uni_course_planner.constants.timetable.Timeslot;

//Represents one row of the timetable for a specific time slot
public class TimetableRowDTO 
{
	private Timeslot slot;
	//Contains the lectures for each weekday
	private List<List<LectureDTO>> lectures;
	
	public TimetableRowDTO(Timeslot slot, List<List<LectureDTO>> lectures)
	{
		this.slot = slot;
		this.lectures = lectures;
	}

	public Timeslot getSlot() 
	{
		return slot;
	}

	public List<List<LectureDTO>> getLectures() 
	{
		return lectures;
	}
}
