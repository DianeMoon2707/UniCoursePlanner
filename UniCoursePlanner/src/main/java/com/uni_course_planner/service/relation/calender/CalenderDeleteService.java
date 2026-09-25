package com.uni_course_planner.service.relation.calender;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.calender.CalenderDTOWithID;
import com.uni_course_planner.relation.calender.Calender;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.calender.CalenderRepository;
import com.uni_course_planner.service.modal.strategy.DeleteStrategy;

@Service
public class CalenderDeleteService implements DeleteStrategy
{
	private CalenderRepository calenderRep;

	public CalenderDeleteService(CalenderRepository calenderRep) 
	{
		this.calenderRep = calenderRep;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.CALENDER;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new CalenderDTOWithID();
	}

	@Override
	public FieldDTO createDTO(String data, LogInData user) 
	{
		CalenderDTOWithID dto = new CalenderDTOWithID();
		
		Long id = Long.parseLong(data);
		Calender calender = calenderRep.findById(id).get();
		
		dto.setId(id);
		dto.setDate(calender.getDate());
		dto.setTime(calender.getTime());
		dto.setTopic(calender.getTopic());
		dto.setExtension(calender.getExtension());
		
		return dto;
	}

	@Override
	public void delete(FieldDTO dto, LogInData user) 
	{
		CalenderDTOWithID calenderDTO = (CalenderDTOWithID)dto;
		calenderRep.deleteById(calenderDTO.getId());
	}

}
