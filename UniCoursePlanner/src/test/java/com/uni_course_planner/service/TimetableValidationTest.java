package com.uni_course_planner.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.entity.module.event_type.*;
import com.uni_course_planner.entity.timetable.Timetable;
import com.uni_course_planner.repository.timetable.TimetableRepository;
import com.uni_course_planner.service.validation.TimetableValidation;

@ExtendWith(MockitoExtension.class)
public class TimetableValidationTest 
{
	@Mock
	private TimetableRepository timetableRep;
	
	@InjectMocks
	private TimetableValidation timetableValidation;
		
	private Weekday day = Weekday.MI;
	private Timeslot time = Timeslot.SLOT_10_12;
	
	//Erstellung von Einträgen
	@Test
	public void testUserAlreadyGeneratedEntryForCell_entryExists_throwException()
	{	
		EventType event = mock(EventType.class);
		Timetable entry = mock(Timetable.class);
		
		when(timetableRep.findByTimeDayAndEvent(time, day, event)).thenReturn(Optional.of(entry));
		
		assertThrows(
			IllegalArgumentException.class,
			() -> timetableValidation.validateUserAlreadyGeneratedEntryForCell(event, day, time));
		
		verify(timetableRep).findByTimeDayAndEvent(time, day, event);
	}
	
	@Test
	public void testUserAlreadyGeneratedEntryForCell_entryDoesNotExist_throwNoException()
	{	
		EventType event = mock(EventType.class);
		
		when(timetableRep.findByTimeDayAndEvent(time, day, event)).thenReturn(Optional.empty());
		
		assertDoesNotThrow(
			() -> timetableValidation.validateUserAlreadyGeneratedEntryForCell(event, day, time));
		
		verify(timetableRep).findByTimeDayAndEvent(time, day, event);
	}
}
