package com.uni_course_planner.service.validation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.entity.module.event_type.EventType;
import com.uni_course_planner.entity.timetable.Timetable;
import com.uni_course_planner.repository.timetable.TimetableRepository;

//Validates timetable entries
@Service
public class TimetableValidation
{
	private TimetableRepository timetableRep;

	public TimetableValidation(TimetableRepository timetableRep)
	{
		this.timetableRep = timetableRep;
	}
	
	public void validateCellDoesNotAlreadyContainEvent(EventType event, Weekday weekday, Timeslot timeslot)
	{
		Optional<Timetable> entry = timetableRep.findByTimeDayAndEvent(timeslot, weekday, event);
		
		if(entry.isPresent())
		{
			throw new IllegalArgumentException("Dieses Event existiert bereits für diese Zeit.");
		}
	}
}
