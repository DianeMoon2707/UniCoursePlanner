package com.uni_course_planner.service.entity.module;

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

//Save modules and their event types
@Service
public class ModuleInsertService implements InsertStrategy
{
	private ModuleRepository moduleRep;
	private EventTypeRepository eventTypeRep;
	
	private UserRepository userRep;	
	
	private ModuleValidation validation;

	public ModuleInsertService(ModuleRepository moduleRep, EventTypeRepository eventTypeRep, UserRepository userRep,
			ModuleValidation validation) 
	{
		this.moduleRep = moduleRep;
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
		ModuleDTO moduleDTO = (ModuleDTO) dto;
		Long mId = moduleRep.findMaxModuleId(currentUser.getId()) + 1;
		
		Module module = new Module(
				new ModuleId(currentUser.getId(), mId),
				moduleDTO.getModulename(),
				moduleDTO.getCredits(),
				userRep.findById(currentUser.getId()).get()
			);
		
		validation.validateModulenameDoesNotContainHyphen(module.getModulename());
		validation.validateUserAlreadyGeneratedModul(module.getModulename(), currentUser.getId());
		
		moduleRep.save(module);
		this.saveEvents(moduleDTO, module);
	}
	
	private void saveEvents(ModuleDTO moduleDTO, Module module)
	{
		for(EventTypes type : moduleDTO.getEventTypes())
		{
			EventTypeId eId = new EventTypeId(module.getmId(), type);
			EventType et = new EventType(eId, module);
			eventTypeRep.save(et);
		}
	}
}
