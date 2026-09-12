package com.uni_course_planner.dto.calender;

import java.time.*;

public class CalenderDTOWithID extends CalenderDTO
{
	private Long id;
	
	public CalenderDTOWithID() {}

	public CalenderDTOWithID(LocalDate date, LocalTime time, String topic, String extension, Long id) {
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
