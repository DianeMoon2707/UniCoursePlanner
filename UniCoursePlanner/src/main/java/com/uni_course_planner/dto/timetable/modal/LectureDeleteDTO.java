package com.uni_course_planner.dto.timetable.modal;

import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.dto.FieldDTO;

public class LectureDeleteDTO extends FieldDTO
{
	private String modulename;
	private Weekday weekday;
	private Timeslot time;
	private String room;
	
	public LectureDeleteDTO() {}
	
	public LectureDeleteDTO(String modulename, Weekday weekday, Timeslot time, String room) 
	{
		this.modulename = modulename;
		this.weekday = weekday;
		this.time = time;
		this.room = room;
	}

	public String getModulename() 
	{
		return modulename;
	}

	public void setModulename(String modulename) 
	{
		this.modulename = modulename;
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
	
	/**
	 * Extracts the module and room from the HTML grid entry.
	 * The expected format is:
	 * <Module> - <Event>;
	 * Room: <Room>
	 * 
	 * Returns the module and event at index 0 and the room at index 1.
	 */
	public static String[] convertEntryData(String data)
	{
		String[]array = new String[4];
		
		String[] textParts = data.split("\n");
		
		String modulename = textParts[0] + " - " + textParts[1].substring(0, textParts[1].length()-1);
		
		String room = "";
		if(textParts[2].length() > "Raum:".length())
		{
			room = textParts[2].substring(textParts[2].indexOf(":") + 2).trim();
		}
		
		array[0] = modulename;
		array[1] = room;
		
		return array;
	}
}
