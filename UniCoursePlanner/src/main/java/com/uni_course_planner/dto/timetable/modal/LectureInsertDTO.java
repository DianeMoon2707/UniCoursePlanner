package com.uni_course_planner.dto.timetable.modal;

import java.util.List;

import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.dto.FieldDTO;

public class LectureInsertDTO extends FieldDTO
{
	private Timeslot time;
	private Weekday day;
	
	private String selectedModul;
	private List<String> modulOptionen;
	
	private String room;
	
	public LectureInsertDTO(List<String> modulOptionen) 
	{
		this.modulOptionen = modulOptionen;
	}
	
	public LectureInsertDTO(Timeslot time, Weekday day, String selectedModul, String room) 
	{
		this.time = time;
		this.day = day;
		
		this.selectedModul = selectedModul;
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

	public String getSelectedModul()
	{
		return selectedModul;
	}

	public void setSelectedModul(String modul) 
	{
		this.selectedModul = modul;
	}	

	public List<String> getModulOptionen() 
	{
		return modulOptionen;
	}

	public void setModulOptionen(List<String> modulOptionen) 
	{
		this.modulOptionen = modulOptionen;
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
