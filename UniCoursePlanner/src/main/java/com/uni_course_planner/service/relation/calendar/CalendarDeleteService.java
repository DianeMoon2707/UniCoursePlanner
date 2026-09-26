package com.uni_course_planner.service.relation.calendar;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.calendar.CalendarDTOWithID;
import com.uni_course_planner.entity.calendar.Calendar;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.repository.calendar.CalendarRepository;
import com.uni_course_planner.service.modal.strategy.DeleteStrategy;

@Service
public class CalendarDeleteService implements DeleteStrategy
{
	private CalendarRepository calendarRep;

	public CalendarDeleteService(CalendarRepository calendarRep) 
	{
		this.calendarRep = calendarRep;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.CALENDAR;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new CalendarDTOWithID();
	}

	@Override
	public FieldDTO createDTO(String data, LogInData user) 
	{
		CalendarDTOWithID dto = new CalendarDTOWithID();
		
		Long id = Long.parseLong(data);
		Calendar calendar = calendarRep.findById(id).get();
		
		dto.setId(id);
		dto.setDate(calendar.getDate());
		dto.setTime(calendar.getTime());
		dto.setTopic(calendar.getTopic());
		dto.setExtension(calendar.getExtension());
		
		return dto;
	}

	@Override
	public void delete(FieldDTO dto, LogInData user) 
	{
		CalendarDTOWithID calendarDTO = (CalendarDTOWithID)dto;
		calendarRep.deleteById(calendarDTO.getId());
	}

}
