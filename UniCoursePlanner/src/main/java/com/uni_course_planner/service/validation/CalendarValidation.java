package com.uni_course_planner.service.validation;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class CalendarValidation
{	
	public void validateEntryIsNotBeforeToday(LocalDate date)
	{
		LocalDate today = LocalDate.now();
		
		if(date.isBefore(today))
		{
			throw new IllegalArgumentException("Bitte wähle ein Datum aus, das nicht in der Vergangenheit liegt.");
		}
	}
}
