package com.uni_course_planner.service.relation.module;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.module.EventTypes;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.module.*;
import com.uni_course_planner.entity.module.event_type.*;
import com.uni_course_planner.entity.module.module.*;
import com.uni_course_planner.entity.module.module.Module;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.repository.module.*;
import com.uni_course_planner.repository.user.UserRepository;
import com.uni_course_planner.service.modal.strategy.InsertStrategy;
import com.uni_course_planner.service.validation.ModuleValidation;

@Service
public class ModuleInsertService implements InsertStrategy
{
	private ModuleRepository modulRep;
	private EventTypeRepository eventTypeRep;
	
	private UserRepository userRep;	
	
	private ModuleValidation validation;

	public ModuleInsertService(ModuleRepository modulRep, EventTypeRepository eventTypeRep, UserRepository userRep,
			ModuleValidation validation) 
	{
		this.modulRep = modulRep;
		this.eventTypeRep = eventTypeRep;
		
		this.userRep = userRep;
		
		this.validation = validation;
	}

	@Override
	public ModalType getType()
	{
		return ModalType.MODULE;
	}
	
	@Override
	public FieldDTO createDTO(LogInData currentUser)
	{
		return new ModuleDTO();
	}
	
	@Override
	public void save(FieldDTO dto, LogInData currentUser) 
	{
		ModuleDTO modulDTO = (ModuleDTO) dto;
		Long mId = modulRep.findMaxModuleId(currentUser.getId()) + 1;
		
		Module modul = new Module(
				new ModuleId(currentUser.getId(), mId),
				modulDTO.getModulename(),
				modulDTO.getCredits(),
				userRep.findById(currentUser.getId()).get()
			);
		
		validation.validateUserAlreadyGeneratedModul(modul.getModulename(), currentUser.getId());
		
		modulRep.save(modul);
		this.saveEvents(modulDTO, modul);
	}
	
	private void saveEvents(ModuleDTO modulDTO, Module modul)
	{
		for(EventTypes type : modulDTO.getEventTypes())
		{
			EventTypeId eId = new EventTypeId(modul.getmId(), type);
			EventType et = new EventType(eId, modul);
			eventTypeRep.save(et);
		}
	}
}
