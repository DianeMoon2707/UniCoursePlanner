package com.uni_course_planner.service.relation.modul;

import java.util.*;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.modul.ModulDTOWithEdit;
import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.relation.modul.event_type.*;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.*;
import com.uni_course_planner.service.modal.strategy.EditStrategy;

@Service
public class ModulEditService implements EditStrategy
{
	private ModulRepository modulRep;
	private EventTypeRepository eventTypeRep;

	public ModulEditService(ModulRepository modulRep, EventTypeRepository eventTypeRep) 
	{
		this.modulRep = modulRep;
		this.eventTypeRep = eventTypeRep;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.MODUL;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new ModulDTOWithEdit("", 0, new HashSet<EventTypes>(),1L, "", 0);
	}

	@Override
	public FieldDTO createDTO(String data) 
	{
		ModulDTOWithEdit dto = new ModulDTOWithEdit();
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			List<String> dataList = mapper.readValue(data, new TypeReference<List<String>>() {});
			
			dto.setModul_id(Long.parseLong(dataList.get(0)));
			dto.setModulname(dataList.get(1));
			dto.setLp(Integer.parseInt(dataList.get(2)));

			String[] eventString = dataList.get(3).split("\n");
			HashSet<EventTypes> events = new HashSet<EventTypes>();
			
			for(String str : eventString)
			{
				events.add(EventTypes.fromDescriptionToEnum(str));
			}
			
			dto.setEventTypes(events);
			
			dto.setModulnameNeu(dto.getModulname());
			dto.setLpNeu(dto.getLp());
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
		return dto;
	}

	@Override
	public void edit(FieldDTO dto, LogInData currentUser) 
	{
		ModulDTOWithEdit modulDTO = (ModulDTOWithEdit)dto;
		ModulId mId = new ModulId(currentUser.getId(), modulDTO.getModul_id());
		Modul modul = modulRep.findById(mId).orElseThrow();
		
		//Standarddaten
		modul.setModulname(modulDTO.getModulnameNeu());
		modul.setLp(modulDTO.getLpNeu());
		
		modulRep.save(modul);
		
		//EventTypes
		for(EventTypes type : modulDTO.getEventTypes())
		{
			EventTypeId eId = new EventTypeId(mId, type);
			eventTypeRep.save(new EventType(eId, modul));
		}
	}
	
}
