package com.uni_course_planner.service.relation.calendar;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Service;

import com.uni_course_planner.dto.calendar.CalendarDTOWithID;
import com.uni_course_planner.entity.calendar.Calendar;
import com.uni_course_planner.repository.calendar.CalendarRepository;

@Service
public class CalendarTableService 
{
	private CalendarRepository calendarRep;

	public CalendarTableService(CalendarRepository calendarRep)
	{
		this.calendarRep = calendarRep;
	}
	
	public List<CalendarDTOWithID> fillCalendarTable(Long user, LocalDate date)
	{
		List<CalendarDTOWithID> tableData = new ArrayList<CalendarDTOWithID>();
		List<Calendar> events = calendarRep.getEventsOfDay(user, date);
		
		for(Calendar event : events)
		{
			tableData.add(
				new CalendarDTOWithID(
					date, event.getTime(), event.getTopic(), 
					event.getExtension(), event.getId()
				)
			);
		}
		
		return tableData;
	}

	public List<LocalDate> getEventDays(Long user, int year, int month) 
	{
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
		
		return calendarRep.getEventDates(user, startDate, endDate);
	}
}
