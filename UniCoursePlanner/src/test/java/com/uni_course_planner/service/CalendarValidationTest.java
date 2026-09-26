package com.uni_course_planner.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.uni_course_planner.service.validation.CalendarValidation;

public class CalendarValidationTest 
{	
	private CalendarValidation calendarValidation = new CalendarValidation();
	
	@Test
	public void testEntryIsNotBeforeToday_dateBeforeToday_throwException()
	{
		LocalDate date = LocalDate.now().minusDays(1);
		
		assertThrows(
			IllegalArgumentException.class,	
			() -> calendarValidation.validateEntryIsNotBeforeToday(date));
	}
	
	@Test
	public void testEntryIsNotBeforeToday_dateIsToday_throwNoException()
	{
		LocalDate date = LocalDate.now();
		
		assertDoesNotThrow(
			() -> calendarValidation.validateEntryIsNotBeforeToday(date));
	}
	
	@Test
	public void testEntryIsNotBeforeToday_dateIsTomorrow_throwNoException()
	{
		LocalDate date = LocalDate.now().plusDays(1);
		
		assertDoesNotThrow(
			() -> calendarValidation.validateEntryIsNotBeforeToday(date));
	}
}
