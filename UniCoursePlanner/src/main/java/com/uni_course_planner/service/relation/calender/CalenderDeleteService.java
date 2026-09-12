package com.uni_course_planner.service.relation.calender;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public FieldDTO createDTO(String data) 
	{
		CalenderDTOWithID dto = new CalenderDTOWithID();
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			List<String> dataList = mapper.readValue(data, new TypeReference<List<String>>() {});
			
			dto.setId(Long.parseLong(dataList.get(0)));
			
			Calender calender = calenderRep.findById(dto.getId()).get();
			
			dto.setDate(calender.getDate());
			dto.setTime(calender.getTime());
			dto.setTopic(calender.getTopic());
			dto.setExtension(calender.getExtension());
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
		return dto;
	}

	@Override
	public void delete(FieldDTO dto, LogInData user) 
	{
		CalenderDTOWithID calenderDTO = (CalenderDTOWithID)dto;
		calenderRep.deleteById(calenderDTO.getId());
	}

}
