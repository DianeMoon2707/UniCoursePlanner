package com.uni_course_planner.service.relation.modul;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.modul.ModulDTOWithID;
import com.uni_course_planner.relation.modul.event_type.*;
import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.*;
import com.uni_course_planner.service.modal.strategy.DeleteStrategy;

@Service
public class ModulDeleteService implements DeleteStrategy
{
	private ModulRepository modulRep;
	private EventTypeRepository eventTypeRep;

	public ModulDeleteService(ModulRepository modulRep, EventTypeRepository eventTypeRep) 
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
		return new ModulDTOWithID("", 0, new HashSet<EventTypes>(), 1L);
	}
	
	@Override
	public FieldDTO createDTO(String data, LogInData user) 
	{
		ModulDTOWithID dto = new ModulDTOWithID();

		ModulId mId = new ModulId(user.getId(), Long.parseLong(data));
		Modul modul = modulRep.findById(mId).get();
		
		dto.setModul_id(mId.getModulId());
		dto.setModulname(modul.getModulname());
		dto.setLp(modul.getLp());
		
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
		ModulDTOWithID modulDTO = (ModulDTOWithID)dto;
		
		ModulId mId = new ModulId(user.getId(), modulDTO.getModul_id());
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
