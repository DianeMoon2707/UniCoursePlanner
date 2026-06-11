package com.uni_course_planner.service.relation.modul;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.views.PopupType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.modul.*;
import com.uni_course_planner.relation.modul.event_type.*;
import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.*;
import com.uni_course_planner.repository.user.UserRepository;
import com.uni_course_planner.service.modal.strategy.InsertStrategy;

@Service
public class ModulInsertService implements InsertStrategy
{
	private ModulRepository modulRep;
	private EventTypeRepository eventTypeRep;
	
	private UserRepository userRep;	
	
	public ModulInsertService(ModulRepository modulRep, EventTypeRepository eventTypeRep, 
		UserRepository userRep) 
	{
		this.modulRep = modulRep;
		this.eventTypeRep = eventTypeRep;
		this.userRep = userRep;
	}

	@Override
	public PopupType getType()
	{
		return PopupType.MODUL;
	}
	
	@Override
	public FieldDTO createDTO()
	{
		return new ModulDTO();
	}
	
	@Override
	public void save(FieldDTO dto, LogInData user) 
	{
		ModulDTO modulDTO = (ModulDTO) dto;
		Long mId = modulRep.getMaxModulId(user.getId()) + 1;
		
		Modul modul = new Modul(
				new ModulId(user.getId(), mId),
				modulDTO.getModulname(),
				modulDTO.getLp(),
				userRep.findById(user.getId()).get()
			);
		
		modulRep.save(modul);
		this.saveEvents(modulDTO, modul);
	}
	
	private void saveEvents(ModulDTO modulDTO, Modul modul)
	{
		for(EventTypes type : modulDTO.getEventTypes())
		{
			EventTypeId eId = new EventTypeId(modul.getmId(), type);
			EventType et = new EventType(eId, modul);
			eventTypeRep.save(et);
		}
	}
}
