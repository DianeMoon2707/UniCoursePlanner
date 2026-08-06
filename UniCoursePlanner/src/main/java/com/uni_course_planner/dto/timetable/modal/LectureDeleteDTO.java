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
	
	public static String[] convertEntryData(String data)
	{
		String[]array = new String[4];
		
		String[] textParts = data.split("\n");
		
		String modulname = textParts[0] + " - " + textParts[1].substring(0, textParts[1].length()-1);
		
		String room = "";
		if(textParts[2].length() > "Raum:".length())
		{
			room = textParts[2].substring(textParts[2].indexOf(":") + 2).trim();
		}
		
		array[0] = modulname;
		array[1] = room;
		
		return array;
	}
}
