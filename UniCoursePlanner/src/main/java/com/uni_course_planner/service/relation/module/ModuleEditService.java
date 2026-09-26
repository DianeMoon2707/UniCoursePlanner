package com.uni_course_planner.service.relation.module;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.module.EventTypes;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.module.ModuleDTOWithEdit;
import com.uni_course_planner.entity.module.event_type.*;
import com.uni_course_planner.entity.module.module.*;
import com.uni_course_planner.entity.module.module.Module;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.repository.module.*;
import com.uni_course_planner.service.modal.strategy.EditStrategy;
import com.uni_course_planner.service.validation.ModuleValidation;

@Service
public class ModuleEditService implements EditStrategy
{
	private ModuleRepository modulRep;
	private EventTypeRepository eventTypeRep;
	
	private ModuleValidation validation;

	public ModuleEditService(ModuleRepository modulRep, EventTypeRepository eventTypeRep, ModuleValidation validation)
	{
		this.modulRep = modulRep;
		this.eventTypeRep = eventTypeRep;
		
		this.validation = validation;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.MODULE;
	}
	
	@Override
	public FieldDTO createDTO()
	{
		return new ModuleDTOWithEdit("", 0, new HashSet<EventTypes>(), 1L, "", 0);
	}

	@Override
	public FieldDTO createDTO(String data, LogInData user) 
	{
		ModuleDTOWithEdit dto = new ModuleDTOWithEdit();
		
		ModuleId mId = new ModuleId(user.getId(), Long.parseLong(data));
		Module modul = modulRep.findById(mId).get();
		
		dto.setModuleId(mId.getModuleId());
		
		dto.setModulename(modul.getModulename());
		dto.setModulenameNew(dto.getModulename());
		
		dto.setCredits(modul.getCredits());
		dto.setCreditsNew(dto.getCredits());
		
		Set<EventTypes> events = eventTypeRep.findAllByModule(modul)
				.stream()
				.map(eventType -> eventType.geteId().getType())
				.collect(Collectors.toSet());
		
		dto.setEventTypes(events);
	
		return dto;
	}

	@Override
	public void edit(FieldDTO dto, LogInData user) 
	{
		ModuleDTOWithEdit modulDTO = (ModuleDTOWithEdit)dto;
		ModuleId mId = new ModuleId(user.getId(), modulDTO.getModuleId());
		Module modul = modulRep.findById(mId).orElseThrow();
		
		validation.validateUserChangesModulnameToAExistingOne(
			modulDTO.getModulenameNew(), 
			modulDTO.getModuleId(), 
			user.getId()
		);
		
		//Standarddaten
		modul.setModulename(modulDTO.getModulenameNew());
		modul.setCredits(modulDTO.getCreditsNew());
		
		modulRep.save(modul);
		
		//EventTypes
		for(EventTypes type : modulDTO.getEventTypes())
		{
			EventTypeId eId = new EventTypeId(mId, type);
			eventTypeRep.save(new EventType(eId, modul));
		}
	}	
}
