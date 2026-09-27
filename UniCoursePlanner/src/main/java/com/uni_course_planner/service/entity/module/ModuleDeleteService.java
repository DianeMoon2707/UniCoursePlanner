package com.uni_course_planner.service.entity.module;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.module.EventTypes;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.module.ModuleDTOWithID;
import com.uni_course_planner.entity.module.event_type.*;
import com.uni_course_planner.entity.module.module.*;
import com.uni_course_planner.entity.module.module.Module;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.repository.module.*;
import com.uni_course_planner.service.modal.strategy.DeleteStrategy;

//Deletes modules or their selected event types
@Service
public class ModuleDeleteService implements DeleteStrategy
{
	private ModuleRepository moduleRep;
	private EventTypeRepository eventTypeRep;

	public ModuleDeleteService(ModuleRepository moduleRep, EventTypeRepository eventTypeRep) 
	{
		this.moduleRep = moduleRep;
		this.eventTypeRep = eventTypeRep;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.MODULE;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new ModuleDTOWithID("", 0, new HashSet<EventTypes>(), 1L);
	}
	
	@Override
	public FieldDTO createDTO(String data, LogInData user) 
	{
		ModuleDTOWithID dto = new ModuleDTOWithID();

		ModuleId mId = new ModuleId(user.getId(), Long.parseLong(data));
		Module module = moduleRep.findById(mId).get();
		
		dto.setModuleId(mId.getModuleId());
		dto.setModulename(module.getModulename());
		dto.setCredits(module.getCredits());
		
		Set<EventTypes> events = eventTypeRep.findAllByModule(module)
				.stream()
				.map(eventType -> eventType.geteId().getType())
				.collect(Collectors.toSet());
		
		dto.setEventTypes(events);
		
		return dto;
	}

	@Override
	public void delete(FieldDTO dto, LogInData user) 
	{
		ModuleDTOWithID moduleDTO = (ModuleDTOWithID)dto;
		
		ModuleId mId = new ModuleId(user.getId(), moduleDTO.getModuleId());
		Set<EventTypes> selectedEvents = moduleDTO.getEventTypes();

		for(EventTypes type : selectedEvents)
		{
			eventTypeRep.deleteById(new EventTypeId(mId, type));
		}
		
		if(!eventTypeRep.existsByMId(mId))
		{
			moduleRep.deleteById(mId);
		}
	}
}
