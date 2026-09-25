package com.uni_course_planner.service.relation.calender;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.calender.CalenderDTOWithEdit;
import com.uni_course_planner.relation.calender.Calender;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.calender.CalenderRepository;
import com.uni_course_planner.service.modal.strategy.EditStrategy;
import com.uni_course_planner.service.validation.KalenderValidation;

@Service
public class CalenderEditService implements EditStrategy
{
	private CalenderRepository calenderRep;
	private KalenderValidation validation;

	public CalenderEditService(CalenderRepository calenderRep, KalenderValidation validation) 
	{
		super();
		this.calenderRep = calenderRep;
		this.validation = validation;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.CALENDER;
	}

	@Override
	public FieldDTO createDTO()
	{
		return new CalenderDTOWithEdit();
	}

	@Override
	public FieldDTO createDTO(String data, LogInData user) 
	{
		CalenderDTOWithEdit dto = new CalenderDTOWithEdit();
		
		Long id = Long.parseLong(data);
		Calender calender = calenderRep.findById(id).get();
		
		dto.setId(id);
		dto.setDate(calender.getDate());
		
		dto.setTime(calender.getTime());
		dto.setTimeNeu(calender.getTime());
		
		dto.setTopic(calender.getTopic());
		dto.setTopicNeu(calender.getTopic());
		
		dto.setExtension(calender.getExtension());
		dto.setExtensionNeu(calender.getExtension());
		
		return dto;
	}

	@Override
	public void edit(FieldDTO dto, LogInData user)
	{
		CalenderDTOWithEdit calenderDTO = (CalenderDTOWithEdit)dto;
		Calender calender = calenderRep.findById(calenderDTO.getId()).orElseThrow();
		
		LocalDate date = calenderDTO.getDate();
		validation.validateEntryIsNotBeforeToday(date);
		
		calender.setTime(calenderDTO.getTimeNeu());
		calender.setTopic(calenderDTO.getTopicNeu());
		calender.setExtension(calenderDTO.getExtensionNeu());
		
		calenderRep.save(calender);
	}

}
