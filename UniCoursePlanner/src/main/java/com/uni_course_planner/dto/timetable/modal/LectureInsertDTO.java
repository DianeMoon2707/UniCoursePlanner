package com.uni_course_planner.dto.timetable.modal;

import java.util.List;

import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.dto.FieldDTO;

public class LectureInsertDTO extends FieldDTO
{
	private Timeslot time;
	private Weekday day;
	
	private String selectedModule;
	private List<String> moduleOptions;
	
	private String room;
	
	//Creates the DTO with the available modules for the insert form
	public LectureInsertDTO(List<String> moduleOptions) 
	{
		this.moduleOptions = moduleOptions;
	}
	
	//Creates the DTO with the selected lecture data
	public LectureInsertDTO(Timeslot time, Weekday day, String selectedModule, String room) 
	{
		this.time = time;
		this.day = day;
		
		this.selectedModule = selectedModule;
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

	public String getSelectedModule()
	{
		return selectedModule;
	}

	public void setSelectedModule(String module) 
	{
		this.selectedModule = module;
	}	

	public List<String> getModuleOptions() 
	{
		return moduleOptions;
	}

	public void setModulOptions(List<String> moduleOptions) 
	{
		this.moduleOptions = moduleOptions;
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
