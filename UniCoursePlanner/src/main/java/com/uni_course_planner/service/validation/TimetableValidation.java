package com.uni_course_planner.service.validation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.relation.modul.event_type.EventType;
import com.uni_course_planner.relation.timetable.Timetable;
import com.uni_course_planner.repository.timetable.TimetableRepository;

@Service
public class TimetableValidation
{
	private TimetableRepository timetableRep;

	public TimetableValidation(TimetableRepository timetableRep)
	{
		this.timetableRep = timetableRep;
	}
	
	public void validateUserAlreadyGeneratedEntryForCell(EventType event, Weekday weekday, Timeslot timeslot)
	{
		Optional<Timetable> entry = timetableRep.findEntryOfACell(timeslot, weekday, event);
		
		if(entry.isPresent())
		{
			throw new IllegalArgumentException("Dieses Event existiert bereits für diese Zeit.");
		}
	}
}
