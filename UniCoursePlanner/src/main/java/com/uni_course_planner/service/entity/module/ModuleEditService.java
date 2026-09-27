package com.uni_course_planner.service.entity.module;

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

//Edits modules and adds event types
@Service
public class ModuleEditService implements EditStrategy
{
	private ModuleRepository moduleRep;
	private EventTypeRepository eventTypeRep;
	
	private ModuleValidation validation;

	public ModuleEditService(ModuleRepository moduleRep, EventTypeRepository eventTypeRep, ModuleValidation validation)
	{
		this.moduleRep = moduleRep;
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
		Module module = moduleRep.findById(mId).get();
		
		dto.setModuleId(mId.getModuleId());
		
		dto.setModulename(module.getModulename());
		dto.setModulenameNew(dto.getModulename());
		
		dto.setCredits(module.getCredits());
		dto.setCreditsNew(dto.getCredits());
		
		Set<EventTypes> events = eventTypeRep.findAllByModule(module)
				.stream()
				.map(eventType -> eventType.geteId().getType())
				.collect(Collectors.toSet());
		
		dto.setEventTypes(events);
	
		return dto;
	}

	@Override
	public void edit(FieldDTO dto, LogInData user) 
	{
		ModuleDTOWithEdit moduleDTO = (ModuleDTOWithEdit)dto;
		ModuleId mId = new ModuleId(user.getId(), moduleDTO.getModuleId());
		Module module = moduleRep.findById(mId).orElseThrow();
		
		validation.validateModulenameDoesNotContainHyphen(module.getModulename());
		validation.validateUserChangesModulnameToAExistingOne(
			moduleDTO.getModulenameNew(), 
			moduleDTO.getModuleId(), 
			user.getId()
		);
		
		module.setModulename(moduleDTO.getModulenameNew());
		module.setCredits(moduleDTO.getCreditsNew());
		
		moduleRep.save(module);
		
		//add new event types
		for(EventTypes type : moduleDTO.getEventTypes())
		{
			EventTypeId eId = new EventTypeId(mId, type);
			eventTypeRep.save(new EventType(eId, module));
		}
	}	
}
