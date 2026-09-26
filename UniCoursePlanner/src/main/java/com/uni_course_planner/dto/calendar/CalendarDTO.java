package com.uni_course_planner.dto.calendar;

import java.time.*;

import com.uni_course_planner.dto.FieldDTO;

public class CalendarDTO extends FieldDTO
{
	private LocalDate date;
	private LocalTime time;
	private String topic;
	private String extension;
	
	public CalendarDTO() {}
	
	public CalendarDTO(LocalDate date, LocalTime time, String topic, String extension) 
	{
		this.date = date;
		this.time = time;
		this.topic = topic;
		this.extension = extension;
	}
	
	public LocalDate getDate() 
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	public LocalTime getTime() 
	{
		return time;
	}

	public void setTime(LocalTime time) 
	{
		this.time = time;
	}

	public String getTopic() 
	{
		return topic;
	}

	public void setTopic(String topic) 
	{
		this.topic = topic;
	}

	public String getExtension() 
	{
		if(extension == null)
		{
			return "";
		}
		
		return extension;
	}

	//Allows the user to optionally add a further description to a calendar entry
	public void setExtension(String extension) 
	{
		if(extension == null)
		{
			this.extension = "";
		}
		else
		{
			this.extension = extension;
		}
	}
}
