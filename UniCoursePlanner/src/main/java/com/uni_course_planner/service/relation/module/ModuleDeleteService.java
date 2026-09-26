package com.uni_course_planner.service.relation.module;

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

@Service
public class ModuleDeleteService implements DeleteStrategy
{
	private ModuleRepository modulRep;
	private EventTypeRepository eventTypeRep;

	public ModuleDeleteService(ModuleRepository modulRep, EventTypeRepository eventTypeRep) 
	{
		this.modulRep = modulRep;
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
		Module modul = modulRep.findById(mId).get();
		
		dto.setModuleId(mId.getModulId());
		dto.setModulename(modul.getModulname());
		dto.setCredits(modul.getLp());
		
		Set<EventTypes> events = eventTypeRep.findAllByModul(modul)
				.stream()
				.map(eventType -> eventType.geteId().getType())
				.collect(Collectors.toSet());
		
		dto.setEventTypes(events);
		
		return dto;
	}

	@Override
	public void delete(FieldDTO dto, LogInData user) 
	{
		ModuleDTOWithID modulDTO = (ModuleDTOWithID)dto;
		
		ModuleId mId = new ModuleId(user.getId(), modulDTO.getModuleId());
		Set<EventTypes> selectedEvents = modulDTO.getEventTypes();

		for(EventTypes type : selectedEvents)
		{
			eventTypeRep.deleteById(new EventTypeId(mId, type));
		}
		
		if(!eventTypeRep.existsByMId(mId))
		{
			modulRep.deleteById(mId);
		}
	}
}
