package com.uni_course_planner.service.relation.modul;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.modul.ModulDTOWithEdit;
import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.relation.modul.event_type.*;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.*;
import com.uni_course_planner.service.modal.strategy.EditStrategy;
import com.uni_course_planner.service.validation.ModulValidation;

@Service
public class ModulEditService implements EditStrategy
{
	private ModulRepository modulRep;
	private EventTypeRepository eventTypeRep;
	
	private ModulValidation validation;

	public ModulEditService(ModulRepository modulRep, EventTypeRepository eventTypeRep, ModulValidation validation)
	{
		this.modulRep = modulRep;
		this.eventTypeRep = eventTypeRep;
		
		this.validation = validation;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.MODUL;
	}
	
	@Override
	public FieldDTO createDTO()
	{
		return new ModulDTOWithEdit("", 0, new HashSet<EventTypes>(), 1L, "", 0);
	}

	@Override
	public FieldDTO createDTO(String data, LogInData user) 
	{
		ModulDTOWithEdit dto = new ModulDTOWithEdit();
		
		ModulId mId = new ModulId(user.getId(), Long.parseLong(data));
		Modul modul = modulRep.findById(mId).get();
		
		dto.setModul_id(mId.getModulId());
		
		dto.setModulname(modul.getModulname());
		dto.setModulnameNeu(dto.getModulname());
		
		dto.setLp(modul.getLp());
		dto.setLpNeu(dto.getLp());
		
		Set<EventTypes> events = eventTypeRep.findAllByModul(modul)
				.stream()
				.map(eventType -> eventType.geteId().getType())
				.collect(Collectors.toSet());
		
		dto.setEventTypes(events);
	
		return dto;
	}

	@Override
	public void edit(FieldDTO dto, LogInData user) 
	{
		ModulDTOWithEdit modulDTO = (ModulDTOWithEdit)dto;
		ModulId mId = new ModulId(user.getId(), modulDTO.getModul_id());
		Modul modul = modulRep.findById(mId).orElseThrow();
		
		validation.validateUserChangesModulnameToAExistingOne(
			modulDTO.getModulnameNeu(), 
			modulDTO.getModul_id(), 
			user.getId()
		);
		
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
