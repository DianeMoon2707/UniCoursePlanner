package com.uni_course_planner.service.relation.modul;

import java.util.*;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.views.PopupType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.modul.ModulDTOWithID;
import com.uni_course_planner.service.popup.strategy.DeleteStrategy;


@Service
public class ModulDeleteService implements DeleteStrategy
{

	@Override
	public PopupType getType() 
	{
		return PopupType.MODUL;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new ModulDTOWithID("nichts", 0, new HashSet<EventTypes>(),1L, 1L);
	}
	
	@Override
	public FieldDTO createDTO(String rowData) 
	{
		ModulDTOWithID dto = new ModulDTOWithID();
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			List<String> data = mapper.readValue(rowData, new TypeReference<List<String>>() {});
			
			dto.setModul_id(Long.parseLong(data.get(0)));
			dto.setUser_id(Long.parseLong(data.get(1)));
			dto.setModulname(data.get(2));
			dto.setLp(Integer.parseInt(data.get(3)));

			String[] eventString = data.get(4).split("\n");
			HashSet<EventTypes> events = new HashSet<EventTypes>();
			
			for(String str : eventString)
			{
				events.add(EventTypes.fromDescriptionToEnum(str));
			}
			
			dto.setEventTypes(events);
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
		return dto;
	}

	@Override
	public void delete(FieldDTO dto) 
	{
		ModulDTOWithID modulDTO = (ModulDTOWithID)dto;
		
		Set<EventTypes> selected = modulDTO.getEventTypes();
		System.out.println(selected);
	}
}
