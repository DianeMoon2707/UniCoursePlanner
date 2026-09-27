package com.uni_course_planner.service.entity.calendar;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.calendar.CalendarDTO;
import com.uni_course_planner.entity.calendar.Calendar;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.repository.calendar.CalendarRepository;
import com.uni_course_planner.service.modal.strategy.InsertStrategy;
import com.uni_course_planner.service.validation.CalendarValidation;

//Saves calendar entries
@Service
public class CalendarInsertService implements InsertStrategy
{
	private CalendarRepository calendarRep;
	private CalendarValidation validation;

	public CalendarInsertService(CalendarRepository calendarRep, CalendarValidation validation) 
	{
		this.calendarRep = calendarRep;
		this.validation = validation;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.CALENDAR;
	}

	@Override
	public FieldDTO createDTO(LogInData currentUser)
	{
		return new CalendarDTO();
	}

	@Override
	public void save(FieldDTO dto, LogInData currentUser) 
	{
		CalendarDTO calendarDTO = (CalendarDTO) dto;	
		
		LocalDate date = calendarDTO.getDate();
		validation.validateEntryIsNotBeforeToday(date);
		
		Calendar calendar = new Calendar(
				date,
				calendarDTO.getTime(),
				calendarDTO.getTopic(),
				calendarDTO.getExtension(),
				currentUser.getUser()
		);
		
		calendarRep.save(calendar);
	}

}
