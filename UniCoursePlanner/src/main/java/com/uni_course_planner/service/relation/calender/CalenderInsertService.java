package com.uni_course_planner.service.relation.calender;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.calender.CalenderDTO;
import com.uni_course_planner.relation.calender.Calender;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.calender.CalenderRepository;
import com.uni_course_planner.service.modal.strategy.InsertStrategy;
import com.uni_course_planner.service.validation.KalenderValidation;

@Service
public class CalenderInsertService implements InsertStrategy
{
	private CalenderRepository calenderRep;
	private KalenderValidation validation;

	public CalenderInsertService(CalenderRepository calenderRep, KalenderValidation validation) 
	{
		this.calenderRep = calenderRep;
		this.validation = validation;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.CALENDER;
	}

	@Override
	public FieldDTO createDTO(LogInData currentUser)
	{
		return new CalenderDTO();
	}

	@Override
	public void save(FieldDTO dto, LogInData currentUser) 
	{
		CalenderDTO calenderDTO = (CalenderDTO) dto;	
		
		LocalDate date = calenderDTO.getDate();
		validation.validateEntryIsNotBeforeToday(date);
		
		Calender calender = new Calender(
				date,
				calenderDTO.getTime(),
				calenderDTO.getTopic(),
				calenderDTO.getExtension(),
				currentUser.getUser()
		);
		
		calenderRep.save(calender);
	}

}
