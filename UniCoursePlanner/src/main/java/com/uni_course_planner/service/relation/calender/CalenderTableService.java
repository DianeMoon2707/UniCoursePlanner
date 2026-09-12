package com.uni_course_planner.service.relation.calender;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Service;

import com.uni_course_planner.dto.calender.CalenderDTOWithID;
import com.uni_course_planner.relation.calender.Calender;
import com.uni_course_planner.repository.calender.CalenderRepository;

@Service
public class CalenderTableService 
{
	private CalenderRepository calenderRep;

	public CalenderTableService(CalenderRepository calenderRep)
	{
		this.calenderRep = calenderRep;
	}
	
	public List<CalenderDTOWithID> fillCalenderTable(Long user, LocalDate date)
	{
		List<CalenderDTOWithID> tableData = new ArrayList<CalenderDTOWithID>();
		List<Calender> events = calenderRep.getEventsOfDay(user, date);
		
		for(Calender event : events)
		{
			tableData.add(
				new CalenderDTOWithID(
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
		
		return calenderRep.getEventDates(user, startDate, endDate);
	}
}
