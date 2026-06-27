package com.uni_course_planner.dto.timetable;

import java.util.List;

import com.uni_course_planner.constants.timetable.Timeslot;

public class TimetableCellDTO 
{
	private Timeslot slot;
	private List<LectureDTO> lectures;
	
	public TimetableCellDTO(Timeslot slot, List<LectureDTO> lectures)
	{
		this.slot = slot;
		this.lectures = lectures;
	}

	public Timeslot getSlot() 
	{
		return slot;
	}

	public List<LectureDTO> getLectures() 
	{
		return lectures;
	}
}
