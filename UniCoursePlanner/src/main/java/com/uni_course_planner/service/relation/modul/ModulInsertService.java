package com.uni_course_planner.service.relation.modul;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.modul.*;
import com.uni_course_planner.relation.modul.event_type.*;
import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.*;
import com.uni_course_planner.repository.user.UserRepository;
import com.uni_course_planner.service.modal.strategy.InsertStrategy;
import com.uni_course_planner.service.validation.ModulValidation;

@Service
public class ModulInsertService implements InsertStrategy
{
	private ModulRepository modulRep;
	private EventTypeRepository eventTypeRep;
	
	private UserRepository userRep;	
	
	private ModulValidation validation;

	public ModulInsertService(ModulRepository modulRep, EventTypeRepository eventTypeRep, UserRepository userRep,
			ModulValidation validation) 
	{
		this.modulRep = modulRep;
		this.eventTypeRep = eventTypeRep;
		
		this.userRep = userRep;
		
		this.validation = validation;
	}

	@Override
	public ModalType getType()
	{
		return ModalType.MODUL;
	}
	
	@Override
	public FieldDTO createDTO(LogInData currentUser)
	{
		return new ModulDTO();
	}
	
	@Override
	public void save(FieldDTO dto, LogInData currentUser) 
	{
		ModulDTO modulDTO = (ModulDTO) dto;
		Long mId = modulRep.getMaxModulId(currentUser.getId()) + 1;
		
		Modul modul = new Modul(
				new ModulId(currentUser.getId(), mId),
				modulDTO.getModulname(),
				modulDTO.getLp(),
				userRep.findById(currentUser.getId()).get()
			);
		
		validation.validateUserAlreadyGeneratedModul(modul.getModulname(), currentUser.getId());
		
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
