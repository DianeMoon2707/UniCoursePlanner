package com.uni_course_planner.dto.calender;

import java.time.*;

public class CalenderDTOWithEdit extends CalenderDTOWithID
{
	private LocalTime timeNeu;
	private String topicNeu;
	private String extensionNeu;
	
	public CalenderDTOWithEdit() {}
	
	public CalenderDTOWithEdit(LocalDate date, LocalTime time, 
			String topic, String extension, Long id,
			LocalTime timeNeu, String topicNeu, String extensionNeu)
	{
		super(date, time, topic, extension, id);
		
		this.timeNeu = timeNeu;
		this.topicNeu = topicNeu;
		this.extensionNeu = extensionNeu;
	}

	public LocalTime getTimeNeu() 
	{
		return timeNeu;
	}

	public void setTimeNeu(LocalTime timeNeu) 
	{
		this.timeNeu = timeNeu;
	}

	public String getTopicNeu() 
	{
		return topicNeu;
	}

	public void setTopicNeu(String topicNeu)
	{
		this.topicNeu = topicNeu;
	}

	public String getExtensionNeu() 
	{
		return extensionNeu;
	}

	public void setExtensionNeu(String extensionNeu)
	{
		this.extensionNeu = extensionNeu;
	}	
}
