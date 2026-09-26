package com.uni_course_planner.service.relation.calendar;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.calendar.CalendarDTOWithEdit;
import com.uni_course_planner.entity.calendar.Calendar;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.repository.calendar.CalendarRepository;
import com.uni_course_planner.service.modal.strategy.EditStrategy;
import com.uni_course_planner.service.validation.CalendarValidation;

@Service
public class CalendarEditService implements EditStrategy
{
	private CalendarRepository calendarRep;
	private CalendarValidation validation;

	public CalendarEditService(CalendarRepository calendarRep, CalendarValidation validation) 
	{
		super();
		this.calendarRep = calendarRep;
		this.validation = validation;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.CALENDAR;
	}

	@Override
	public FieldDTO createDTO()
	{
		return new CalendarDTOWithEdit();
	}

	@Override
	public FieldDTO createDTO(String data, LogInData user) 
	{
		CalendarDTOWithEdit dto = new CalendarDTOWithEdit();
		
		Long id = Long.parseLong(data);
		Calendar calendar = calendarRep.findById(id).get();
		
		dto.setId(id);
		dto.setDate(calendar.getDate());
		
		dto.setTime(calendar.getTime());
		dto.setTimeNew(calendar.getTime());
		
		dto.setTopic(calendar.getTopic());
		dto.setTopicNew(calendar.getTopic());
		
		dto.setExtension(calendar.getExtension());
		dto.setExtensionNew(calendar.getExtension());
		
		return dto;
	}

	@Override
	public void edit(FieldDTO dto, LogInData user)
	{
		CalendarDTOWithEdit calendarDTO = (CalendarDTOWithEdit)dto;
		Calendar calendar = calendarRep.findById(calendarDTO.getId()).orElseThrow();
		
		LocalDate date = calendarDTO.getDate();
		validation.validateEntryIsNotBeforeToday(date);
		
		calendar.setTime(calendarDTO.getTimeNew());
		calendar.setTopic(calendarDTO.getTopicNew());
		calendar.setExtension(calendarDTO.getExtensionNew());
		
		calendarRep.save(calendar);
	}

}
