package com.uni_course_planner.service.relation.calender;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.calender.CalenderDTOWithEdit;
import com.uni_course_planner.relation.calender.Calender;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.calender.CalenderRepository;
import com.uni_course_planner.service.modal.strategy.EditStrategy;

@Service
public class CalenderEditService implements EditStrategy
{
	private CalenderRepository calenderRep;
	
	public CalenderEditService(CalenderRepository calenderRep)
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
		return new CalenderDTOWithEdit();
	}

	@Override
	public FieldDTO createDTO(String data) 
	{
		CalenderDTOWithEdit dto = new CalenderDTOWithEdit();
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			List<String> dataList = mapper.readValue(data, new TypeReference<List<String>>() {});
			
			dto.setId(Long.parseLong(dataList.get(0)));
			
			Calender calender = calenderRep.findById(dto.getId()).get();
			
			dto.setDate(calender.getDate());
			dto.setTime(calender.getTime());
			dto.setTimeNeu(calender.getTime());
			
			dto.setTopic(calender.getTopic());
			dto.setTopicNeu(calender.getTopic());
			
			dto.setExtension(calender.getExtension());
			dto.setExtensionNeu(calender.getExtension());
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
		return dto;
	}

	@Override
	public void edit(FieldDTO dto, LogInData user)
	{
		CalenderDTOWithEdit calenderDTO = (CalenderDTOWithEdit)dto;
		Calender calender = calenderRep.findById(calenderDTO.getId()).orElseThrow();
		
		calender.setTime(calenderDTO.getTimeNeu());
		calender.setTopic(calenderDTO.getTopicNeu());
		calender.setExtension(calenderDTO.getExtensionNeu());
		
		calenderRep.save(calender);
	}

}
