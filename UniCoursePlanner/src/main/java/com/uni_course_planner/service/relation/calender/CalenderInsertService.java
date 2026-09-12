package com.uni_course_planner.service.relation.calender;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.calender.CalenderDTO;
import com.uni_course_planner.relation.calender.Calender;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.calender.CalenderRepository;
import com.uni_course_planner.service.modal.strategy.InsertStrategy;

@Service
public class CalenderInsertService implements InsertStrategy
{
	private CalenderRepository calenderRep;

	public CalenderInsertService(CalenderRepository calenderRep)
	{
		this.calenderRep = calenderRep;
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
		Calender calender = new Calender(
				calenderDTO.getDate(),
				calenderDTO.getTime(),
				calenderDTO.getTopic(),
				calenderDTO.getExtension(),
				currentUser.getUser()
		);
		
		calenderRep.save(calender);
	}

}
