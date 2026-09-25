package com.uni_course_planner.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.uni_course_planner.service.validation.KalenderValidation;

public class KalenderValidationTest 
{	
	private KalenderValidation kalenderValidation = new KalenderValidation();
	
	@Test
	public void testEntryIsNotBeforeToday_dateBeforeToday_throwException()
	{
		LocalDate date = LocalDate.now().minusDays(1);
		
		assertThrows(
			IllegalArgumentException.class,	
			() -> kalenderValidation.validateEntryIsNotBeforeToday(date));
	}
	
	@Test
	public void testEntryIsNotBeforeToday_dateIsToday_throwNoException()
	{
		LocalDate date = LocalDate.now();
		
		assertDoesNotThrow(
			() -> kalenderValidation.validateEntryIsNotBeforeToday(date));
	}
	
	@Test
	public void testEntryIsNotBeforeToday_dateIsTomorrow_throwNoException()
	{
		LocalDate date = LocalDate.now().plusDays(1);
		
		assertDoesNotThrow(
			() -> kalenderValidation.validateEntryIsNotBeforeToday(date));
	}
}
