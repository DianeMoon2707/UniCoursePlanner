package com.uni_course_planner.service.relation.modul;

import java.util.*;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.views.PopupType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.modul.ModulDTOWithEdit;
import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.relation.modul.event_type.*;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.*;
import com.uni_course_planner.service.popup.strategy.EditStrategy;

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
	public PopupType getType() 
	{
		return PopupType.MODUL;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new ModulDTOWithEdit("", 0, new HashSet<EventTypes>(),1L, 1L, "", 0);
	}

	@Override
	public FieldDTO createDTO(String rowData) 
	{
		ModulDTOWithEdit dto = new ModulDTOWithEdit();
		
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
