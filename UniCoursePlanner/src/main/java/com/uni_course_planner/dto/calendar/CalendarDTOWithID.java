package com.uni_course_planner.dto.calendar;

import java.time.*;

public class CalendarDTOWithID extends CalendarDTO
{
	private Long id;
	
	public CalendarDTOWithID() {}

	public CalendarDTOWithID(LocalDate date, LocalTime time, String topic, String extension, Long id) {
		super(date, time, topic, extension);
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}
}
