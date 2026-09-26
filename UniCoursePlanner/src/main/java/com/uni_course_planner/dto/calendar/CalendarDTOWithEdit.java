package com.uni_course_planner.dto.calendar;

import java.time.*;

public class CalendarDTOWithEdit extends CalendarDTOWithID
{
	private LocalTime timeNew;
	private String topicNew;
	private String extensionNew;
	
	public CalendarDTOWithEdit() {}
	
	public CalendarDTOWithEdit(LocalDate date, LocalTime time, 
			String topic, String extension, Long id,
			LocalTime timeNew, String topicNew, String extensionNew)
	{
		super(date, time, topic, extension, id);
		
		this.timeNew = timeNew;
		this.topicNew = topicNew;
		this.extensionNew = extensionNew;
	}

	public LocalTime getTimeNew() 
	{
		return timeNew;
	}

	public void setTimeNew(LocalTime timeNew) 
	{
		this.timeNew = timeNew;
	}

	public String getTopicNew() 
	{
		return topicNew;
	}

	public void setTopicNew(String topicNew)
	{
		this.topicNew = topicNew;
	}

	public String getExtensionNew() 
	{
		return extensionNew;
	}

	public void setExtensionNew(String extensionNew)
	{
		this.extensionNew = extensionNew;
	}	
}
